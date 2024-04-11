// bloc.model.ts

import { Chambre } from "./Chambre";
export class Bloc {
  idBloc: number;
  nomBloc: string;
  capaciteBloc: number;
  //foyer: Foyer; // Assurez-vous d'importer correctement la classe Foyer
  chambre: Chambre[]; // Assurez-vous d'importer correctement la classe Chambre

  constructor(
    idBloc: number,
    nomBloc: string,
    capaciteBloc: number,
    //foyer: Foyer,
    chambre: Chambre[]
  ) {
    this.idBloc = idBloc;
    this.nomBloc = nomBloc;
    this.capaciteBloc = capaciteBloc;
    //this.foyer = foyer;
    this.chambre = chambre;
  }
}
