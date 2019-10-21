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

  getSubjectById(id: string) {
    const url = SUBJECT_API + '/' + id;
    return this.httpClient.get<Subject>(url, {withCredentials: true});
  }

  deleteSubject(id: string) {
    const url = SUBJECT_API + '/' + id;
    return this.httpClient.delete(url, {withCredentials: true, responseType: "text"});
  }

  saveSubject(subject: Subject) {
    return this.httpClient.post(SUBJECT_API, subject, {withCredentials: true, responseType: "text"});
  }
}
