import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'trig'
})
export class TrigPipe implements PipeTransform {

  transform(x:number) :number{
    return x.toFixed( [2] );
  }

}
