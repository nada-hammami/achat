package tn.esprit.com.foyer.controller;

import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.com.foyer.entities.Reservation;
import tn.esprit.com.foyer.services.IReservationService;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class ReservationController {
    IReservationService reservationService;
    @GetMapping("/retrieveReservations")
    public List<Reservation> retrieveReservations(){
        return reservationService.retrieveReservations();

    }
    @PostMapping("/addReservation")
    public Reservation addReservation(@RequestBody Reservation res){
        return reservationService.addReservation(res);

    }

    @PutMapping("/updateReservation")
    public Reservation updateReservation(@RequestBody Reservation res){
        return reservationService.updateReservation(res);

    }

    @GetMapping("/retrieveReservation/{id-reservation}")
    public Reservation retrieveReservation(@PathVariable("id-reservation") long idReservation){
        return reservationService.retrieveReservation(idReservation);

    }

    @GetMapping("/getReservationParAnneeUniversitaire/{annee-univ}")
    public List <Reservation> getReservationParAnneeUniversitaire(@PathVariable("annee-univ") Date anneeUniversitaire){
        return reservationService.getReservationParAnneeUniversitaire(anneeUniversitaire);

    }

    @GetMapping("/statistiques")
    public double statistiques(){
        return  reservationService.statistiques();

    }

    @PutMapping("/validerReservation/{id-reservation}")
    public void validerReservation(@PathVariable("id-reservation") long idReservation){
        reservationService.validerReservation(idReservation);
    }
}