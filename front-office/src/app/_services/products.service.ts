import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { productsDTO } from '../models/dto/productsDTO';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  //api backend
  private base_url= environment.publicApi +"/product";
  
  headers = new HttpHeaders().set('Content-Type', 'application/json');

 ProductDTO ={
  title:'', 
  detailimage: '',
  note: '',
  name: '',
  image: '',
  consultationNumber: '',


  }
  constructor(private http :HttpClient) { }

  
//get all by entreprise
getAllproductsByEntreprise():Observable<productsDTO>{
 return this.http.get<productsDTO>(this.base_url);
}


// get by id
getByidproduct(id:number):Observable<productsDTO>{
  return this.http.get<productsDTO>(this.base_url + '/' +id);

}


 
}
