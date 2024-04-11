import { reservation } from "./reservation"

export class etudiant {
    idEtudiant !: number
    nomEt!: string
    prenomEt!: string
    cin!: number
    ecole!: string
    email!: string
    dateNaissance!: Date
    reservations!: reservation[]

}