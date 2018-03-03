import {Injectable} from '@angular/core';
import {environment} from 'environments/environment';

import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import {Person} from '../model/Person';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/do';
import {ErrorObservable} from 'rxjs/observable/ErrorObservable';

const API_URL = environment.apiUrl;

@Injectable()
export class PersonServiceService {

  constructor(private http: HttpClient) {
  }

  getPaginatedData(link: string): Observable<any> {
    return this.http
      .get(link)
      .map( data => data)
      .do(data => console.log('Persons Received'))
      .catch(this.handleError);
  }


  getCompletePersons(): Observable<any> {
    return this.http
      .get(API_URL + '/persons/search/findAll')
      .map( data => data)
      .do(data => console.log('All Persons Received'))
      .catch(this.handleError);
  }

  // API: GET /persons
  // will use this.http.get()
  getAllPersons(): Observable<any> {
    return this.http
      .get(API_URL + '/persons')
      .map( data => data)
      .do(data => console.log('Persons Received'))
      .catch(this.handleError);
  }

  // API: POST /persons
  // will use this.http.post()
  createPerson(person: Person): Observable<any> {
    return this.http
      .post(API_URL + '/persons', person)
      .map( data => data)
      .do(data => console.log('Persons Received'))
      .catch(this.handleError);
  }

  // API: GET /persons/:id
  // will use this.http.get()
  getPersonById(personId: number): Observable<any> {
    return this.http
      .get(API_URL + '/persons/' + personId)
      .map( data => data)
      .do(data => console.log('Persons Received'))
      .catch(this.handleError);
  }

  // API: PUT /persons/:id
  // will use this.http.put()
  updatePerson(person: Person): Observable<any> {
    return this.http
      .put(API_URL + '/persons/' + person.id, person)
      .map( data => data)
      .do(data => console.log('Persons Received'))
      .catch(this.handleError);
  }

  // DELETE /persons/:id
  // will use this.http.delete()
  deletePersonById(personId: number): Observable<null> {
    return this.http
      .delete(API_URL + '/persons/' + personId)
      .map(response => null)
      .catch(this.handleError);
  }

  private handleError(err: HttpErrorResponse): ErrorObservable {
    console.error( err.message );
    return Observable.throw(err.message);
  }
}
