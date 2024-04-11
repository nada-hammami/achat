import { Etat } from "./Etat";
import { Foyer } from "./Foyer";
export class Bloc {
  idBloc?: number;
  nomBloc?: string;
  capaciteBloc?: number;
  etatbloc?: Etat; // Make sure to define the Etat enum if it exists
  foyer?: Foyer;
}
