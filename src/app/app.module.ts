import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';

import { AppComponent } from './app.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { AppRoutingModule } from './app-routing.module';

import {ISSService} from './services/iss/iss.service';
import { TrigPipe } from './pipes/trig.pipe';
import { LocationService } from './services/location/location.service';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    TrigPipe
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    ISSService,
    LocationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
