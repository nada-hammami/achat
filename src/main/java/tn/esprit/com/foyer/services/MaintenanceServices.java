package tn.esprit.com.foyer.services;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.entities.Bloc;
import tn.esprit.com.foyer.entities.Etaat;
import tn.esprit.com.foyer.entities.Maintenance;
import tn.esprit.com.foyer.repositories.BlocRepository;
import tn.esprit.com.foyer.repositories.MaintenanceRepository;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class MaintenanceServices implements IMaintenanceService {
    MaintenanceRepository maintenanceRepository;
    BlocRepository blocRepository;
    BlocServices blocServices;
    public Workbook exportToExcel() {
        List<Maintenance> maintenances = maintenanceRepository.findAll();

        // Création du Workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Maintenance Report");

        // Création de l'en-tête
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Bloc ID");
        headerRow.createCell(2).setCellValue("Date de Début de Maintenance");
        headerRow.createCell(3).setCellValue("Date de Fin de Maintenance");
        headerRow.createCell(4).setCellValue("Détails");

        // Format de date pour afficher correctement les dates dans Excel
        CreationHelper createHelper = workbook.getCreationHelper();
        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("dd-MM-yyyy HH:mm:ss")
        );

        // Remplissage des données
        int rowNum = 1;
        for (Maintenance maintenance : maintenances) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(maintenance.getId());
            row.createCell(1).setCellValue(maintenance.getBloc().getId());

            Cell startDateCell = row.createCell(2);
            if (maintenance.getStartDate() != null) {
                startDateCell.setCellValue(maintenance.getStartDate());
                startDateCell.setCellStyle(dateStyle);
            }

            Cell endDateCell = row.createCell(3);
            if (maintenance.getEndDate() != null) {
                endDateCell.setCellValue(maintenance.getEndDate());
                endDateCell.setCellStyle(dateStyle);
            }

            row.createCell(4).setCellValue(maintenance.getDetails());
        }

        // Auto ajustement de la largeur des colonnes
        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }

        return workbook;
    }

    @Scheduled(fixedRate = 86400000)
    public void performDailyMaintenance() {
        List<Bloc> blocs = blocRepository.findByEtat(Etaat.MAUVAIS); // Utilisez la bonne énumération pour 'mauvaise'

        for (Bloc bloc : blocs) {
            Date currentDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.MONTH, 1); // Ajout d'un mois à la date actuelle

            Maintenance maintenance = new Maintenance();
            maintenance.setBloc(bloc);
            maintenance.setStartDate(currentDate); // Date de début est maintenant
            maintenance.setEndDate(calendar.getTime()); // Date de fin est un mois après

            maintenanceRepository.save(maintenance);

            // Changement de l'état du bloc maintenant que la date de fin est définie
            bloc.setEtat(Etaat.EXELLENT); // Utilisez la bonne énumération pour 'excellente'
            blocRepository.save(bloc);
        }
    }

}





