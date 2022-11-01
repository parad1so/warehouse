package ru.seoweblab.view.jdbcView;

import ru.seoweblab.service.connection.ConnectionDataBaseFactory;
import ru.seoweblab.view.interfaceView.View;
import lombok.Getter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MovingProductViewImpl implements View {
    Connection connection;
    private int numberId, warehouseAId, warehouseBId, listOfProductId, productId, quantity;
    private String productName;
    //language=sql
    private final String SQL_SELECT_ALL_VIEW = "SELECT * from moving_list_product";

    public MovingProductViewImpl() {
    }

    public MovingProductViewImpl(int numberId, int warehouseAId, int warehouseBId, int listOfProductId, int productId, int quantity, String productName) {
        this.numberId = numberId;
        this.warehouseAId = warehouseAId;
        this.warehouseBId = warehouseBId;
        this.listOfProductId = listOfProductId;
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
    }

    @Override
    public List<View> findAllView() {
        try {
            List<View> products = new ArrayList<>();
            connection = ConnectionDataBaseFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_VIEW);
            while (resultSet.next()) {
                int numberId = resultSet.getInt("number_id");
                int warehouseAId = resultSet.getInt("warehousea_id");
                int warehouseBId = resultSet.getInt("warehouseb_id");
                int listOfProductId = resultSet.getInt("list_of_product_id");
                String productName = resultSet.getString("product_name");
                int productId = resultSet.getInt("product_id");
                int quantity = resultSet.getInt("quantity");
                MovingProductViewImpl productView = new MovingProductViewImpl(numberId, warehouseAId, warehouseBId, listOfProductId, productId, quantity, productName);
                products.add(productView);
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<View> findByAllName(String name) {
        return null;
    }
}
