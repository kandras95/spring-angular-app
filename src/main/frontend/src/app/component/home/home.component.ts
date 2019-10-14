import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth/auth.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private authService: AuthService) {
  }

  ngOnInit() {
  }

  getLoggedUserName(): string {
    const user = this.authService.getLoggedUser();
    if (user != null) {
      return user.username;
    } else {
      return '';
    }
  }

  getUserRoles() {
    return this.authService.loggedUser != null ? this.authService.loggedUser.roles : '';
  }

}
