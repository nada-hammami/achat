package tn.esprit.com.foyer.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.com.foyer.entities.Chambre;
import tn.esprit.com.foyer.entities.TypeChambrePourcentage;
import tn.esprit.com.foyer.services.BlocServices;
import tn.esprit.com.foyer.services.ChambreServices;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class ChambreController {
    ChambreServices chambreServices;
    BlocServices blocServices;
    @GetMapping("/chambre/get-all-chambres")
    public List<Chambre> retrieveChambres(){
        return chambreServices.retrieveAllChambre();
    }
    @GetMapping("/admin/chambre/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chambreId){
        return chambreServices.retrieveChambre(chambreId);
    }
    @PostMapping("/admin/chambre/add-chambre")
    public Chambre addChambre(@RequestBody Chambre ch){
        return chambreServices.addChambre(ch);
    }
    @PutMapping("/admin/chambre/update-chambre/{id}")
    public ResponseEntity<Chambre> updateChambre(@PathVariable("id") Long id, @RequestBody Chambre chambre) {
        chambre.setIdChambre(id); // Ensure the provided chambre has the correct ID
        Chambre updatedChambre = chambreServices.updateChambre(chambre);
        return ResponseEntity.ok(updatedChambre);
    }

    @DeleteMapping("/admin/chambre/delete-chambre/{chambre-id}")
    public void deleteChambre(@PathVariable("chambre-id") Long chambreId){
        chambreServices.removeChambre(chambreId);
    }
    @PutMapping("/admin/chambre/affecterListeChambre/{nomBloc}")
    public void affecterChambreBloc(@PathVariable("nomBloc") String nomBloc, @RequestBody List<Long> chambreNumbers) {
        chambreServices.affecterChambresABloc(chambreNumbers, nomBloc);
    }

    @GetMapping("/admin/chambre/calculerPourcentageChambre")
    public Set<TypeChambrePourcentage> calculerPourcentageChambre1(@RequestParam boolean estValide) {
        return chambreServices.calculerPourcentageChambreParTypeChambre1(estValide);
    }

}