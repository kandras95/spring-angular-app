import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Person} from "../model/person.model";

export const PERSON_API = 'http://localhost:8080/api/persons';

@Injectable()
export class PersonService {

  constructor(private httpClient: HttpClient) {
  }

  getPersons() {
    return this.httpClient.get<Person[]>(PERSON_API, {withCredentials: true});
  }

  save(person: Person) {
    return this.httpClient.post<Person>(PERSON_API, person, {withCredentials: true});
  }
}
