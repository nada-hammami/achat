import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { HttpClient ,HttpHeaders} from '@angular/common/http';
import { Observable, catchError, tap } from 'rxjs';
import { Chambre } from '../classes/Chambre';
@Injectable({
  providedIn: 'root'
})
export class ChambreserviceService {
  private accessToken: string = '';

  private apiUrl = environment.baseUrl; // Remplacez cela par votre URL d'API

  constructor(private _http: HttpClient) {}

  setAccessToken(token: string): void {
    this.accessToken = token;
  }
  getChambres(): Observable<Chambre[]> {
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this._http.get<Chambre[]>(this.apiUrl + 'chambre/get-all-chambres',{headers});
    
  }
  updateChambre(chambre: Chambre): Observable<Chambre> {
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this._http.put<Chambre>(this.apiUrl + 'chambre/update-chambre', chambre,{headers});
  }

  //getChambreById(idChambre)
  getChambreById(idChambre: number): Observable<Chambre> {
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this._http.get<Chambre>(this.apiUrl +`chambre/retrieve-chambre/${idChambre}`,{headers});
  }
  //addChambre
  addChambre(chambre: Chambre): Observable<Chambre> {
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this._http.post<Chambre>(this.apiUrl + 'chambre/add-chambre', chambre,{headers});
  }
  deleteChambre(idChambre : number) : Observable<Chambre>{
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this._http.delete<Chambre>(this.apiUrl + `chambre/delete-chambre/${idChambre}`,{headers});
  }
  /*deleteChambre(idChambre: number): Observable<any> {
    const url = `${this.apiUrl}removechambre/${idChambre}`;
    return this._http.delete(url).pipe(
      tap(response => console.log('Réponse du service après suppression : ', response)),
      catchError(error => {
        console.error('Erreur lors de la suppression de la chambre : ', error);
        throw error; // Rethrow l'erreur pour la gérer dans le composant
      })
    );
  }*/
  afficherparnombloc(nomBloc : String):Observable<Chambre>{
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this._http.get<Chambre>(this.apiUrl + `getchparbloc/${nomBloc}`,{headers})
  }
  

}

