import { Component } from '@angular/core';


@Component({
    selector: "app-registry",
    template: `
    <app-employee name="Richard" specialty ="Java" position = "Lead Trainer" years=4></app-employee>
<app-employee name="Sierra" specialty ="JavaScript" position = "Trainer" years=0></app-employee>
<app-employee name="Adam" specialty ="Java" position = "Senior Trainer" years=2></app-employee>
    `
})

export class RegistryComponent{
    
}