import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';

import { RegisterComponent } from './components/register/register.component';
import { LogoutComponent } from './components/logout/logout.component';
import { NavbarComponent } from './components/navbar/navbar.component';

import { NotFoundComponent } from './components/not-found/not-found.component';
import { LoginComponent } from './components/login/login.component';
import { AuthService } from './services/auth.service';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule} from '@ng-bootstrap/ng-bootstrap';


import { AlertListComponent } from './components/alert-list/alert-list.component';
import { FooterComponent } from './components/footer/footer.component';
import { ContactComponent } from './components/contact/contact.component';
import { SnitchListComponent } from './components/snitch-list/snitch-list.component';
import { AlertService } from './services/alert.service';
import { SnitchComponent } from './components/snitch/snitch.component';
import { SnitchPersonalComponent } from './components/snitch-personal/snitch-personal.component';
import { UserService } from './services/user.service';
import { SnitchService } from './services/snitch.service';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegisterComponent,
    LogoutComponent,
    NavbarComponent,
    NotFoundComponent,
    LoginComponent,
    AlertListComponent,
    FooterComponent,
    ContactComponent,
    SnitchListComponent,
    SnitchComponent,
    SnitchPersonalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
  ],
  providers: [
    AuthService,
    AlertService,
    UserService,
    SnitchService

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
