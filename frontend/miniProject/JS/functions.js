const status = document.querySelector('#status');
let animalsNearYou = [];
    async function getOccurences(latitude,longitude){
        // want to read the DOM for value before fetch
        // 1 deg of latitude = 68.9722 miles
        // 1 deg of longitude = cos(lat.radians) * 69.172
        let earthRadius = 3960.00000;
        let degToRadians = Math.PI / 180.00000;
        let radToDegrees = 180.00000 / Math.PI;
        let inputDistance = document.getElementById("distanceInput").value;

        function milesToLat(miles){
            return (miles/earthRadius) * radToDegrees;
        }

        function milesToLong(latitude, miles){
            let r = earthRadius*Math.cos(latitude*degToRadians)
            return (miles/r)*radToDegrees;
        }

        let mileToLat = milesToLat(inputDistance);
        let mileToLon = milesToLong(latitude,inputDistance);

        let latLow = (latitude - mileToLat).toPrecision(8);
        let latHi = (latitude + mileToLat).toPrecision(8);
        let lonLow = (longitude - mileToLon).toPrecision(8);
        let lonHi = (longitude + mileToLon).toPrecision(8);



        status.textContent = 'Gathering all species occurences near you...';
        
        function generateQuery(){

            let queryParams = `search?&limit=250&year=2018,2020&decimalLatitude=${latLow},${latHi}&decimalLongitude=${lonLow},${lonHi}`;
            queryParams+= `&kingdomKey=`
            if(ifAnimals){
                queryParams+= `1`;
            }
            if(ifPlants){
                queryParams+= `,6`;
            }

            return queryParams;
        }



        let qP = generateQuery();
        const httpResponse = await fetch(`http://api.gbif.org/v1/occurrence/${qP}`);
        // get the species key after this
        const occurences = await httpResponse.json();
        if(httpResponse.status == 404){
            alert('not Found');
            return;
        }
        
        
        

        console.log(occurences);
        
        


        let i = 0;
        let speciesKeys = [];
        while( i < occurences.results.length){
            var tmpkey = occurences.results[i].speciesKey;
            speciesKeys.push(tmpkey);
            i++;
        } 
        

        let uniqueSpeciesKeys = [...new Set(speciesKeys)];
    
        
        console.log("unique keys finished");
        getAnimals(uniqueSpeciesKeys);
    }

    async function getAnimals(uniqueSpeciesKeys){
        console.log("get Animals");
        status.textContent = 'Collecting animal data...';
        let j = 0;
        while( j < uniqueSpeciesKeys.length){
            let httpAnimalResponse = await fetch(`http://api.gbif.org/v1/species/${uniqueSpeciesKeys[j]}`);
            let animalResponse = await httpAnimalResponse.json();
            let httpDogResponse = await fetch('https://dog.ceo/api/breeds/image/random');
            let Dog = await httpDogResponse.json();

            var animal = {
                animalName: animalResponse.vernacularName,
                animalSpecies: animalResponse.species,
                animalGenus: animalResponse.genus,
                animalFamily: animalResponse.family,
                animalOrder: animalResponse.order,
                animalClass: animalResponse.class,
                animalPhylum: animalResponse.phylum,
                animalKingdom:  animalResponse.kingdom,
                animalPicture: Dog.message
            }

            animalsNearYou.push(animal);
            

            j++;
        } 
        status.textContent = 'Success!';
        
        document.getElementById('status').innerHTML = `<button id = "show-animals" class="btn btn-primary"> Show me the animals</button> <br/>`;
        document.querySelector('#show-animals').addEventListener('click', renderAnimalCards);
        console.log("Animals got");
        

        
        

    }
    
    function geoFindMe() {

        

        function success(position) {
            const latitude  = position.coords.latitude;
            const longitude = position.coords.longitude;

            getOccurences(latitude,longitude);
        }

        function error() {
            status.textContent = 'Unable to retrieve your location';
        }

        if(!navigator.geolocation) {
            status.textContent = 'Geolocation is not supported by your browser';
        } else {
            status.textContent = 'Locating...';
            navigator.geolocation.getCurrentPosition(success, error);
        }

    }



    async function renderAnimalCards() {

        const container = document.getElementById('card-columns');


        
        
        animalsNearYou.forEach((animal, idx) => {
            const content = `            
                    <div class="card">
                        <img class ="card-img-top" src = "${animal.animalPicture}">
                        <div class="card-body">
                            <h5 class="card-title">${animal.animalName}</h5>
                            <p class="card-text">${animal.animalSpecies}</p>
                            <p class="card-text">${animal.animalGenus}</p>
                            <p class="card-text">${animal.animalFamily}</p>
                            <p class="card-text">${animal.animalOrder}</p>
                            <p class="card-text">${animal.animalClass}</p>
                            <p class="card-text"> ${animal.animalPhylum}</p>
                        </div>
                    </div>`;
            container.innerHTML += content;
        })
        
    }    


    let ifAnimals = true;
    let ifPlants = false;
    
    

    document.querySelector('#find-animals-btn').addEventListener('click', geoFindMe);
    
    document.querySelector('#if-animals').addEventListener('change', function(){
        if(this.checked) {
            ifAnimals = true;
        } else {
            ifAnimals = false;
        }
    });
    document.querySelector('#if-plants').addEventListener('change', function(){
        if(this.checked) {
            ifPlants = true;
        } else {
            ifPlants = false;
        }
    });



