import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./component/home/home.component";
import {PersonListComponent} from "./pages/person-list/person-list.component";
import {LoginComponent} from "./component/login/login.component";
import {LoggedInGuard} from "./service/auth/logged-in-guard.service";
import {SubjectListComponent} from "./pages/subject-list/subject-list.component";


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
    path: 'login', component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
