import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

//custom component
import {IntroComponent} from './components/intro-component'
import {EmployeeComponent} from './components/employe-component'
import {RegistryComponent} from './components/employee-registry-component'

// app.module is the main module of your Angular application
// Every component you make
// every library you use will register itself in theis app.module
@NgModule({
  //Components
  declarations: [
    AppComponent,
    IntroComponent,
    EmployeeComponent,
    RegistryComponent
  ],
  //Libraries that your application needs 
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
