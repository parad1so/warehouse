package ru.seoweblab.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.seoweblab.model.interfaceModel.Model;
import lombok.*;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="list_of_product")
public class ListOfProduct implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonProperty("id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @NonNull
    @JsonProperty("product_id")
    private Product productId;
    @NonNull
    @Column(name = "quantity")
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "purchase_price_id", referencedColumnName = "id")
    @NonNull
    @JsonProperty("purchase_price_id")
    private PurchasePrice purchasePriceId;
    @ManyToOne
    @JoinColumn(name = "selling_price_id", referencedColumnName = "id")
    @NonNull
    @JsonProperty("selling_price_id")
    private SellingPrice sellingPriceId;

    /*@OneToMany(mappedBy = "listOfProductId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ArrivalOfProduct> arrivalOfProductList = new HashSet<>();*/

}
