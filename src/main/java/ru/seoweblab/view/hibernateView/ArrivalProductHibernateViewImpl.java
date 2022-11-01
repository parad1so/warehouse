package ru.seoweblab.view.hibernateView;


import ru.seoweblab.dao.domain.HibernateDaoImpl.service.ArrivalProductService;
import ru.seoweblab.model.ArrivalOfProduct;
import ru.seoweblab.view.interfaceView.HibernateView;


import java.util.List;

public class ArrivalProductHibernateViewImpl implements HibernateView<ArrivalOfProduct> {
    @Override
    public List<ArrivalOfProduct> findAllView() {
        return new ArrivalProductService().findAll();
    }
}
