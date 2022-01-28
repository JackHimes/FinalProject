import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Eventtag } from '../models/eventtag';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class EventtagService {

  private url = environment.baseUrl + 'api/eventtags';

  

  constructor(
    private http :  HttpClient,
    private auth : AuthService,
  ) { }


  getHttpOptions(){
    let options = {
      headers: {
        
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      }
    };
    return options;
  }

  index(): Observable<Eventtag[]> {
    return this.http.get<Eventtag[]>(this.url, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Failed to call EventTag index()')
        )
      })
    );

  }

  public create(eventtag : Eventtag): Observable<Eventtag>{

    return this.http.post<Eventtag>(this.url, eventtag, this.getHttpOptions())
      .pipe(
        catchError((err : any) => {
          console.log(err);
          return throwError(
            () => new Error('Create(todo) eventtag method error')
          )
        })
      );
  }

  public destroy(id : number): Observable<void>{
    return this.http.delete<void>(this.url + "/" + id, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Destroy(id) eventtag method error')
        )
      })
    );
  
}

public update(eventtag : Eventtag): Observable<Eventtag>{
  return this.http.put<Eventtag>(this.url + "/" +  eventtag.id, eventtag, this.getHttpOptions())
  .pipe(
    catchError((err : any) => {
      console.log(err);
      return throwError(
        () => new Error('Update(todo) eventtag method error: ' + err)
      )
    })
  );
}
public show(id: number): Observable<Eventtag>{
  return this.http.get<Eventtag>(this.url + "/" + id, this.getHttpOptions())
  .pipe(
    catchError((err: any) => {
      console.error(err);
      return throwError(
        () => new Error("show(id) method for eventtag: " + id + " error. " + err)
      )
    })
  )
} 

}
