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
@Table(name="product")
public class Product implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonProperty("id")
    private int id;

    @Column(name = "vendor_code", length = 100)
    @JsonProperty("vendor_code")
    @NonNull
    private String vendorCode;

    @Column(name = "product_name",  length = 50)
    @JsonProperty("product_name")
    @NonNull
    private String productName;

    @Column(name = "last_purchase_price")
    @JsonProperty("last_purchase_price")
    @NonNull
    private int lastPurchasePrice;

    @Column(name = "last_sale_price")
    @JsonProperty("last_sale_price")
    @NonNull
    private int lastSalePrice;

}
