import { HttpClient, HttpClientModule } from '@angular/common/http';
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

@NgModule({
  declarations: [
    AppComponent,
    ChannelListComponent,
    PackagesListComponent,
    ContracctListComponent,
    ProvidersComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
   NgbModule
   

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
