import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { HttpClient,HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class MailServiceService {
  private accessToken: string = '';

  setAccessToken(token: string): void {
    this.accessToken = token;
  }
  constructor(private _http: HttpClient) { }
  apiUrl: string = environment.baseUrl;

  sendMail(subject: string, message: string) {
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    const payload = { subject, message };
    return this._http.post(this.apiUrl + "/mailer" + "/sendMailToStudentWithValidReservation2", payload,{headers})
    //return this._http.post<etudiant>(this.apiUrl + "etudiant" + "/addetudiant", etudiant)

  }
}
