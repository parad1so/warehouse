package ru.seoweblab.dao.domain.documentsDaoJdbc;
import ru.seoweblab.dao.jdbcDao.CrudDao;
import ru.seoweblab.model.UserAccount;

import java.util.List;

public interface UserAccountDao extends CrudDao<UserAccount> {
    boolean isExist(String name, String password);
    List<UserAccount> findAll();
}
