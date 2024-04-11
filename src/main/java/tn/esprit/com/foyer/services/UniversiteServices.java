package tn.esprit.com.foyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.entities.Foyer;
import tn.esprit.com.foyer.entities.Universite;
import tn.esprit.com.foyer.repositories.FoyerRepository;
import tn.esprit.com.foyer.repositories.UniversteRepository;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@AllArgsConstructor
public class UniversiteServices implements IUniversiteService{
    UniversteRepository universteRepository;
    FoyerRepository foyerRepository;

    @Override
    public List<Universite> retrieveAllUniversities() {
        return universteRepository.findAll();
    }

    @Override
    public Universite addUniversity(Universite u) {
        return universteRepository.save(u);
    }

    @Override
    public Universite updateUniversity(Universite u) {
        return universteRepository.save(u);
    }

    @Override
    public Universite retrieveUniversity(long idUniversite) {
        Optional<Universite> optionalUniversite = universteRepository.findById(idUniversite);
        return optionalUniversite.orElse(null);
    }

    @Override
    public void removeUniversity(long idUniversite) {
        universteRepository.deleteById(idUniversite);
    }




    public Universite affecterFoyerUniversite(long idFoyer, String nomUniversite) {
        Optional<Foyer> optionalFoyer = foyerRepository.findById(idFoyer);
        Optional<Universite> optionalUniversite = universteRepository.findByNomUniversite(nomUniversite);

        if (optionalFoyer.isPresent() && optionalUniversite.isPresent()) {
            Foyer f = optionalFoyer.get();
            Universite u = optionalUniversite.get();

            u.setFoyer(f);
            universteRepository.save(u);
            return u;
        } else {
            log.info("Foyer or Universite not found");
            return null; // Or handle the case differently based on your requirements
        }
    }

    public Universite desaffeecterFoyerUniversite(String nomUniversite) {
        Optional<Universite> optionalUniversite = universteRepository.findByNomUniversite(nomUniversite);

        if (optionalUniversite.isPresent()) {
            Universite universite = optionalUniversite.get();
            universite.setFoyer(null);
            universteRepository.save(universite);
            return universite;
        } else {
            log.info("Universite not found with name: " + nomUniversite);
            return null; // Or handle the case differently based on your requirements
        }
    }

}
