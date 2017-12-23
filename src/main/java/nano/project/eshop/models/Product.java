package nano.project.eshop.models;

import javax.persistence.*;

@Entity
@Table(name = "produit")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "qte")
    private int qte;

    @Column(name = "categorie")
    private String categorie;

    @Column(name = "prix")
    private float prix;

    @Column(name = "libel")
    private String libel;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Column(name = "photo")
    private String photo;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getLibel() {
        return libel;
    }

    public void setLibel(String libel) {
        this.libel = libel;
    }
}
