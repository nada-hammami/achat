import { Component, OnInit } from '@angular/core';
import { BlocservicesService } from '../Services/blocservices.service';
import { Bloc } from '../classes/Bloc';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bloc',
  templateUrl: './bloc.component.html',
  styleUrls: ['./bloc.component.css']
})
export class BlocComponent implements OnInit {
  blocs:Bloc[] = [];

  constructor(private blocservice:BlocservicesService , private router: Router) { }

  ngOnInit(): void {
    this.getBlocs();
  }
  getBlocs(){
    this.blocservice.getBlocs().subscribe(
      (data) => {
        this.blocs = data;
      },
      (error) => {
        console.error('Une erreur s\'est produite lors de la récupération des blocs:', error);
      }
    );}
    redirigerVersChambres() {
      // Utilisez la méthode navigate pour rediriger vers ChambresComponent
      this.router.navigate(['/chambres']);
    }

}
