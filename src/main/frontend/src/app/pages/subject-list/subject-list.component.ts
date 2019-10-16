import {Component, OnInit} from '@angular/core';
import {SubjectService} from "../../service/subject.service";
import {Subject} from "../../model/subject.model";

@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.scss']
})
export class SubjectListComponent implements OnInit {

  subjects: Subject[] = [];

  constructor(private subjectService: SubjectService) {
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
}
