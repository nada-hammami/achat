package tn.esprit.com.foyer.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Maintenance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Bloc bm;
    private Date endDate;
    private Date startDate;


    private String details;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Bloc
    public Bloc getBloc() {
        return bm;
    }

    public void setBloc(Bloc bm) {
        this.bm = bm;
    }

    // Maintenance Date


    // Start Date
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    // End Date
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    // Details
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}

