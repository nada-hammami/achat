import { Component, OnInit } from '@angular/core';
import {Bloc} from "../../Models/Bloc";
import {FoyerservicesService} from "../../Services/foyerservices.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Foyer} from "../../Models/Foyer";

@Component({
  selector: 'app-afficherblocs',
  templateUrl: './afficherblocs.component.html',
  styleUrls: ['./afficherblocs.component.css']
})
export class AfficherblocsComponent implements OnInit {

  blocs: Bloc[]; // This should be an array if you are expecting multiple blocs
  foyerId: number; // The ID will be provided via URL

  constructor(
    private foyerService: FoyerservicesService,
    private ac: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.ac.params.subscribe(params => {
      this.foyerId = +params['foyerId']; // Retrieve the 'foyerId' from the URL route parameters

      // Now call your service to get the blocs for our foyer
      this.getBlocsForFoyer();
    });
  }

  getBlocsForFoyer(): void {
    if (this.foyerId) {
      this.foyerService.getBlocksByFoyer(this.foyerId).subscribe(
        (data: Bloc[]) => {
          this.blocs = data;
        },
        error => {
          console.error('Erreur lors de la récupération des blocs:', error);
          // Optional: navigate back to foyers if there's an error
          this.router.navigate(['/foyers']);
        }
      );
    }
  }}
