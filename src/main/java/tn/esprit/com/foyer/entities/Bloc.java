package tn.esprit.com.foyer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Builder
@Setter
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Bloc")
public class Bloc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idBloc")
    private Long idBloc; // Clé primaire
    private String nomBloc;
    private Long capaciteBloc;
    private Etaat etat;
    @ManyToOne
    Foyer foyer;
    @JsonIgnore

    @OneToMany(mappedBy="bloc" , cascade = CascadeType.ALL )
    private List<Chambre> chambre;
    @Transient
    @OneToMany(mappedBy = "bm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Maintenance> maintenances;
    public Long getId() {
        return this.idBloc;
    }

    // Setter pour id
    public void setId(Long id) {
        this.idBloc = id;
    }


}
