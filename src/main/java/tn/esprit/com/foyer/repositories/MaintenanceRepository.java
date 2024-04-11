package tn.esprit.com.foyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.com.foyer.entities.Maintenance;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance,Long> {
}
