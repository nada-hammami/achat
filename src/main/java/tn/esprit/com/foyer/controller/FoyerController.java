package tn.esprit.com.foyer.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.com.foyer.entities.Foyer;
import tn.esprit.com.foyer.entities.TypeBlocPourcentage;
import tn.esprit.com.foyer.services.FoyerServices;
import tn.esprit.com.foyer.services.MaintenanceServices;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class FoyerController {
    FoyerServices foyerServices;
MaintenanceServices maintenanceServices;
    @GetMapping("/retrieve-all-foyer")
    public List<Foyer> retrieveAllFoyer(){
        return foyerServices.retrieveAllFoyers();
    }

    @GetMapping("/admin/foyer/retrieve-foyer/{foyer-id}")
    public Foyer retrieveFoyer(@PathVariable("foyer-id") Long foyerId){
        return foyerServices.retrieveFoyer(foyerId);
    }

    @PostMapping("/admin/foyer/add-foyer")
    public Foyer addFoyer(@RequestBody Foyer f){
        return foyerServices.addFoyer(f);
    }
    @DeleteMapping("/admin/foyer/delete-foyer/{foyer-id}")
    public void deleteFoyer(@PathVariable("foyer-id") Long foyerId){
        foyerServices.deleteFoyer(foyerId);
    }

    @PostMapping("/admin/foyer/archiver-foyer/{foyer-id}")
    public void archiverFoyer(@PathVariable("foyer-id") Long foyerId){
        foyerServices.archiverFoyer(foyerId);
    }
    @GetMapping("/admin/foyer/pourcentage-par-etat")
    public ResponseEntity<Set<TypeBlocPourcentage>> calculerPourcentageBlocParEtat() {
        Set<TypeBlocPourcentage> pourcentages = foyerServices.calculerPourcentageBlocParEtat();
        return new ResponseEntity<>(pourcentages, HttpStatus.OK);
    }
    @PutMapping("/admin/modifier-foyer/{id}")
    public  Foyer updatefoyer(@PathVariable("id") @RequestBody  Foyer foyer){ return foyerServices.updateFoyer(foyer);}
    @GetMapping("/admin/foyer/export")
    public void exportToExcel(HttpServletResponse response) {
        try {
            // Appeler la méthode du service pour obtenir le workbook
            Workbook workbook = maintenanceServices.exportToExcel();

            // Définir le contentType pour forcer le téléchargement
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=maintenance_report.xlsx");

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();


            response.getOutputStream().write(outputStream.toByteArray());

            response.getOutputStream().flush();
            response.getOutputStream().close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}