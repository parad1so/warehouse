package com.warehouse.view.hibernateView;

import com.warehouse.dao.domain.HibernateDaoImpl.service.MovingProductService;
import com.warehouse.model.MovingOfProduct;
import com.warehouse.view.interfaceView.HibernateView;

import java.util.List;

public class MovingProductHibernateViewImpl implements HibernateView<MovingOfProduct> {
    @Override
    public List<MovingOfProduct> findAllView() {
        return new MovingProductService().findAll();
    }
}
