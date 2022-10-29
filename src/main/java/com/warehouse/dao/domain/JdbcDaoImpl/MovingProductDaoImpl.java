package com.warehouse.dao.domain.JdbcDaoImpl;


import com.warehouse.dao.domain.documentsDaoJdbc.DocumentsMovingDao;
import com.warehouse.model.MovingOfProduct;
import com.warehouse.service.connection.ConnectionDataBaseFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Не используется, вместо него Hibernate
public class MovingProductDaoImpl implements DocumentsMovingDao {
    Connection connection;
    //language=SQL
    private final String SQL_SELECT_ALL = " SELECT * from moving_of_product";
    //language=sql
    private final String SQL_ADD_PRODUCT = "INSERT INTO moving_of_product(number_id, warehousea_id, warehouseb_id, list_of_product_id) VALUES (?,?,?,?)";
    //language=sql
    private final String SQL_DELETE_PRODUCT = "DELETE FROM moving_of_product where id = ? ";
    //language=sql
    private final String SQL_UPDATE_PRODUCT = "UPDATE moving_of_product SET number_id=?, warehousea_id = ?, warehouseb_id = ?, list_of_product_id= ? WHERE id = ?";

    private boolean checkException = false;



    @Override
    public void save(MovingOfProduct model) {
        try {
            connection = ConnectionDataBaseFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_PRODUCT);
         /*   preparedStatement.setInt(1, model.getNumberId());
            preparedStatement.setInt(2, model.getWarehouseAId());
            preparedStatement.setInt(3, model.getWarehouseBId());
            preparedStatement.setInt(4, model.getListOfProductId());*/
            preparedStatement.execute();
        } catch (SQLException e) {
            checkException = true;
            e.printStackTrace();
        }
    }

    @Override
    public void update(MovingOfProduct model, Integer id) {
        try {
            connection = ConnectionDataBaseFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PRODUCT);
          /*  preparedStatement.setInt(1, model.getNumberId());
            preparedStatement.setInt(2, model.getWarehouseAId());
            preparedStatement.setInt(3, model.getWarehouseBId());
            preparedStatement.setInt(4, model.getListOfProductId());*/
            preparedStatement.setInt(5, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            checkException = true;
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            connection = ConnectionDataBaseFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PRODUCT);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            checkException = true;
            e.printStackTrace();
        }

    }



    @Override
    public List<MovingOfProduct> findAll() {
        try {
            List<MovingOfProduct> products = new ArrayList<>();
            connection = ConnectionDataBaseFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
            /*    int numberId = resultSet.getInt("number_id");
                int warehouseAId = resultSet.getInt("warehousea_id");
                int warehouseBId = resultSet.getInt("warehouseb_id");
                int listOfProductId = resultSet.getInt("list_of_product_id");
                MovingOfProduct product = new MovingOfProduct(numberId, warehouseAId, warehouseBId, listOfProductId);
                products.add(product);*/
            }
            return products;
        } catch (SQLException e) {
            checkException = true;
            e.printStackTrace();
        }
        return null;
    }

    public boolean isCheckException() {
        return checkException;
    }
}
