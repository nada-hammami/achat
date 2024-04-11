import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ChambreComponent } from '../chambre.component';
import { TypeChambre } from 'src/app/classes/TypeChambre';
import { ChambreserviceService } from 'src/app/Services/chambreservice.service';
import { Chambre } from 'src/app/classes/Chambre';

@Component({
  selector: 'app-updatechambre',
  templateUrl: './updatechambre.component.html',
  styleUrls: ['./updatechambre.component.css']
})
export class UpdateChambreComponent implements OnInit {
  chambre : any = {};
  typesChambre = Object.keys(TypeChambre); // Obtenez les valeurs de l'énumération TypeChambre
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private chambreService: ChambreserviceService
  ) {}
  ngOnInit() {
    // Récupérer l'ID de la chambre depuis la route
    this.route.params.subscribe(params => {
      const idChambre = +params['idChambre']; // '+': convertit le paramètre en nombre
  
      // Utiliser l'ID pour charger les données de la chambre
      this.chambreService.getChambreById(idChambre).subscribe(
        (chambre) => {
          console.log(this.typesChambre)
          // Charger les données de la chambre dans le formulaire
          this.chambre = chambre;
        },
        (error) => {
          console.error('Erreur lors du chargement de la chambre : ', error);
          // Gérez les erreurs ici
        }
      );
    });}
    onSubmit() {
    this.chambreService.updateChambre(this.chambre).subscribe(
      (response) => {
        console.log('Chambre mise à jour avec succès : ', response);
        // Redirigez l'utilisateur vers la liste des chambres après la mise à jour
        this.router.navigate(['/chambres']);
      },
      (error) => {
        console.error('Erreur lors de la mise à jour de la chambre : ', error);
        // Gérez les erreurs ici
      }
    );}
  
}
