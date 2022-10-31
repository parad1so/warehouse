<p align="center"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Spring_Framework_Logo_2018.svg/1280px-Spring_Framework_Logo_2018.svg.png" 
 alt="Spring"  height="100"  />
  <img src = "https://www.svgrepo.com/show/13238/plus-cross.svg"
   alt="Spring"  height="100" />
 <img src="https://alexkosarev.name/wp-content/uploads/2019/01/spring_boot_logo.png" 
  alt="Spring Boot"  height="100"  />
  
 
 </p>
<p align="right"></p>

# Spring app по учету товаров на складе

## Суть проекта
Имеется веб-приложение которое отправляет данные о списках товаров в формате json. После отправки, дать возможность пользователю получить отчет в формате json, которые будут упакованы в zip-архив. 

## Создание БД
Запустите скрипт для создания базы данных по пути: src/test/sql/createDb.sql 

## Разработка
- Язык разработки: **Java 8**
- Фреймворк: **Spring 5.1.14 (есть, но не используется), Spring Boot 2.2.6**
- Сурбд: **PostgreSQL**
- JPA: **Hibernate 5.3.14.Final, Spring Data Jpa 2.1.16.RELEASE**
- JDBC: **Spring JDBC 5.1.14.RELEASE**
- Security: **Spring Security 5.1.9.RELEASE**
- Сборка: **Maven**
## API

Это веб приложение себя включает:
- Авторизация
- Аутентификация
- Работа с базой данных: _просмотр_, _создание_, _редактирование_ и _удаление сущностей_
- Конвертер _json to javaObject_ с помощью фреймворка **Jackson**
- Хеширование паролей с помощью фреймворка **Spring Security Bcrypt**
- Архивация любых файлов в формате **zip**
- Загрузка и скачивания документов в базе данных
- Работа с каталогом: _удаление_ или _создание_ каталога, _получение имени файлов в данном_ катологе, _получение имени католога_ типа **String** или **File**

## Application Deployment
Данное приложение развертывалось при помощи **Apache Tomcat 8.5.50** по адресу __localhost:8080__
- application context: "/" - устанавливаете
