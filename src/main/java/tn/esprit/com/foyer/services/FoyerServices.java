package tn.esprit.com.foyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.entities.Etaat;

import tn.esprit.com.foyer.entities.Foyer;
import tn.esprit.com.foyer.entities.TypeBlocPourcentage;
import tn.esprit.com.foyer.repositories.BlocRepository;
import tn.esprit.com.foyer.repositories.FoyerRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Slf4j
@Service
@AllArgsConstructor
public class FoyerServices implements IFoyerService{
    FoyerRepository foyerRepository;
BlocRepository blocRepository;
    @Override
    public List<Foyer> retrieveAllFoyers() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer updateFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer retrieveFoyer(long idFoyer) {
        return foyerRepository.findById(idFoyer).orElse(null);
    }

    @Override
    public void archiverFoyer(long idFoyer) {
        Foyer f = foyerRepository.findById(idFoyer).orElse(null);
        if (f != null) {
            f.setArchived(true);
            foyerRepository.save(f);
        } else {
            log.info("foyer not found");
        }
    }

    @Override
    public void deleteFoyer(long idFoyer) {
        foyerRepository.deleteById(idFoyer);
    }


    public Set<TypeBlocPourcentage> calculerPourcentageBlocParEtat() {
        Long totalBlocsTotal = blocRepository.count();
        Set<TypeBlocPourcentage> resultSet = new HashSet<>();

        for (Etaat etat : Etaat.values()) {
            Float result = blocRepository.nbrBlocParEtat(etat);


            float pourcentage = (result != null ? result : 0.0f) * 100.0f / totalBlocsTotal;

            TypeBlocPourcentage blocInfo = new TypeBlocPourcentage(etat, pourcentage);
            resultSet.add(blocInfo);
        }

        return resultSet;
    }

}
