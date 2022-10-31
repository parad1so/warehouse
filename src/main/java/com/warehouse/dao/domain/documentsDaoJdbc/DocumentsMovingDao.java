package com.warehouse.dao.domain.documentsDaoJdbc;

import com.warehouse.dao.jdbcDao.CrudDao;

import com.warehouse.model.MovingOfProduct;

import java.util.List;

public interface DocumentsMovingDao extends CrudDao<MovingOfProduct> {
    List<MovingOfProduct> findAll();

}
