import {Component, OnInit} from '@angular/core';
import {Subject} from "../../model/subject.model";
import {ActivatedRoute, Router} from "@angular/router";
import {SubjectService} from "../../service/subject.service";
import {Person} from "../../model/person.model";

@Component({
  selector: 'app-subject-form',
  templateUrl: './subject-form.component.html',
  styleUrls: ['./subject-form.component.scss']
})
export class SubjectFormComponent implements OnInit {

  subject = new Subject();
  persons: Person[] = [];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private subjectService: SubjectService) {
  }

  ngOnInit() {
    this.route.params.subscribe(
      value => {
        const id = value['id'];
        if (id !== 'new') {
          this.findSubjectById(id);
        }
      },
      error => {
        console.dir('Error occurred: ');
        console.log(error.error.message);
        this.backToSubjects();
      }
    );
  }

  private findSubjectById(id: string) {
    this.subjectService.getSubjectById(id).subscribe(
      data => {
        this.subject = data;
        this.loadPersonsForSubject();
      },
      error => {
        console.log('Error occured');
        console.log(error);
        this.backToSubjects();
      }
    );
  }

  backToSubjects() {
    this.router.navigate(['/subjects']);
  }

  saveSubject() {
    this.subjectService.saveSubject(this.subject).subscribe(
      (data) => {
        console.log(data);
        this.backToSubjects();
      },
      error => {
        console.log('Error occured');
        console.log(error);
      }
    );
  }

  loadPersonsForSubject() {
    this.subjectService.loadPersonsForSubject(this.subject.id).subscribe(
      (data) => {
        console.log(data);
        this.persons = data;
      },
      error => {
        console.log('Error occured');
        console.log(error);
      }
    );
  }
}
