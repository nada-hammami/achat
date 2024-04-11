package tn.esprit.com.foyer.services;

import tn.esprit.com.foyer.entities.Etudiant;

import java.util.List;

public interface IEtudiantService {

    List<Etudiant> retrieveAllEtudiants();

    Etudiant addEtudiant(Etudiant e);

    Etudiant updateEtudiant(Etudiant e);

    Etudiant retrieveEtudiant(Long idEtudiant);

    void removeEtudiant(Long idEtudiant);

    Etudiant affecterEtudiantAReservation (String nomEt, String prenomEt, Long idReservation) ;



    public Etudiant findEtudiantwithemail(String email);


}

