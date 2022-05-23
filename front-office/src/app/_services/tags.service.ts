
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators'
import { environment } from 'src/environments/environment';
import { TagsDTO } from '../models/dto/TagsDTO';

@Injectable({
  providedIn: 'root'
})
export class TagsSerice {

  //api backend
  private base_url= environment.publicApi +"/tags";
  
  constructor(private http :HttpClient) { }


//get all data 
getAlltags():Observable<TagsDTO>{
   return this.http.get<TagsDTO>(this.base_url);
}

  // get tags by id
  getByidTags(id:number):Observable<TagsDTO>{
    return this.http.get<TagsDTO>(this.base_url + '/' +id)
  }

}
