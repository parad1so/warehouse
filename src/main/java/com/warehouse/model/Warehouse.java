package com.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.warehouse.model.interfaceModel.Model;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="warehouse")
public class Warehouse implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "warehouse_id" , referencedColumnName = "id")
    @JsonProperty("warehouse_id")
    @NonNull
   private NumberOfWarehose numberOfWarehoseId;
    @ManyToOne
    @JoinColumn(name = "product_id" , referencedColumnName = "id")
    @JsonProperty("product_id")
    @NonNull
    private Product productId;
    @Column(name = "quantity")
    @JsonProperty("quantity")
    @NonNull
    private int quantity;

    /*@OneToMany(mappedBy = "warehouseId", cascade = CascadeType.ALL)
    private Set<ArrivalOfProduct> arrivalOfProductWarehouse = new HashSet<>();*/


}
