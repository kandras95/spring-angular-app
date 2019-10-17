import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth/auth.service";
import {Subject} from "../../model/subject.model";
import {PersonService} from "../../service/person.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(public authService: AuthService,
              private personService: PersonService) {
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

  getSubjects() {
    const user = this.authService.getLoggedUser();
    if (user != null) {
      return user.person.subjects;
    } else {
      return [];
    }
  }

  removeSubjectFromPerson(subjectToRemove: Subject) {
    if (confirm('Are you sure?')) {
      let newList: Subject[] = [];
      this.authService.getLoggedUser().person.subjects.forEach(subject => {
        if (subjectToRemove.id !== subject.id) {
          newList.push(subject);
        }
      });
      this.authService.getLoggedUser().person.subjects = newList;
      this.personService.save(this.authService.getLoggedUser().person).subscribe(
        data => {
          console.log('Person updated');
          console.log(data);
        },
        error => {
          console.log('Error occurred:');
          console.log(error);
          alert('Error occurred');
        }
      );
    }
  }
}
