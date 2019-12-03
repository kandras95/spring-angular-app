import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UniversityService} from "../../service/university.service";
import {University} from "../../model/university.model";

@Component({
  selector: 'app-university-form',
  templateUrl: './university-form.component.html',
  styleUrls: ['./university-form.component.scss']
})
export class UniversityFormComponent implements OnInit {

  university = new University();

  constructor(private route: ActivatedRoute,
              private router: Router,
              private universityService: UniversityService) {
  }

  ngOnInit() {
    this.route.params.subscribe(
      value => {
        const id = value['id'];
        if (id !== 'new') {
          this.findUniversityById(id);
        }
      },
      error => {
        console.dir('Error occurred: ');
        console.log(error.error.message);
        this.backToUniversities();
      }
    );
  }

  private findUniversityById(id: string) {
    this.universityService.getUniversityById(id).subscribe(
      data => {
        this.university = data;
      },
      error => {
        console.log('Error occurred');
        console.log(error);
        this.backToUniversities();
      }
    );
  }

  backToUniversities() {
    this.router.navigate(['/universities']);
  }

  saveUniversity() {
    this.universityService.saveUniversity(this.university).subscribe(
      (data) => {
        console.log(data);
        this.backToUniversities();
      },
      error => {
        console.log('Error occurred');
        console.log(error);
      }
    );
  }

}
