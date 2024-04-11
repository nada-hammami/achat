import { Component, OnInit } from '@angular/core';
import { FoyerservicesService } from 'src/app/services/foyerservices.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Foyer } from 'src/app/Models/Foyer';
@Component({
  selector: 'app-modifier-foyer',
  templateUrl: './modifier-foyer.component.html',
  styleUrls: ['./modifier-foyer.component.css']
})
export class ModifierFoyerComponent implements OnInit {
foyer: Foyer = <Foyer>{}; // Initial empty object for the Foyer
  id: number; // Assumed to be a number. Adjust if your ID is a different type.

  constructor(
    private foyerService: FoyerservicesService,
    private ac: ActivatedRoute,
    private router: Router
  ) {}

  getFoyerById() {
    this.foyerService.getFoyerById(this.id).subscribe((res) => {
      this.foyer = res;
    });
  }

  modifierFoyer():void {
    this.foyerService.updateFoyer(this.id, this.foyer).subscribe({
      next: (data) => {

        this.router.navigate(['/foyers']);  // Mettez à jour avec la route correspondante
      },
      error: (err) => {

        console.error('Erreur lors de l’ajout du foyer:', err);
      }
    });
}



  ngOnInit(): void {
     this.id = this.ac.snapshot.params["foyer-id"];
    this.getFoyerById();
    console.log(this.id);

  }

  }



