import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms'

import { AppComponent } from './app.component';
import { HiscoreheaderComponent } from './components/hiscoreheader/hiscoreheader.component';
import { HiscoresListComponent } from './components/hiscores-list/hiscores-list.component';
import { HiscoreFormComponent } from './components/hiscore-form/hiscore-form.component';

//
import {ScoreService} from './services/score.service'

@NgModule({
  // components
  declarations: [
    AppComponent,
    HiscoreheaderComponent,
    HiscoresListComponent,
    HiscoreFormComponent
  ],
  // modules
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [ScoreService], // your services
  bootstrap: [AppComponent]
})
export class AppModule { }
