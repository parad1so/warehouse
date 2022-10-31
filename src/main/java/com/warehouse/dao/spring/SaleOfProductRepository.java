package com.warehouse.dao.spring;

import com.warehouse.model.SaleOfProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("saleRep")
public interface SaleOfProductRepository extends JpaRepository<SaleOfProduct, Integer> {

}
