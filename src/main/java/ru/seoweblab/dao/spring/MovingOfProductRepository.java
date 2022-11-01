package ru.seoweblab.dao.spring;


import ru.seoweblab.model.MovingOfProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("movingRep")
public interface MovingOfProductRepository extends JpaRepository<MovingOfProduct, Integer> {
}
