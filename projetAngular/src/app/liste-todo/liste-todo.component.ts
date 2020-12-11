import { Component, OnInit } from '@angular/core';
import { BugServiceService } from '../bug-service.service';
import { Bug } from '../models/bug';
import { delay } from 'rxjs/operators';

@Component({
  selector: 'app-liste-todo',
  templateUrl: './liste-todo.component.html',
  styleUrls: ['./liste-todo.component.css']
})
export class ListeTodoComponent implements OnInit {


  public bugs: Bug[];

  constructor(private BugService: BugServiceService) { }

  ngOnInit(): void {
    this.BugService.getBugsList()
    .pipe(delay(2000))
    .subscribe((bugsResponse => {
      this.bugs = bugsResponse;
    }));
  }



}
