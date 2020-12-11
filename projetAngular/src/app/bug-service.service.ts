import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Bug } from './models/bug';
import { Developpeur } from './models/developpeur';
import { Commentaire } from './models/commentaire';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BugServiceService {

  constructor(private http: HttpClient) { }

  public getBugsList(): Observable<Bug[]> {
    return this.http.get<Bug[]>(`http://localhost:5000/bugs`);
  }

  public getBugsToDoList(): Observable<Bug[]> {
    return this.http.get<Bug[]>(`${environment.url}/bugs/todo`);
  }

  public getBugsEnCoursList(): Observable<Bug[]> {
    return this.http.get<Bug[]>(`${environment.url}/bugs/encours`);
  }

  public getBugsTermineList(): Observable<Bug[]> {
    return this.http.get<Bug[]>(`${environment.url}/bugs/termine`);
  }

  public deleteBug(id: number): Observable<any> {
    return this.http.delete(`${environment.url}/bugs/${id}`);
  }
  public createBug(bug : Bug): Observable<Bug> {
    return this.http.post<Bug>(`${environment.url}/bugs`, bug);
  }
}
