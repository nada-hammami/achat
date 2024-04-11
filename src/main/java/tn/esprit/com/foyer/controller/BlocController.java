package tn.esprit.com.foyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.com.foyer.entities.Bloc;
import tn.esprit.com.foyer.services.BlocServices;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200")
    @AllArgsConstructor
    @RequestMapping("/api")
    public class BlocController {
        BlocServices blocServices;
        @GetMapping("bloc/retrieve-all-bloc")
        public List<Bloc> retrieveAllBloc(){
            return blocServices.retrieveAllBlocs();
        }
        @GetMapping("/admin/retrieve-bloc/{bloc-id}")
        public Bloc retrieveBloc(@PathVariable("bloc-id") Long blocId){
            return blocServices.retrieveBloc(blocId);
        }

        @PostMapping("/admin/add-bloc")
        public Bloc addBloc(@RequestBody Bloc bloc){
            return blocServices.addBloc(bloc);
        }
        @PutMapping("/admin/update-bloc")
        public Bloc updateBloc(@RequestBody Bloc bloc){return  blocServices.updateBloc(bloc);}

        @DeleteMapping("/admin/delete-bloc/{bloc-id}")
        public void deleteBloc(@PathVariable("bloc-id") Long blocId){
            blocServices.removeBloc(blocId);
        }
    @GetMapping("/admin/{id}/generate-pdf")
    public ResponseEntity<byte[]> generateChambrePdf(@PathVariable Long id) throws IOException {
        Bloc bloc = blocServices.retrieveBloc(id);// ... remplacez par votre logique d'obtention d'utilisateur
        // Générez le PDF
        byte[] pdfBytes = blocServices.generatePdfForBloc(bloc);
        HttpHeaders headers = new HttpHeaders(); headers.setContentType(MediaType.APPLICATION_PDF); headers.setContentDispositionFormData("inline", "bloc.pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK); }


    @GetMapping("/bloc/infos-blocs")
    public ResponseEntity<List<Map<String, Object>>> obtenirInfosBlocsParFoyer() {
        List<Map<String, Object>> blocsInfos = blocServices.obtenirInfosBlocsParFoyer();
        return new ResponseEntity<>(blocsInfos, HttpStatus.OK);
    }
    @GetMapping("/bloc/infos")
    public ResponseEntity<List<Map<String, Object>>> obtenirInfos() {
        List<Map<String, Object>> blocsInfos = blocServices.obtenirInfos();
        return new ResponseEntity<>(blocsInfos, HttpStatus.OK);
    }


}
