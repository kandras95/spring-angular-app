import {Component, OnInit} from '@angular/core';
import {PersonService} from "../../service/person.service";
import {Person} from "../../model/person.model";

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.scss']
})
export class PersonListComponent implements OnInit {

  persons: Person[] = [];

  constructor(private personService: PersonService) {
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
}
