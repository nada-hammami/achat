package tn.esprit.com.foyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.com.foyer.entities.Chambre;
import tn.esprit.com.foyer.entities.Foyer;
import tn.esprit.com.foyer.entities.TypeChambre;
import tn.esprit.com.foyer.services.DevopsService;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class DevopsController {
    DevopsService devopsService;



    @PostMapping("/admin/chambre/ajouterChambre/{id}")
    public ResponseEntity<Foyer> ajouterChambre(@PathVariable Long id, @RequestBody Chambre ch) {
        try {
            if (id == null || ch == null) {
                return ResponseEntity.badRequest().build(); // Input validation
            }

            Foyer foyer = devopsService.ajouterChambreAvecCapacite(id, ch);
            if (foyer != null) {
                return ResponseEntity.ok(foyer);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }


}
