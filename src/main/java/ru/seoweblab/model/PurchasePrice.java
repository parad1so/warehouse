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
@Table(name="purchase_price")
public class PurchasePrice implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @NonNull
    @JsonProperty("id")
    private int id;

    @NonNull
    @Column(name = "price")
    @JsonProperty("price")
    private int price;
}
