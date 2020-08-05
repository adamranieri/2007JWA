import { Component, OnInit } from '@angular/core';
import { BookService } from 'src/app/services/book.service';
import { Book } from 'src/app/model/book';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-adder-page',
  templateUrl: './book-adder-page.component.html',
  styleUrls: ['./book-adder-page.component.css']
})
export class BookAdderPageComponent implements OnInit {

  constructor(private bookService:BookService, private router:Router) { }

  titleField:string;
  authorField:string;
  genreField:string;
  pagesField:number;
  

  ngOnInit(): void {
  }

  async addBook(){

    let book = new Book(0,this.titleField,this.genreField,this.authorField,this.pagesField);
    book = await this.bookService.createBook(book);

    this.authorField="";
    this.genreField="";
    this.pagesField=0;
    this.titleField="";

    alert("Book added!!!")
    //this.router.navigateByUrl("/viewbooks")
  }

}
