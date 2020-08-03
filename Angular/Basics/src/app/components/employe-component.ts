import { Component, Input} from '@angular/core'
@Component({
    selector:"app-employee",
    template: `
    
    <h3>{{name}}</h3>

    <ul>
        <li>Position: {{position}}</li>
        <li>Specialty: {{specialty}}</li>
        <li >Years With Company: {{years}}</li>
    </ul>

    `  
})

export class EmployeeComponent{
    // can have instance variables
    // we can interpolate between our variables and our html

    // @Input makes instance varables attributes that we can set in your html
    @Input() name:string;
    @Input() position:string;
    @Input() specialty:string;
    @Input() years:number;

}

