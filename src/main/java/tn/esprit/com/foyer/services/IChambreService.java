package tn.esprit.com.foyer.services;

import tn.esprit.com.foyer.entities.Chambre;
import tn.esprit.com.foyer.entities.TypeChambrePourcentage;

import java.util.HashSet;
import java.util.List;

public interface IChambreService {
    List<Chambre> retrieveAllChambre();

    Chambre addChambre(Chambre c);

    Chambre updateChambre(Chambre updatedChambre );

    Chambre retrieveChambre(Long idChambre);

    void removeChambre(Long idChambre);
    HashSet<TypeChambrePourcentage> calculerPourcentageChambreParTypeChambre1(boolean estValid);

}
