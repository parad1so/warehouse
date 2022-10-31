package com.warehouse.dao.domain.documentsDaoHibernate;

import com.warehouse.dao.hibernateDao.CrudHibernateDao;
import com.warehouse.model.UserAccount;

import java.util.List;

public interface UserAccountHibDao extends CrudHibernateDao<UserAccount, Integer> {
    boolean isExist(String name, String password);
    List<UserAccount> findAll();
}
