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
public class SaleProductViewImpl implements View {
    Connection connection;

    //language=sql
    private final String SQL_SELECT_ALL_VIEW = "SELECT * from sale_list_product";

    private int numberId, warehouseId, listOfProductId, productId, quantity, price;
    private String productName;
    public SaleProductViewImpl() { }
    public SaleProductViewImpl(int numberId, int warehouseId, int listOfProductId, String productName, int productId, int quantity, int price) {
        this.numberId = numberId;
        this.warehouseId = warehouseId;
        this.listOfProductId = listOfProductId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.productName = productName;
    }
    public List<View> findAllView() {
        try {
            List<View> products = new ArrayList<>();
            connection = ConnectionDataBaseFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_VIEW);
            while (resultSet.next()) {
                int numberId = resultSet.getInt("number_id");
                int warehouseId = resultSet.getInt("warehouse_id");
                int listOfProductId = resultSet.getInt("list_of_product_id");
                String productName = resultSet.getString("product_name");
                int productId = resultSet.getInt("product_id");
                int quantity = resultSet.getInt("quantity");
                int price = resultSet.getInt("price");
                SaleProductViewImpl productView = new SaleProductViewImpl(numberId, warehouseId, listOfProductId, productName, productId, quantity, price);
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
