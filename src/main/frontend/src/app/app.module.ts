import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {PersonListComponent} from './pages/person-list/person-list.component';
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

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PersonListComponent,
    SubjectListComponent,
    SubjectFormComponent,
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
    AuthService,
    LoggedInGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
