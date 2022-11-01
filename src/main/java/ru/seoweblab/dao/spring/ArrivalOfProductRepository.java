package ru.seoweblab.dao.spring;


import ru.seoweblab.model.ArrivalOfProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("arrRep")
public interface ArrivalOfProductRepository extends JpaRepository<ArrivalOfProduct, Integer> {

}
