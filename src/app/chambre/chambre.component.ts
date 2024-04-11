import { Component, OnInit } from '@angular/core';
import { Chambre } from '../classes/Chambre';
//import { ChambreserviceService } from '../services/chambreservice.service';
import { ChambreserviceService } from '../Services/chambreservice.service';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-chambre',
  templateUrl: './chambre.component.html',
  styleUrls: ['./chambre.component.css']
})
export class ChambreComponent implements OnInit {

  chambres: Chambre[] = [];
  accessToken:string ="";

  constructor(private chambreservice: ChambreserviceService ,  private router: Router , private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getChambres();  
  }

  getChambres() {
    this.accessToken = localStorage.getItem('access_token') ;

    this.chambreservice.setAccessToken(this.accessToken)
    this.chambreservice.getChambres().subscribe(
      (data) => {
        this.chambres = data;
      },
      (error) => {
        console.error('Une erreur s\'est produite lors de la récupération des chambres:', error);
      }
    );
  }

  redirigerVersformulaireModification(idChambre:number) {
    this.router.navigate(['chambres/updatechambres' , idChambre]  );
   }
  redirigerVersformulairedajout(){
    this.router.navigate(['chambres/addchambre']);
  }

  onDeleteChambre(idChambre: number, index: number) {
    // Demander confirmation avant de supprimer
    const confirmation = confirm('Êtes-vous sûr de vouloir supprimer cette chambre ?');

    if (confirmation) {
      this.chambreservice.deleteChambre(idChambre).subscribe(
        (response) => {
          console.log('Chambre supprimée avec succès : ', response);
          // Supprimer la ligne du tableau après la confirmation
          this.chambres.splice(index, 1);
        },
        (error) => {
          console.error('Erreur lors de la suppression de la chambre : ', error);
          // Gérez les erreurs ici
        }
      );
    }
  }

}
