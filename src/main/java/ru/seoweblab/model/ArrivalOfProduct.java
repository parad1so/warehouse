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

@Table(name="arrival_of_product")
public class ArrivalOfProduct implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn( name = "number_id", referencedColumnName = "id")
    @JsonProperty("number_id")
     private NumberOfProduct numberId;
    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    @JsonProperty("warehouse_id")
    private NumberOfWarehose warehouseId;
    @ManyToOne
    @JoinColumn(name = "list_of_product_id" , referencedColumnName = "id")
    @JsonProperty("list_of_product_id")
    private ListOfProduct listOfProductId;





}
