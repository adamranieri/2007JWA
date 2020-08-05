import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
// import the HttpClient service
// angular automatically sets up this client for us when we import the module
import {Book} from '../model/book'

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http:HttpClient) { } // inject the HttpClient service into our BookService

  // async methods always return a promise
  async getAllBooks():Promise<Array<Book>>{
    const books:Array<Book> = await this.http.get<Array<Book>>("http://localhost:7000/books").toPromise();// this returns a promise
    return books;
  }

  async getBookById(id:number):Promise<Book>{
    const book:Book = await this.http.get<Book>(`http://localhost:7000/books/${id}`).toPromise();
    return book;
  }

  async createBook(book:Book):Promise<Book>{
    // second parameter is the body
    // it takes in an object it will automatically 
    // turn it into a json when sent
    book = await this.http.post<Book>("http://localhost:7000/books",book).toPromise();
    return book;
  }
}
