package com.warehouse.view.interfaceView;

import java.util.List;

public interface HibernateView<Model> {

        /**
         * Ищет все данные из базы данных и добавляет объекты в список.
         * Служит для отображения
         * @return список объектов.
         */
        List<Model> findAllView();

}
