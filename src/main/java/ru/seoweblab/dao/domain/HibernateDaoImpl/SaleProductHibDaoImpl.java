package ru.seoweblab.dao.domain.HibernateDaoImpl;

import ru.seoweblab.dao.domain.documentsDaoHibernate.DocumentsSaleHibDao;
import ru.seoweblab.hibernate.HibernateUtil;
import ru.seoweblab.model.SaleOfProduct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Data
@NoArgsConstructor
public class SaleProductHibDaoImpl implements DocumentsSaleHibDao {

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
    public List<SaleOfProduct> findAll() {
        List<SaleOfProduct> products = (List<SaleOfProduct>) getCurrentSession().createQuery("from SaleOfProduct").list();
        return products;
    }



    @Override
    public void save(SaleOfProduct model) {
        getCurrentSession().save(model);
    }

    @Override
    public void update(SaleOfProduct model) {
        getCurrentSession().update(model);
    }

    @Override
    public SaleOfProduct findById(Integer id) {
        SaleOfProduct products = getCurrentSession().get(SaleOfProduct.class, id);
        return products;
    }

    @Override
    public void delete(SaleOfProduct model) {
        getCurrentSession().delete(model);
    }

    @Override
    public SaleOfProduct read(Integer modelId) {
        final SaleOfProduct result = getCurrentSession().get(SaleOfProduct.class, modelId);
        if(result!= null) {
            Hibernate.initialize(result.getListOfProductId());
            Hibernate.initialize(result.getNumberId());
            Hibernate.initialize(result.getWarehouseId());
        }
        return result;
    }
}
