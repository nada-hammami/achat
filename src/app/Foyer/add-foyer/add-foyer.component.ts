import { Component, OnInit } from '@angular/core';
import { FoyerservicesService } from 'src/app/Services/foyerservices.service';
import { Router } from '@angular/router';
import { Foyer } from 'src/app/Models/Foyer';
@Component({
  selector: 'app-add-foyer',
  templateUrl: './add-foyer.component.html',
  styleUrls: ['./add-foyer.component.css']
})
export class AddFoyerComponent implements OnInit {
  ngOnInit(): void {}
  constructor(private foyerservices: FoyerservicesService, private router: Router) { }
  foyer = <Foyer>{};
public saveFoyer(): void {
    this.foyerservices.addFoyer(this.foyer).subscribe({
      next: (data) => {

        this.router.navigate(['/foyers']);  // Mettez à jour avec la route correspondante
      },
      error: (err) => {

        console.error('Erreur lors de l’ajout du foyer:', err);
      }
    });
}
  
  public cancel(): void {
     this.router.navigate(['/foyers']);
  }

  }


