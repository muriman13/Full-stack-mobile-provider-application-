import { HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';



import { AppComponent } from './app.component';
import { PackagesListComponent } from './packages-list/packages-list.component';
import { ContracctListComponent } from './contracct-list/contracct-list.component';
import {MatDialogModule} from '@angular/material/dialog';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ChannelListComponent } from './channel-list/channel-list.component';
import { ProvidersComponent } from './providers/providers.component';

import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './login/login.component';
import { HttpInterceptorService } from './HttpInterceptor.service';
import { HomeComponent } from './home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    ChannelListComponent,
    PackagesListComponent,
    ContracctListComponent,
    ProvidersComponent,
    LoginComponent,
    HomeComponent
  ],
  
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
   NgbModule,
   AppRoutingModule,
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: HttpInterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
