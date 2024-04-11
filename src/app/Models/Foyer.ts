import { Bloc } from "./Bloc";

export class Foyer {
  idFoyer?: number;
  nomFoyer?: string;
  capaciteFoyer?: number;
  archived?: boolean;
  blocs : Bloc[] = [];
}
