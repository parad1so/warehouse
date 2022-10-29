package com.warehouse.view.hibernateView;

import com.warehouse.dao.domain.HibernateDaoImpl.service.SaleProductService;
import com.warehouse.model.SaleOfProduct;
import com.warehouse.view.interfaceView.HibernateView;

import java.util.List;

public class SaleProductHibernateViewImpl implements HibernateView<SaleOfProduct> {
    @Override
    public List<SaleOfProduct> findAllView() {
        return new SaleProductService().findAll();
    }
}
