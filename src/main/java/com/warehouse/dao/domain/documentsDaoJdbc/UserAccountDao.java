package com.warehouse.dao.domain.documentsDaoJdbc;
import com.warehouse.dao.jdbcDao.CrudDao;
import com.warehouse.model.UserAccount;

import java.util.List;

public interface UserAccountDao extends CrudDao<UserAccount>{
    boolean isExist(String name, String password);
    List<UserAccount> findAll();
}
