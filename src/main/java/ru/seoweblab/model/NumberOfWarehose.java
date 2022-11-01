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
@Table(name="number_of_warehouse")
public class NumberOfWarehose implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonProperty("id")
    @Getter private int id;
    @Column(name = "warehouse_name")
    @JsonProperty("warehouse_name")
    private String warehouseName;
}
