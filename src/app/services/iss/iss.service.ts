import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {ISS} from '../../models/iss';

@Injectable({
  providedIn: 'root'
})
export class ISSService {

  constructor(private http:HttpClient) { }

  async getISS():Promise<ISS>{
    const iss:ISS = await this.http.get<ISS>("http://api.open-notify.org/iss-now.json").toPromise();
    return iss;
  }
}
