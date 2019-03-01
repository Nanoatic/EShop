package nano.project.eshop.models;

import org.apache.solr.client.solrj.beans.Field;
import org.hibernate.annotations.Type;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.*;

@org.hibernate.search.annotations.Indexed
@SolrDocument(solrCoreName = "product")
@Entity
public class Product {

    @Field
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Field
    @Column(name = "quantity")
    private Integer quantity;

    @Field
    @Column(name = "category")
    private String category;
    @Field
    @Column(name = "price")
    private Float price;
    @org.hibernate.search.annotations.Field
    @Field
    @Indexed(name = "name", type = "string")
    @Column(name = "name")
    private String name;
    @Indexed
    @Field
    @Type(type="text")
    private String details;
    @Field
    @Column(name = "photo")
    private String photo;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
