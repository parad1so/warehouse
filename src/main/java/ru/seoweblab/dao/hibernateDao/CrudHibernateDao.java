package ru.seoweblab.dao.hibernateDao;

public interface CrudHibernateDao<Entity, Id> {
    void save (Entity model);
    void update (Entity model);
    void delete (Entity model);
    Entity read (Id modelId);
}
