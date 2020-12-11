import { Component, OnInit } from '@angular/core';
import { BugServiceService } from '../bug-service.service';
import { Bug } from '../models/bug';
import { delay } from 'rxjs/operators';

@Component({
  selector: 'app-liste-encours',
  templateUrl: './liste-encours.component.html',
  styleUrls: ['./liste-encours.component.css']
})
export class ListeEncoursComponent implements OnInit {

  public bugs: Bug[];

  constructor(private BugService: BugServiceService) { }

  ngOnInit(): void {
    this.BugService.getBugsEnCoursList()
    .pipe(delay(2000))
    .subscribe((bugsResponse => {
      this.bugs = bugsResponse;
    }));
  }

}
