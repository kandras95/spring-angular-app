import {Component, OnInit} from '@angular/core';
import {PersonService} from "../../service/person.service";
import {Person} from "../../model/person.model";
import {AuthService} from "../../service/auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.scss']
})
export class PersonListComponent implements OnInit {

  persons: Person[] = [];

  constructor(private personService: PersonService, private router: Router, public authService: AuthService) {
  }

  ngOnInit() {
    this.initPersons();
  }

  initPersons() {
    this.personService.getPersons().subscribe(
      data => {
        this.persons = data;
      },
      error => {
        console.log('Error occurred:');
        console.log(error);
      }
    )
  }

  editPerson(id: string) {
      this.router.navigate(['persons/person-form/' + id]);
    }
}
