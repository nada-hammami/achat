// chambre.model.ts
 //Assurez-vous d'importer correctement l'enum TypeChambre
import { Bloc } from './Bloc';
import { TypeChambre } from './TypeChambre';
export class Chambre {
  idChambre: number;
  numeroChambre: number;
  typeC: TypeChambre;
  //reservations: Reservation[]; // Assurez-vous d'importer correctement la classe Reservation
  bloc: Bloc; // Assurez-vous d'importer correctement la classe Bloc

  constructor(
    idChambre: number,
    numeroChambre: number,
    typeC: TypeChambre,
    //reservations: Reservation[],
    //bloc: Bloc
  ) {
    this.idChambre = idChambre;
    this.numeroChambre = numeroChambre;
    this.typeC = typeC;
    //this.reservations = reservations;
    //his.bloc = bloc;
  }
}
