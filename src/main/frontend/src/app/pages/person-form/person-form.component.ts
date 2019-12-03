import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {PersonService} from "../../service/person.service";
import {Person} from "../../model/person.model";

@Component({
  selector: 'app-person-form',
  templateUrl: './person-form.component.html',
  styleUrls: ['./person-form.component.scss']
})
export class PersonFormComponent implements OnInit {

  person = new Person();

  constructor(private route: ActivatedRoute,
              private router: Router,
              private personService: PersonService) {
  }

  ngOnInit() {
    this.route.params.subscribe(
      value => {
        const id = value['id'];
        if (id !== 'new') {
          this.findPersonById(id);
        }
      },
      error => {
        console.dir('Error occurred: ');
        console.log(error.error.message);
        this.backToPersons();
      }
    );
  }

  private findPersonById(id: string) {
    this.personService.getPersonById(id).subscribe(
      data => {
        this.person = data;
      },
      error => {
        console.log('Error occured');
        console.log(error);
        this.backToPersons();
      }
    );
  }

  backToPersons() {
    this.router.navigate(['/persons']);
  }

  savePerson() {
    this.personService.save(this.person).subscribe(
      (data) => {
        console.log(data);
        this.backToPersons();
      },
      error => {
        console.log('Error occured');
        console.log(error);
      }
    );
  }
}
