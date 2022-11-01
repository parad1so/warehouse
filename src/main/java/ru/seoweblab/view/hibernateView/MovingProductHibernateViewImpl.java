package ru.seoweblab.view.hibernateView;

import ru.seoweblab.dao.domain.HibernateDaoImpl.service.MovingProductService;
import ru.seoweblab.model.MovingOfProduct;
import ru.seoweblab.view.interfaceView.HibernateView;

import java.util.List;

public class MovingProductHibernateViewImpl implements HibernateView<MovingOfProduct> {
    @Override
    public List<MovingOfProduct> findAllView() {
        return new MovingProductService().findAll();
    }
}
