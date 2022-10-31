package com.warehouse.dao.spring;


import com.warehouse.model.ArrivalOfProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("arrRep")
public interface ArrivalOfProductRepository extends JpaRepository<ArrivalOfProduct, Integer> {

}
