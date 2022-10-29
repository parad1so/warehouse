package com.warehouse.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


//Не используется

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    protected static SessionFactory buildSessionFactory() {
        // SessionFactory устанавливается один раз для приложения!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() //настраивает настройки из hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
           /* Реестр будет уничтожен SessionFactory, но у нас возникли проблемы при создании SessionFactory,
                    поэтому уничтожьте его вручную.*/
            StandardServiceRegistryBuilder.destroy( registry );

            throw new ExceptionInInitializerError("Initial SessionFactory failed" + e);
        }
        return sessionFactory;
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Закрывает кэши и пулы соединений
        getSessionFactory().close();
    }
}
