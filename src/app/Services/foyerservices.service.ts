import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Foyer } from '../Models/Foyer';
import { environment } from 'src/environments/environment';
import { Bloc } from '../Models/Bloc';

@Injectable({
  providedIn: 'root'
})
export class FoyerservicesService {
accessToken ="";

  constructor(private http: HttpClient) { }
 accountUrl = "http://localhost:8089/api/";
    public getFoyers(): Observable<Foyer[]> {
    console.log("service appelé");
    const headers = new HttpHeaders().set("Authorization", "Bearer" + this.accessToken);
      console.log({"token":this.accessToken})
    return this.http.get<Foyer[]>(this.accountUrl + "retrieve-all-foyer",{headers});
  }
  
  setAccessToken(token: string): void {
    this.accessToken = token;
  }

  public addFoyer(foyer: any) {
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this.http.post(this.accountUrl + "admin/foyer/add-foyer", foyer,{headers});
  }
   deleteFoyer(id: any) {
    const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this.http.delete(this.accountUrl + "admin/foyer/delete-foyer/" + id,{headers});
  }
getFoyerById(id: number): Observable<Foyer> {
  const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

  return this.http.get<Foyer>(this.accountUrl + "admin/foyer/retrieve-foyer/" + id,{headers});
}

updateFoyer(foyerId: number, foyer: Foyer): Observable<Foyer> {
  const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);
  console.log("update"+foyerId)
  return this.http.put<Foyer>(
    this.accountUrl + "admin/modifier-foyer/" + foyerId,
    foyer,
    { headers }
  );
}
 getBlocksByFoyer(foyerId: number): Observable<Bloc[]> {

  const headers = new HttpHeaders().set("Authorization", "Bearer " + this.accessToken);

    return this.http.get<Bloc[]>(this.accountUrl+"foyer/"+foyerId+"/blocks",{headers});
  }

}
