package ru.seoweblab.dao.domain.documentsDaoJdbc;

import ru.seoweblab.dao.jdbcDao.CrudDao;

import ru.seoweblab.model.MovingOfProduct;

import java.util.List;

public interface DocumentsMovingDao extends CrudDao<MovingOfProduct> {
    List<MovingOfProduct> findAll();

}
