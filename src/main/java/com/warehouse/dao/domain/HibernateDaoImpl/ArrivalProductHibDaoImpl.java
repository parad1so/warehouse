package com.warehouse.dao.domain.HibernateDaoImpl;

import com.warehouse.dao.domain.documentsDaoHibernate.DocumentsArrivalHibDao;
import com.warehouse.hibernate.HibernateUtil;
import com.warehouse.model.ArrivalOfProduct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Data
@NoArgsConstructor
public class ArrivalProductHibDaoImpl implements DocumentsArrivalHibDao {
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
    public List<ArrivalOfProduct> findAll() {
        List<ArrivalOfProduct> products = (List<ArrivalOfProduct>) getCurrentSession().createQuery("from ArrivalOfProduct").list();
        return products;
    }



    @Override
    public void save(ArrivalOfProduct model) {
        getCurrentSession().save(model);
    }

    @Override
    public void update(ArrivalOfProduct model) {
        getCurrentSession().update(model);
    }

    @Override
    public ArrivalOfProduct findById(Integer id) {
        ArrivalOfProduct arrivalOfProduct = getCurrentSession().get(ArrivalOfProduct.class, id);
        return arrivalOfProduct;
    }

    @Override
    public void delete(ArrivalOfProduct model) {
        getCurrentSession().delete(model);
    }

    @Override
    public ArrivalOfProduct read(Integer modelId) {
        final ArrivalOfProduct result = getCurrentSession().get(ArrivalOfProduct.class, modelId);
        if(result!= null) {
            Hibernate.initialize(result.getListOfProductId());
            Hibernate.initialize(result.getNumberId());
            Hibernate.initialize(result.getWarehouseId());
        }
        return result;
    }
}
