import {Component, OnInit} from '@angular/core';
import {SubjectService} from "../../service/subject.service";
import {Subject} from "../../model/subject.model";
import {AuthService} from "../../service/auth/auth.service";
import {PersonService} from "../../service/person.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.scss']
})
export class SubjectListComponent implements OnInit {

  subjects: Subject[] = [];

  constructor(private subjectService: SubjectService,
              private personService: PersonService,
              public authService: AuthService,
              private router: Router) {
  }

  ngOnInit() {
    this.initSubjects();
  }

  initSubjects() {
    this.subjectService.getSubjects().subscribe(
      data => {
        this.subjects = data;
      },
      error => {
        console.log('Error occured');
        console.log(error);
      }
    )
  }

  addSubjectToPerson(subject: Subject) {
    if (!this.isSubjectConnectedToPerson(subject)) {
      this.authService.getLoggedUser().person.subjects.push(subject);
      this.personService.save(this.authService.getLoggedUser().person).subscribe(
        data => {
          console.log('Person updated');
          console.log(data);
          this.initSubjects();
        },
        error => {
          console.log('Error occurred:');
          console.log(error);
          alert('Error occurred');
        }
      );
    }
  }

  isSubjectConnectedToPerson(newSubject: Subject) {
    let addedAlready = false;
    this.authService.getLoggedUser().person.subjects.forEach(personSubject => {
      if (newSubject.id === personSubject.id) {
        addedAlready = true;
      }
    });
    return addedAlready;
  }

  editSubject(id: string) {
    this.router.navigate(['subjects/subject-form/' + id]);
  }

  removeSubject(id: string) {
    if (confirm('Are you sure?')) {
      this.subjectService.deleteSubject(id).subscribe(
        (data) => {
          console.log(data);
          this.initSubjects();
        },
        error => {
          console.log('Error occurred');
          console.log(error);
          alert('Error occurred');
        }
      );
    }
  }

  addSubject() {
    this.router.navigate(['subjects/subject-form/new']);
  }
}
