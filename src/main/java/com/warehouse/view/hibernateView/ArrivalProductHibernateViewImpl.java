package com.warehouse.view.hibernateView;


import com.warehouse.dao.domain.HibernateDaoImpl.service.ArrivalProductService;
import com.warehouse.model.ArrivalOfProduct;
import com.warehouse.view.interfaceView.HibernateView;


import java.util.List;

public class ArrivalProductHibernateViewImpl implements HibernateView<ArrivalOfProduct> {
    @Override
    public List<ArrivalOfProduct> findAllView() {
        return new ArrivalProductService().findAll();
    }
}
