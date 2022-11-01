package ru.seoweblab.view.hibernateView;

import ru.seoweblab.dao.domain.HibernateDaoImpl.service.SaleProductService;
import ru.seoweblab.model.SaleOfProduct;
import ru.seoweblab.view.interfaceView.HibernateView;

import java.util.List;

public class SaleProductHibernateViewImpl implements HibernateView<SaleOfProduct> {
    @Override
    public List<SaleOfProduct> findAllView() {
        return new SaleProductService().findAll();
    }
}
