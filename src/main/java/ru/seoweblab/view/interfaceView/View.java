package ru.seoweblab.view.interfaceView;

import java.util.List;

/**
 * Класс отображает интерфейс пользователя.
 */
public interface View {
    /**
     * Ищет все данные из базы данных и добавляет объекты в список.
     * @return список объектов.
     */
    List<View> findAllView();

    /**
     * Ищет данные по выбранному имени из базы данных и добавляет объекты в список.
     * @return список объектов.
     */
    List<View> findByAllName(String name);
}
