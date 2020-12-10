import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListeTodoComponent } from './liste-todo/liste-todo.component';

@NgModule({
  declarations: [
    AppComponent,
    ListeTodoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent,ListeTodoComponent]
})
export class AppModule { }
