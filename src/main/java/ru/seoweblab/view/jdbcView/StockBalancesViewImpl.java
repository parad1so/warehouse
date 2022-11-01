package ru.seoweblab.view.jdbcView;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.seoweblab.service.connection.ConnectionDataBaseFactory;
import ru.seoweblab.view.interfaceView.View;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockBalancesViewImpl implements View {

    Connection connection;

    //language=sql
    private final String SELECT_ALL_VIEW ="SELECT * from stock_balances";
    //language=sql
    private final String SELECT_ALL_VIEW_BY_ID = "SELECT * FROM stock_balances where warehouse_name = ?";

    @JsonProperty("vendor_code")
    private String vendorCode;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("warehouse_name")
    private String warehouseName;

    public StockBalancesViewImpl() {}

    public StockBalancesViewImpl(String vendorCode, String productName, String warehouseName) {
        this.vendorCode = vendorCode;
        this.productName = productName;
        this.warehouseName = warehouseName;
    }

    @Override
    public List<View> findAllView() {

        try {
            List<View> products = new ArrayList<>();
            connection = ConnectionDataBaseFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_VIEW);
            while (resultSet.next()) {
                String vendorCode = resultSet.getString("vendor_code");
                String productName = resultSet.getString("product_name");
                String warehouseId = resultSet.getString("warehouse_name");
                StockBalancesViewImpl productView = new StockBalancesViewImpl(vendorCode, productName, warehouseId);
                products.add(productView);
            }
            return products;
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<View> findByAllName(String name) {
        try {
            List<View> products = new ArrayList<>();
            connection = ConnectionDataBaseFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_VIEW_BY_ID);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String vendorCode = resultSet.getString("vendor_code");
                String productName = resultSet.getString("product_name");
                String warehouseId = resultSet.getString("warehouse_name");
                StockBalancesViewImpl productView = new StockBalancesViewImpl(vendorCode, productName, warehouseId);
                products.add(productView);
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
