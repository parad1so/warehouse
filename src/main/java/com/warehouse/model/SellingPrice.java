package com.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.warehouse.model.interfaceModel.Model;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="selling_price")
public class SellingPrice implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @NonNull
    @JsonProperty("id")
    private int id;
    @Column(name = "price", nullable = true)
    @NonNull
    @JsonProperty("price")
    private int price;
}
