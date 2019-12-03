import {Component, OnInit} from '@angular/core';
import {UniversityService} from "../../service/university.service";
import {University} from "../../model/university.model";
import {AuthService} from "../../service/auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-university-list',
  templateUrl: './university-list.component.html',
  styleUrls: ['./university-list.component.scss']
})
export class UniversityListComponent implements OnInit {

  universities: University[] = [];

  constructor(private universityService: UniversityService,
              public authService: AuthService,
              private router: Router) {
  }

  ngOnInit() {
    this.initUniversities();
  }

  initUniversities() {
    this.universityService.getUniversities().subscribe(
      data => {
        this.universities = data;
      },
      error => {
        console.log('Error occured');
        console.log(error);
      }
    )
  }

  editUniversity(id: string) {
    this.router.navigate(['universities/university-form/' + id]);
  }

  removeUniversity(id: string) {
    if (confirm('Are you sure?')) {
      this.universityService.deleteUniversity(id).subscribe(
        (data) => {
          console.log(data);
          this.initUniversities();
        },
        error => {
          console.log('Error occurred');
          console.log(error);
          alert('Error occurred');
        }
      );
    }
  }

  addUniversity() {
    this.router.navigate(['universities/university-form/new']);
  }
}
