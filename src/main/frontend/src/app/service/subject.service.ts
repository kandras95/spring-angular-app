import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Subject} from "../model/subject.model";

export const SUBJECT_API = 'http://localhost:8080/api/subjects';

@Injectable()
export class SubjectService {

  constructor(private httpClient: HttpClient) {
  }

  getSubjects() {
    return this.httpClient.get<Subject[]>(SUBJECT_API, {withCredentials: true});
  }
}
