import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { ViewBooksPageComponent } from './components/view-books-page/view-books-page.component';
import { BookAdderPageComponent } from './components/book-adder-page/book-adder-page.component';

const routes: Routes = [
  {path:"home", component :HomePageComponent},
  {path: "viewbooks",component: ViewBooksPageComponent},
  {path: "addbooks",component: BookAdderPageComponent},
  {path: "**", component:HomePageComponent} // put the wild card path last
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
