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
@Table(name="moving_of_product")
public class MovingOfProduct implements Model {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name = "id")
     private int id;

     @ManyToOne
     @JoinColumn(name = "number_id", referencedColumnName = "id")
     @JsonProperty("number_id")
     @NonNull
     private NumberOfProduct numberId;

     @ManyToOne
     @JoinColumn(name = "warehousea_id", referencedColumnName = "id")
     @JsonProperty("warehousea_id")
     @NonNull
     private  NumberOfWarehose warehouseAId;

     @ManyToOne
     @JoinColumn(name = "warehouseb_id", referencedColumnName = "id")
     @JsonProperty("warehouseb_id")
     @NonNull
     private  NumberOfWarehose warehouseBId;

     @ManyToOne
     @JoinColumn(name ="list_of_product_id", referencedColumnName = "id")
     @JsonProperty("list_of_product_id")
     @NonNull
     private ListOfProduct listOfProductId;





 }
