import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule} from '@angular/common/http'; // allows us to make http request
import {FormsModule} from '@angular/forms'

// components
import { AppComponent } from './app.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { ViewBooksPageComponent } from './components/view-books-page/view-books-page.component';
import { BookAdderPageComponent } from './components/book-adder-page/book-adder-page.component';

//services
import {BookService} from './services/book.service'

// Angular Matierials
import {MatInputModule} from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { QuotesPipe } from './pipes/quotes.pipe';

@NgModule({
  //components
  declarations: [
    AppComponent,
    HomePageComponent,
    ViewBooksPageComponent,
    BookAdderPageComponent,
    QuotesPipe
  ],
  //modules
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatInputModule,
    BrowserAnimationsModule
  ],
  providers: [BookService], // services
  bootstrap: [AppComponent]
})
export class AppModule { }
