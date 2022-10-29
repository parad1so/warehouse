package com.warehouse.dao.jdbcDao;

public interface CrudDao<T> {
    void save (T model);
    void update (T model, Integer id);
    void delete (Integer id);
}
