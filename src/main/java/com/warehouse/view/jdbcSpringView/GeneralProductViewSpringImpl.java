package com.warehouse.view.jdbcSpringView;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.warehouse.view.interfaceView.View;
import com.warehouse.view.jdbcView.GeneralListOfProductViewImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@NoArgsConstructor
public class GeneralProductViewSpringImpl implements View {

    //language=sql
    private final String SELECT_ALL_VIEW ="SELECT * from general_list_of_product";
    //language=sql
    private final String SELECT_ALL_VIEW_BY_NAME = "SELECT * FROM general_list_of_product where product_name = ?";


    JdbcTemplate jdbcTemplate;


    @JsonProperty("vendor_code")
    private  String vendorCode;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("purchase_price")
    private int purchasePrice;
    @JsonProperty("selling_price")
    private int sellingPrice;

    public GeneralProductViewSpringImpl(String vendorCode, String productName, int purchasePrice, int sellingPrice) {
        this.vendorCode = vendorCode;
        this.productName = productName;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
    }

    @Override
    public List<View> findAllView() {
        return jdbcTemplate.query(SELECT_ALL_VIEW, (resultSet, rowNum) -> {
            String vendorCode = resultSet.getString("vendor_code");
            String productName = resultSet.getString("product_name");
            int purchasePrice = resultSet.getInt("purchase_price");
            int sellingPrice = resultSet.getInt("selling_price");
            return new GeneralProductViewSpringImpl(vendorCode, productName, purchasePrice, sellingPrice);
        }
        );
    }



   @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<View> findByAllName(String name) {
        return jdbcTemplate.query(SELECT_ALL_VIEW_BY_NAME, ps -> ps.setString(1,name), (resultSet, rowNum) -> {
            String vendorCode = resultSet.getString("vendor_code");
            String productName = resultSet.getString("product_name");
            int purchasePrice = resultSet.getInt("purchase_price");
            int sellingPrice = resultSet.getInt("selling_price");
           return  new GeneralListOfProductViewImpl(vendorCode, productName, purchasePrice, sellingPrice);
        });
    }
}
