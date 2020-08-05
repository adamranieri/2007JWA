import { Component, OnInit } from '@angular/core';
import { BookService } from 'src/app/services/book.service';
import { Book } from 'src/app/model/book';

@Component({
  selector: 'app-view-books-page',
  templateUrl: './view-books-page.component.html',
  styleUrls: ['./view-books-page.component.css']
})
export class ViewBooksPageComponent implements OnInit {

  constructor(private bookService:BookService) { }

  books:Array<Book> = [];

  selectedBook:Book = null;
  // An Angular lifecycle method
  // gets called whenever you create an instance of this component
  // **kinda like a constrcuor 
  ngOnInit(): void {
    this.allBooks(); // because ngOnInit is not asynchronous I cannot wait for the
  }

  async allBooks():Promise<void>{
    this.books = await this.bookService.getAllBooks()
  };

  async showBookDetails(id: number){
    const book = await this.bookService.getBookById(id);
    this.selectedBook = book;
  }

}
