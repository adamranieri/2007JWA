import { Component, OnInit } from '@angular/core';
import {ISSService} from 'src/app/services/iss/iss.service';
import {LocationService} from 'src/app/services/location/location.service';
import {ISS} from 'src/app/models/iss';
import {You} from 'src/app/models/you';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})

export class HomePageComponent implements OnInit {

  constructor(private issService:ISSService, private locationService:LocationService) { }

  yourLat:number = null;
  yourLon:number = null;

  distance:number = null;

  issNow:ISS = null;

  ngOnInit(): void {
    this.getPosition();
   }

  async locateISS():Promise<void>{
    this.issNow = await this.issService.getISS()
    this.getDistance(this.yourLat,this.yourLon,this.issNow.iss_position.latitude,this.issNow.iss_position.longitude)
    var that = this;
    setTimeout(function (){
      that.locateISS()
    }, 500 )
  }

  getPosition() {
    var that = this;
    this.locationService.getPosition().then(pos=>
      {
        that.yourLat=pos.lat;
        that.yourLon=pos.lng;
      });
  }

  getDistance(lat1, lon1, lat2, lon2) {
      var radlat1 = Math.PI * lat1/180;
      var radlat2 = Math.PI * lat2/180;
      var theta = lon1-lon2;
      var radtheta = Math.PI * theta/180;
      var dist = Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
      if (dist > 1) {
        dist = 1;
      }
      dist = Math.acos(dist);
      dist = dist * 180/Math.PI;
      dist = dist * 60 * 1.1515;
      dist = Math.pow(dist,2) + Math.pow(254,2)
      dist = Math.sqrt(dist);
      this.distance = dist;
  }
}
