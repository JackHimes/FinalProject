import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Comment } from '../models/comment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private url = environment.baseUrl +'api/comments';

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

getHttpOptions() {
  let options = {
    headers: {
      Authorization: 'Basic ' + this.auth.getCredentials(),
      'X-Requested-With': 'XMLHttpRequest',
    },
  };
  return options;
}

show(id: number): Observable<Comment> {
  return this.http.get<Comment>(this.url + '/' + id, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError (
        () => new Error('CommentService.show(): error retreiving comment: ' + err)
      );
    })
  );
}

index(): Observable<Comment> {
  return this.http.get<Comment>(this.url, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () =>
          new Error('CommentSvc.index(): error retreiving comment: ' + err)
      );
    })
  )
}

create(comment: Comment, gameEventId: number): Observable<Comment> {
  return this.http.post<Comment>(environment.baseUrl + `api/gameevents/${gameEventId}/comments`, comment, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () =>
          new Error('CommentService.create(): error creating comment: ' + err)
      );
    })
  )
}

update(comment: Comment, gameEventId: number): Observable<Comment> {
  return this.http.put<Comment>(environment.baseUrl + `api/gameevents/${gameEventId}/comments/${comment.id}`, comment, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('CommentService.update(): error updating comment: ' + err)
      );
    })
  );
}

destroy(commentId: number, gameEventId: number): Observable<void> {
  return this.http.delete<void>(environment.baseUrl + `api/gameevents/${gameEventId}/comments/${commentId}`, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('CommentService.destroy(): error destroying comment: ' + err)
      );
    })
  )
}

}
