import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {University} from "../model/university.model";

export const UNIVERSITY_API = 'http://localhost:8080/api/universities';

@Injectable()
export class UniversityService {

  constructor(private httpClient: HttpClient) {
  }

  getUniversities() {
    return this.httpClient.get<University[]>(UNIVERSITY_API, {withCredentials: true});
  }

  getUniversityById(id: string) {
    const url = UNIVERSITY_API + '/' + id;
    return this.httpClient.get<University>(url, {withCredentials: true});
  }

  deleteUniversity(id: string) {
    const url = UNIVERSITY_API + '/' + id;
    return this.httpClient.delete(url, {withCredentials: true, responseType: "text"});
  }

  saveUniversity(university: University) {
    return this.httpClient.post(UNIVERSITY_API, university, {withCredentials: true, responseType: "text"});
  }
}
