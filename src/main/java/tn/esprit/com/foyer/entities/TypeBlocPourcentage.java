package tn.esprit.com.foyer.entities;

public class TypeBlocPourcentage {
    private Etaat etat;
    private float pourcentage;
    public TypeBlocPourcentage(Etaat etat, float pourcentage) {
        this.etat = etat;
        this.pourcentage = pourcentage;
    }

    public Etaat getEtat() {
        return etat;
    }

    public void setEtat(Etaat etat) {
        this.etat = etat;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

}
