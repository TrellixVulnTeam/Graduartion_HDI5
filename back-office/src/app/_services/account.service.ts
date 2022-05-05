import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { AccountDTO } from '../models/dto/accountDTO';

@Injectable({
  providedIn: 'root'
})
export class Accountservice {

  //api backend
  private base_url="http://localhost:8089/user";
  
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http :HttpClient, private datePipe: DatePipe) { }

  //http opttion
  httpOptions={ 
    headers:new HttpHeaders({
      'content-type':'application/json'

    })
  }
  //handel api  errors 
  handleError(error: HttpErrorResponse){
    if( error.error instanceof ErrorEvent){
    //a client-side or a neetwork error occurend .Handel it accordingly
    console.error('An Error occurend' , error.error.message)

  }
  else{
    // the backend may returned an successfully response code 
    // the response body may contain clues as to what went wrong 
    console.error(`backend returned code ${error.status}, ` +
    `body was : ${ error.error}`
    );}
   // return an observabel with a user-facing error message 
  return throwError( 'something bad happined , please try again later .');
};


// insert 
create(item : AccountDTO):Observable<AccountDTO[]>{
  return this.http.post<AccountDTO[]>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
}

//get all account data 
all():Observable<AccountDTO>{
   return this.http.get<AccountDTO>(this.base_url).pipe(retry(2),catchError(this.handleError));
 }


  // get account by id
  getByid(id:number):Observable<AccountDTO>{
    return this.http.get<AccountDTO>(this.base_url + '/' +id).pipe(retry(2),catchError(this.handleError));

  }

   // update account by Id the
   update(item : AccountDTO){
    return this.http.put<AccountDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
   }

    // delete accounts
    delete(id:number){
      return this.http.delete<AccountDTO>(this.base_url + '/' +id,this.httpOptions).pipe(retry(2),catchError(this.handleError));

}

//validation formulaire
    form : FormGroup= new FormGroup({
    id: new FormControl(null),
    username: new FormControl('',Validators.required),
    email : new FormControl('',[ Validators.required]),
    password : new FormControl('',[ Validators.required]),
    matchingPassword : new FormControl('',[ Validators.required]),
    fiscaleCode : new FormControl('',[ Validators.required]),
    accountStatus: new FormControl(''),
});

// inialisation formulaire 
initializeFormGroup() {
    this.form.setValue({
    id :null,
    username: null,
    email: null,
    password: null,
    matchingPassword: null,
    fiscaleCode: null,
    accountStatus:null
  });
}
populateForm(account: any) {
  this.form.patchValue(_.omit(account));
}


   //update account by status
   updateAccountByStatus(id : number,item : string){
    return this.http.put<any>(this.base_url + '/toggle-status/' + id,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
     }
   
}
