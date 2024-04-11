import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private apiUrl = environment.baseUrl; 
  private accessToken: string = '';

  constructor(private http: HttpClient) { }

  login(userData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}auth/authenticate`, userData);
  }

  setAccessToken(token: string): void {
    this.accessToken = token;
  }

  getUserDataByToken(): Observable<any> {
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);
    return this.http.get<any>(`${this.apiUrl}users/getDataByToken`, {headers: headers });
  }
  register(userData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}auth/register`, userData);
  }
  changePassword(data): Observable<any> {
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);
    return this.http.patch<any>(`${this.apiUrl}users/change-password`, data,{headers: headers });
  }
}
