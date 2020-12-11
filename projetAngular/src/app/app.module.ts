import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListeTodoComponent } from './liste-todo/liste-todo.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ListeEncoursComponent } from './liste-encours/liste-encours.component';
import { ListeTermineComponent } from './liste-termine/liste-termine.component';
@NgModule({
  declarations: [
    AppComponent,
    ListeTodoComponent,
    ListeEncoursComponent,
    ListeTermineComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
