import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';

import { RegisterComponent } from './components/register/register.component';
import { LogoutComponent } from './components/logout/logout.component';
import { NavbarComponent } from './components/navbar/navbar.component';

import { NotFoundComponent } from './components/not-found/not-found.component';
import { LoginComponent } from './components/login/login.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegisterComponent,
    LogoutComponent,
    NavbarComponent,
    NotFoundComponent,
    LoginComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
