import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Address } from '../models/address';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AddressService {
private url = environment.baseUrl +'api/addressess';

constructor(
  private http: HttpClient,
  private auth: AuthService
) {}

getHttpOptions() {
  let options = {
    headers: {
      Authorization: 'Basic ' + this.auth.getCredentials(),
      'X-Requested-With': 'XMLHttpRequest',
    },
  };
  return options;
}

show(id: number): Observable<Address> {
  return this.http.get<Address>(this.url + '/' + id, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError (
        () => new Error('AddressService.show(): error retreiving address: ' + err)
      );
    })
  );
}

index(): Observable<Address> {
  return this.http.get<Address>(this.url, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () =>
          new Error('AddressService.index(): error retreiving address: ' + err)
      );
    })
  )
}

createHomeAddress(address: Address): Observable<Address> {
  return this.http.post<Address>(this.url + '/home', address, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () =>
          new Error('AddressService.create(): error creating home address: ' + err)
      );
    })
  )
}

createFavoriteVenueAddress(address: Address): Observable<Address> {
  return this.http.post<Address>(this.url, address, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () =>
          new Error('AddressService.create(): error creating favorite address: ' + err)
      );
    })
  )
}

update(address: Address, id: number): Observable<Address> {
  return this.http.put<Address>(this.url + '/' + id, address, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('AddressService.create(): error updating address: ' + err)
      );
    })
  );
}

destroy(id: number): Observable<void> {
  return this.http.delete<void>(this.url + '/' + id, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('AddressService.create(): error destroying address: ' + err)
      );
    })
  )
}

}
