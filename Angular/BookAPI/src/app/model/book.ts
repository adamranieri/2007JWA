
export class Book{

    id:number;
    title:string;
    genre:string;
    author:string;
    pages:number;

    constructor(id:number, title:string,genre:string,author:string,pages:number){
        this.id = id;
        this.genre = genre;
        this.pages = pages;
        this.title = title;
        this.author = author;
    }
}