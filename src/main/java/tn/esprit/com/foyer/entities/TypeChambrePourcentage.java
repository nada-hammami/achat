package tn.esprit.com.foyer.entities;

public class TypeChambrePourcentage {
    private TypeChambre typeChambre;
    private Float pourcentage;

    public TypeChambrePourcentage() {
    }

    public TypeChambrePourcentage(TypeChambre typeChambre, Float pourcentage) {
        this.typeChambre = typeChambre;
        this.pourcentage = pourcentage;
    }

    public TypeChambre getTypeChambre() {
        return typeChambre;
    }

    public void setTypeChambre(TypeChambre typeChambre) {
        this.typeChambre = typeChambre;
    }

    public Float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Float pourcentage) {
        this.pourcentage = pourcentage;
    }

}
