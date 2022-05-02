import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { LogInComponent } from 'angular-auth-lib';
import { LoginComponent } from './login/login.component';
import { ChannelListComponent } from './channel-list/channel-list.component';
import { HomeComponent } from './home/home.component';


const routes: Routes = [{path: '', component: LoginComponent},{path: 'home', component: HomeComponent },{path: '**', redirectTo: 'home'}]; // sets up routes constant where you define your routes

// configures NgModule imports and exports
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
