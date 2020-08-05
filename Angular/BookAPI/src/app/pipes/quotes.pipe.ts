import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'quotes'
})
export class QuotesPipe implements PipeTransform {

  //A pipe will take some sort of input and give back an outpout
  // Analagous to functions in SQL in that they must have an input and always give an output

  // common reasons to pipe
  //ex you have a number and want to display it as currency 
  // you have a date in one string format but want to display as another
  // you have a true false value that you want to display with words
  // isEnrolled: false     false => "Not Enrolled" true => "Enrolled"
  transform(phrase:string): string {
    return `"${phrase}"`;
  }

}
