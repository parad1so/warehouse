package com.warehouse.model;


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
@Table(name="number_of_product")
public class NumberOfProduct implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "number", nullable = true)
    @NonNull
    private int number;

//    @OneToMany(mappedBy = "numberId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @ToString.Exclude private Set<ArrivalOfProduct> arrivalOfProductNumber = new HashSet<>();




}
