package com.warehouse.dao.domain.JdbcDaoImpl;
import com.warehouse.dao.domain.documentsDaoJdbc.DocumentsSaleDao;
import com.warehouse.model.SaleOfProduct;
import com.warehouse.service.connection.ConnectionDataBaseFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Не используется, вместо него Hibernate
public class SaleProductDaoImpl implements DocumentsSaleDao {
    Connection connection;
    //language=SQL
    private final String SQL_SELECT_ALL = " SELECT * from sale_of_product";
    //language=sql
    private final String SQL_ADD_PRODUCT = "INSERT INTO sale_of_product(number_id, warehouse_id, list_of_product_id) VALUES (?,?,?)";
    //language=sql
    private final String SQL_DELETE_PRODUCT = "DELETE FROM sale_of_product where id = ? ";
    //language=sql
    private final String SQL_UPDATE_PRODUCT = "UPDATE sale_of_product SET number_id=?, warehouse_id = ?, list_of_product_id= ? WHERE id = ?";

    private boolean checkException = false;


    @Override
    public void save(SaleOfProduct model) {
        try {
            connection = ConnectionDataBaseFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_PRODUCT);
            preparedStatementSaleOfProduct(preparedStatement,model);
            preparedStatement.execute();
        } catch (SQLException e) {
            checkException = true;
            e.printStackTrace();
        }
    }

    @Override
    public void update(SaleOfProduct model, Integer id) {
        try {
            connection = ConnectionDataBaseFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PRODUCT);
           preparedStatementSaleOfProduct(preparedStatement,model);
            preparedStatement.setInt(4, id);
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
    public List<SaleOfProduct> findAll() {
      /*  try {
            List<SaleOfProduct> products = new ArrayList<>();
            connection = ConnectionDataBaseFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                int numberId = resultSet.getInt("number_id");
                int warehouseId = resultSet.getInt("warehouse_id");
                int listOfProductId = resultSet.getInt("list_of_product_id");
                SaleOfProduct saleOfProduct = new SaleOfProduct(numberId, warehouseId,listOfProductId);
                products.add(saleOfProduct);
            }
            return products;
        } catch (SQLException e) {
            checkException = true;
            e.printStackTrace();
        }*/
        return null;
    }

    private void preparedStatementSaleOfProduct(PreparedStatement preparedStatement, SaleOfProduct model) {
    /*    try {
            preparedStatement.setInt(1, model.getNumberId());
            preparedStatement.setInt(2, model.getWarehouseId());
            preparedStatement.setInt(3, model.getListOfProductId());
        } catch (SQLException e) {
            checkException = true;
            e.printStackTrace();
        }*/
    }

    public boolean isCheckException() {
        return checkException;
    }
}
