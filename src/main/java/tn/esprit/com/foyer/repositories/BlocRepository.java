package tn.esprit.com.foyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.com.foyer.entities.Bloc;
import tn.esprit.com.foyer.entities.Etaat;
import tn.esprit.com.foyer.entities.Foyer;
import tn.esprit.com.foyer.entities.TypeChambre;

import java.util.List;
@Repository
public interface BlocRepository extends JpaRepository<Bloc,Long> {
    Bloc findByNomBloc(String nom);
    @Query("SELECT COUNT(c) FROM Chambre c WHERE c.typeC = :type AND c.bloc.idBloc = :idBloc")
    long nbChambreParTypeEtBloc(@Param("type") TypeChambre type, @Param("idBloc") long idBloc);
    @Query("SELECT COUNT(b) FROM Bloc b WHERE b.etat = :etat")
    Float nbrBlocParEtat(@Param("etat") Etaat etat);
    List<Bloc> findByEtat(Etaat etat);

    Bloc findByIdBloc(long idBloc);

    List<Bloc> findByFoyer(Foyer foyer);
}
