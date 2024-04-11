package tn.esprit.com.foyer.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
import tn.esprit.com.foyer.entities.Etudiant;

import tn.esprit.com.foyer.entities.Reservation;
import tn.esprit.com.foyer.services.EtudiantService;
import tn.esprit.com.foyer.services.IEtudiantService;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class EtudiantController {
    IEtudiantService etudiantService;
EtudiantService etuservice;
    @GetMapping("/retrieve-all-etudiant")
    List<Etudiant> retrieveAllEtudiants() {
        return etudiantService.retrieveAllEtudiants();

    }


    @PostMapping("/addetudiant")
    public Etudiant addEtudiant(@RequestBody Etudiant e) {
        return etudiantService.addEtudiant(e);

    }

    @PutMapping("/updateetudiant")
    public Etudiant updateEtudiant(@RequestBody Etudiant e) {

        return etudiantService.updateEtudiant(e);

    }


    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Long etudiantId) {
        return etudiantService.retrieveEtudiant(etudiantId);
    }

    @DeleteMapping("/remouve-etudiant/{etudiant-id}")
    void removeEtudiant(@PathVariable("etudiant-id") Long etudiantId) {

        etudiantService.removeEtudiant(etudiantId);
    }

    @PostMapping("/affecterEtudiantAReservation/{nom-et}/{prenom-et}/{id-reservation}")
    public Etudiant affecterEtudiantAReservation(@PathVariable("nom-et") String nomEt, @PathVariable("prenom-et") String prenomEt, @PathVariable("id-reservation") Long idReservation) {
        etudiantService.affecterEtudiantAReservation(nomEt, prenomEt, idReservation);
        return null;
    }

    @PostMapping("/passerUneReservation2/{id-etudiant}/{numC}")
    public void passerUneReservation(@PathVariable("id-etudiant") long idEtudiant , @RequestBody Reservation res, @PathVariable("numC") long numchambre
    ){
        etuservice.passerUneReservation2( idEtudiant,  res, numchambre);
    }

    @GetMapping("/findEtudiantwithemail/{e-mail}")
    @JsonFormat
    public Etudiant findEtudiantwithemail(@PathVariable("e-mail") String email){
        return etudiantService.findEtudiantwithemail(email);

    }


}
