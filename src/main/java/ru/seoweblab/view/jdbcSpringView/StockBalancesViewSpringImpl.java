package ru.seoweblab.view.jdbcSpringView;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.seoweblab.view.interfaceView.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockBalancesViewSpringImpl implements View {

    //language=sql
    private final String SELECT_ALL_VIEW ="SELECT * from stock_balances";
    //language=sql
    private final String SELECT_ALL_VIEW_BY_ID = "SELECT * FROM stock_balances where warehouse_name = ?";

    JdbcTemplate jdbcTemplate;

    @JsonProperty("vendor_code")
    private String vendorCode;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("warehouse_name")
    private String warehouseName;

    public StockBalancesViewSpringImpl() {}

    public StockBalancesViewSpringImpl(String vendorCode, String productName, String warehouseName) {
        this.vendorCode = vendorCode;
        this.productName = productName;
        this.warehouseName = warehouseName;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<View> findAllView() {
        return jdbcTemplate.query(SELECT_ALL_VIEW, (resultSet, rowNum) -> {
            String vendorCode = resultSet.getString("vendor_code");
            String productName = resultSet.getString("product_name");
            String warehouseId = resultSet.getString("warehouse_name");
            return new StockBalancesViewSpringImpl(vendorCode, productName, warehouseId);
        });
    }

    @Override
    public List<View> findByAllName(String name) {
        return jdbcTemplate.query(SELECT_ALL_VIEW_BY_ID, ps -> ps.setString(1, name), (resultSet, rowNum) -> {
            String vendorCode = resultSet.getString("vendor_code");
            String productName = resultSet.getString("product_name");
            String warehouseId = resultSet.getString("warehouse_name");
            return new StockBalancesViewSpringImpl(vendorCode, productName, warehouseId);
        });
    }
}
