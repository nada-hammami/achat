import { Component, OnInit } from '@angular/core';
import { Chambre } from 'src/app/classes/Chambre';
import { TypeChambre } from 'src/app/classes/TypeChambre';
import { ChambreserviceService } from 'src/app/Services/chambreservice.service';

@Component({
  selector: 'app-addchambre',
  templateUrl: './addchambre.component.html',
  styleUrls: ['./addchambre.component.css']
})
export class AddchambreComponent implements OnInit {
  chambre: Chambre = new Chambre(0, 0, TypeChambre.SIMPLE); // Initialisez la chambre avec des valeurs par défaut
  typesChambre = Object.values(TypeChambre); 
  constructor(private chambreservise : ChambreserviceService) { }

  ngOnInit(): void {
  }
  onSubmit() {
    console.log(this.chambre);
    
    this.chambreservise.addChambre(this.chambre).subscribe(
      (response) => {
        console.log('Chambre ajoutée avec succès : ', response);
        // Réinitialisez le formulaire ou effectuez d'autres actions après l'ajout réussi
        this.chambre = new Chambre(0, 0, TypeChambre.SIMPLE);
      },
      (error) => {
        console.error('Erreur lors de l\'ajout de la chambre : ', error);
        // Gérez les erreurs ici
      }
    );
  }

}
