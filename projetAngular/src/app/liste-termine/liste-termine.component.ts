import { Component, OnInit } from '@angular/core';
import { BugServiceService } from '../bug-service.service';
import { Bug } from '../models/bug';
import { delay } from 'rxjs/operators';


@Component({
  selector: 'app-liste-termine',
  templateUrl: './liste-termine.component.html',
  styleUrls: ['./liste-termine.component.css']
})
export class ListeTermineComponent implements OnInit {

  public bugs: Bug[];

  constructor(private BugService: BugServiceService) { }

  ngOnInit(): void {
    this.BugService.getBugsTermineList()
    .pipe(delay(2000))
    .subscribe((bugsResponse => {
      this.bugs = bugsResponse;
    }));
  }

}


