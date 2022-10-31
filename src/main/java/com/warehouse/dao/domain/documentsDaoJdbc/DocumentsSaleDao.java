package com.warehouse.dao.domain.documentsDaoJdbc;

import com.warehouse.dao.jdbcDao.CrudDao;
import com.warehouse.model.SaleOfProduct;


import java.util.List;

public interface DocumentsSaleDao extends CrudDao<SaleOfProduct> {
    List<SaleOfProduct> findAll();

}
