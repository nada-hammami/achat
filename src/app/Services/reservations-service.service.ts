import { Injectable } from '@angular/core';
import { HttpClient ,HttpHeaders} from '@angular/common/http';
import { environment } from 'src/environments/environment.prod';
import { reservation } from '../Models/reservation';
import { etudiant } from '../Models/etudiant';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ReservationsServiceService {
  private accessToken: string = '';

  constructor(private _http: HttpClient) { }
  apiUrl: string = environment.baseUrl;
  getAllReservations() {
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this._http.get<reservation[]>(this.apiUrl + "reservation" + "/retrieveReservations",{headers})
    //return this._http.get<etudiant[]>(this.apiUrl + "etudiant" + "/retrieve-all-etudiant")
  }
  getAllStudents() {
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this._http.get<etudiant[]>(this.apiUrl + "etudiant" + "/retrieve-all-etudiant",{headers})
  }
  
  setAccessToken(token: string): void {
    this.accessToken = token;
  }
  findStudentWithEmail(email: string){
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this._http.get<etudiant>(this.apiUrl+"etudiant"+"/findEtudiantwithemail/"+email,{headers})
  }

  validerReservation(idReservation: number){
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this._http.get(this.apiUrl+"reservation"+"/validerReservation/"+idReservation,{headers})
  }
  statistiquesReservation(){
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this._http.get(this.apiUrl+"reservation"+"/statistiques",{headers})
  }
}
