package ru.seoweblab.dao.domain.documentsDaoHibernate;

import ru.seoweblab.dao.hibernateDao.CrudHibernateDao;
import ru.seoweblab.model.UserAccount;

import java.util.List;

public interface UserAccountHibDao extends CrudHibernateDao<UserAccount, Integer> {
    boolean isExist(String name, String password);
    List<UserAccount> findAll();
}
