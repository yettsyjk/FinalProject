import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/register/register.component';
import { NotFoundComponent } from './components/not-found/not-found.component';

import { ContactComponent } from './components/contact/contact.component';

import { SnitchListComponent } from './components/snitch-list/snitch-list.component';
import { AlertListComponent } from './components/alert-list/alert-list.component';
import { LogoutComponent } from './components/logout/logout.component';
import { SnitchComponent } from './components/snitch/snitch.component';
<<<<<<< HEAD
import { AdminComponent } from './components/admin/admin.component';
=======
import { SnitchUpdateComponent } from './components/snitch-update/snitch-update.component';
>>>>>>> 71c3a767b452c1162c043f3df827dd8ef1101c1c



const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'snitch', component: SnitchComponent },
  { path: 'snitchUpdate', component: SnitchUpdateComponent },

  { path: 'contact', component: ContactComponent },
  { path: 'snitches', component: SnitchListComponent },
  { path: 'alerts', component: AlertListComponent},

  // { path: 'snitches/:id', component: SnitchListComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: '**', component: NotFoundComponent }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
