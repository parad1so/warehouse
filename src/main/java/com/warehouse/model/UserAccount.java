package com.warehouse.model;

import com.warehouse.model.interfaceModel.Model;
import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_account")
public class UserAccount implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @NonNull
    @Column(name = "name", length = 100)
    private String name;
    @NonNull
    @Column(name = "password", length = 100)
    private String password;


}
