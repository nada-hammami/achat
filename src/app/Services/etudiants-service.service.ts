import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { etudiant } from '../Models/etudiant';
import { reservation } from '../Models/reservation';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class EtudiantsServiceService {

  constructor(private _http: HttpClient) { }

  apiUrl: string = environment.baseUrl;

  private accessToken: string = '';

  getAllStudents() {
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this._http.get<etudiant[]>(this.apiUrl  + "retrieve-all-etudiant",{headers})
  }

  

  setAccessToken(token: string): void {
    this.accessToken = token;
  }
  updateStudent(etudiant: etudiant) {
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this._http.put<etudiant>(this.apiUrl + "updateetudiant", etudiant,{headers})
  }

  removeStudent(idEtudiant: number) {
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this._http.delete<etudiant>(this.apiUrl  + "remouve-etudiant/"+idEtudiant,{headers})
  }
  
  findStudent(idEtudiant: number){
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this._http.get<etudiant>(this.apiUrl+ "retrieve-etudiant/"+idEtudiant,{headers})
  }

  findStudentWithEmail(email: string): Observable<any>{
    
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this._http.get<etudiant>(this.apiUrl+"findEtudiantwithemail/"+email,{headers})
  }
  passReservation(idEtudiant: number, res:reservation){
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    //long idEtudiant , Reservation res
    return this._http.post<reservation>(this.apiUrl+"passerUneReservation/"+idEtudiant, res,{headers})
  }
  

}
