import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./component/home/home.component";
import {PersonListComponent} from "./pages/person-list/person-list.component";
import {LoginComponent} from "./component/login/login.component";
import {LoggedInGuard} from "./service/auth/logged-in-guard.service";
import {SubjectListComponent} from "./pages/subject-list/subject-list.component";
import {SubjectFormComponent} from "./pages/subject-form/subject-form.component";
import {UniversityListComponent} from "./pages/university-list/university-list.component";
import {UniversityFormComponent} from "./pages/university-form/university-form.component";


const routes: Routes = [
  {
    path: '', redirectTo: 'home', pathMatch: 'full'
  },
  {
    path: 'home', component: HomeComponent, pathMatch: 'full',
    canActivate: [LoggedInGuard]
  },
  {
    path: 'persons', component: PersonListComponent,
    canActivate: [LoggedInGuard]
  },
  {
    path: 'subjects', component: SubjectListComponent,
    canActivate: [LoggedInGuard]
  },
  {
    path: 'universities', component: UniversityListComponent,
    canActivate: [LoggedInGuard]
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'subjects/subject-form/:id', component: SubjectFormComponent
  },
  {
    path: 'universities/university-form/:id', component: UniversityFormComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
