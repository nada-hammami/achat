import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Bloc } from '../classes/Bloc';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class BlocservicesService {
  private apiUrl = environment.baseUrl+'api/'; // Remplacez cela par votre URL d'API
  accessToken:string="";
  constructor(private _http: HttpClient) {}
  setAccessToken(token: string): void {
    this.accessToken = token;
  }
  getBlocs(): Observable<Bloc[]> {
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this._http.get<Bloc[]>(this.apiUrl + 'admin/bloc/retrieve-all-bloc',{headers});
  }
}
