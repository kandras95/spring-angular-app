import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {PersonListComponent} from './pages/person-list/person-list.component';
import {PersonFormComponent} from './pages/person-form/person-form.component';
import {PersonService} from "./service/person.service";
import {HttpClientModule} from "@angular/common/http";
import {HeaderComponent} from './component/header/header.component';
import {HomeComponent} from './component/home/home.component';
import {AuthService} from "./service/auth/auth.service";
import {LoggedInGuard} from "./service/auth/logged-in-guard.service";
import {LoginComponent} from "./component/login/login.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {SubjectListComponent} from './pages/subject-list/subject-list.component';
import {SubjectService} from "./service/subject.service";
import {SubjectFormComponent} from "./pages/subject-form/subject-form.component";
import {UniversityListComponent} from './pages/university-list/university-list.component';
import {UniversityService} from "./service/university.service";
import {UniversityFormComponent} from "./pages/university-form/university-form.component";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PersonListComponent,
    PersonFormComponent,
    SubjectListComponent,
    SubjectFormComponent,
    UniversityListComponent,
    UniversityFormComponent,
    HeaderComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    PersonService,
    SubjectService,
    UniversityService,
    AuthService,
    LoggedInGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
