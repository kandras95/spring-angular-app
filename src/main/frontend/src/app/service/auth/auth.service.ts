import {Injectable} from '@angular/core';

import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';
import {User} from "../../model/user.model";
import {Observable} from "rxjs";

export const AUTH_API = 'http://localhost:8080/api/auth';

@Injectable()
export class AuthService {

  authenticated = false;
  loggedUser: User = new User();

  constructor(private httpClient: HttpClient,
              private router: Router) {
    this.refreshAuthenticatedUser();
  }

  refreshAuthenticatedUser() {
    this.getAuthenticatedUser().subscribe(
      data => {
        this.loggedUser = data;
        this.authenticated = true;
        sessionStorage.setItem('exampleAppLoggedIn', 'true');
      },
      (error: HttpErrorResponse) => {
        console.log('User is not authenticated, error: ' + error.error.message);
        sessionStorage.clear();
        this.authenticated = false;
        this.loggedUser = new User();
        this.router.navigate(['/login'], {});
      }
    );
  }

  isUserLoggedIn(): boolean {
    return sessionStorage.getItem('exampleAppLoggedIn') === 'true' || this.authenticated;
  }

  getLoggedUser(): User {
    return this.loggedUser;
  }

  getAuthenticatedUser(): Observable<any> {
    const URL: string = AUTH_API + '/user-details';
    return this.httpClient.get(URL, {withCredentials: true});
  }

  login(username: string, password: string) {
    const URL: string = AUTH_API + '/login';
    const body = new HttpParams()
      .set('username', username)
      .set('password', password);
    return this.httpClient.post(URL,
      body.toString(),
      {
        headers: new HttpHeaders()
          .set('Content-Type', 'application/x-www-form-urlencoded'),
        responseType: 'text',
        withCredentials: true
      }
    );
  }

  logout() {
    sessionStorage.clear();
    const URL: string = AUTH_API + '/logout';
    return this.httpClient.post(URL, '', {withCredentials: true});
  }
}
