package tn.esprit.com.foyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.com.foyer.entities.Etudiant;

@Repository

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    public Etudiant findEtudiantByNomEtAndPrenomEt(String nomEt, String prenomEt);
    public Etudiant findEtudiantByEmail(String email);
    public Etudiant findEtudiantByNomEt(String nom);
}
