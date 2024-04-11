package tn.esprit.com.foyer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder

@AllArgsConstructor
@NoArgsConstructor
@Table( name = "Chambre")
public class Chambre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idChambre")
    private Long idChambre; // Clé primaire
    private Long numeroChambre;
    @Enumerated(EnumType.STRING)
    private TypeChambre typeC;
    @ManyToOne
    Bloc bloc;
    @OneToMany(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    private Set<Reservation> reservations;




}
