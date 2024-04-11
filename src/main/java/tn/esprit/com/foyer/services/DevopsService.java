package tn.esprit.com.foyer.services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.entities.Bloc;
import tn.esprit.com.foyer.entities.Chambre;
import tn.esprit.com.foyer.entities.Foyer;
import tn.esprit.com.foyer.entities.TypeChambre;
import tn.esprit.com.foyer.repositories.BlocRepository;
import tn.esprit.com.foyer.repositories.ChambreRepository;
import tn.esprit.com.foyer.repositories.FoyerRepository;

import java.util.Optional;


@Service
@AllArgsConstructor
public class DevopsService {

    private BlocRepository blocRepository;

    private ChambreRepository chambreRepository;

    private FoyerRepository foyerRepository;

    public Foyer ajouterChambreAvecCapacite(Long idBloc, Chambre ch) {
        Bloc bloc = blocRepository.findById(idBloc).get();
        if (bloc != null) {

            int capaciteChambre = determinerCapaciteChambre(ch.getTypeC());

            chambreRepository.save(ch);

            bloc.setCapaciteBloc(bloc.getCapaciteBloc() + capaciteChambre);
            blocRepository.save(bloc);
            Foyer foyer = bloc.getFoyer();

            if (foyer != null) {
                foyer.setCapaciteFoyer(foyer.getCapaciteFoyer() + capaciteChambre);
                foyerRepository.save(foyer);
            }

            return foyer;
        } else {
            return null;
        }
    }

    private int determinerCapaciteChambre(TypeChambre typeChambre) {
        switch (typeChambre) {
            case SIMPLE:
                return 1;
            case DOUBLE:
                return 2;
            case TRIPLE:
                return 3;
            default:
                return 0;
        }
    }

}
