package com.warehouse.dao.domain.HibernateDaoImpl;

import com.warehouse.dao.domain.documentsDaoHibernate.DocumentsMovingHibDao;
import com.warehouse.hibernate.HibernateUtil;
import com.warehouse.model.MovingOfProduct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Data
@NoArgsConstructor
public class MovingProductHibDaoImpl implements DocumentsMovingHibDao {
    private Session currentSession;
    private Transaction currentTransaction;

    public Session openCurrentSession() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        return currentSession;
    }
    public Session openCurrentSessionWithTransaction() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }
    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MovingOfProduct> findAll() {
        List<MovingOfProduct> products = (List<MovingOfProduct>) getCurrentSession().createQuery("from MovingOfProduct ").list();
        return products;
    }



    @Override
    public void save(MovingOfProduct model) {
        getCurrentSession().save(model);
    }

    @Override
    public void update(MovingOfProduct model) {
        getCurrentSession().update(model);
    }

    @Override
    public MovingOfProduct findById(Integer id) {
        MovingOfProduct products = getCurrentSession().get(MovingOfProduct.class, id);
        return products;
    }

    @Override
    public void delete(MovingOfProduct model) {
        getCurrentSession().delete(model);
    }

    @Override
    public MovingOfProduct read(Integer modelId) {
        final MovingOfProduct result = getCurrentSession().get(MovingOfProduct.class, modelId);
        if(result!= null) {
            Hibernate.initialize(result.getListOfProductId());
            Hibernate.initialize(result.getNumberId());
            Hibernate.initialize(result.getWarehouseAId());
            Hibernate.initialize(result.getWarehouseBId());
        }
        return result;
    }

}
