import { etudiant } from "./etudiant"

export class reservation {
    idReservation!: number
    anneeUniversitaire !: Date
    estValid: boolean = false
    etudiants !: etudiant 
}