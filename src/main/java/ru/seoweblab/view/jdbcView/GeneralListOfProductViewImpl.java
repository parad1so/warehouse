package ru.seoweblab.view.jdbcView;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.seoweblab.service.connection.ConnectionDataBaseFactory;
import ru.seoweblab.view.interfaceView.View;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneralListOfProductViewImpl implements View {

    Connection connection;
    //language=sql
    private final String SELECT_ALL_VIEW ="SELECT * from general_list_of_product";
    //language=sql
    private final String SELECT_ALL_VIEW_BY_NAME = "SELECT * FROM general_list_of_product where product_name = ?";

    @JsonProperty("vendor_code")
    private  String vendorCode;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("purchase_price")
    private int purchasePrice;
    @JsonProperty("selling_price")
    private int sellingPrice;


    public GeneralListOfProductViewImpl() {}

    public GeneralListOfProductViewImpl(String vendorCode, String productName, int purchasePrice, int sellingPrice) {
        this.vendorCode = vendorCode;
        this.productName = productName;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
    }

    @Override
    public List<View> findAllView() {
        try {
            List<View> products = new ArrayList<>();
            connection = ConnectionDataBaseFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_VIEW);
            return getViews(products, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<View> getViews(List<View> products, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String vendorCode = resultSet.getString("vendor_code");
            String productName = resultSet.getString("product_name");
            int purchasePrice = resultSet.getInt("purchase_price");
            int sellingPrice = resultSet.getInt("selling_price");
            GeneralListOfProductViewImpl productView = new GeneralListOfProductViewImpl(vendorCode, productName, purchasePrice, sellingPrice);
            products.add(productView);
        }
        return products;
    }

    @Override
    public List<View> findByAllName(String name) {
        try {
            List<View> products = new ArrayList<>();
            connection = ConnectionDataBaseFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_VIEW_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return getViews(products, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
