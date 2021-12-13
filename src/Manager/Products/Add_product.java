package Manager.Products;

import Connection.Connections;
import Entity.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Add_product {
    private Connection connections = Connections.getConnect();
    public Add_product() {
    }

    public void add_Pro(Product product){
        String sql = "{call add_product(?,?,?,?,?,?,?)}";
        CallableStatement callableStatement = null;
        try {
            callableStatement = connections.prepareCall(sql);

            callableStatement.setString(1, product.getId());
            callableStatement.setNString(2, product.getName());
            callableStatement.setBoolean(3, product.isStatus());
            callableStatement.setFloat(4, product.getPrice());
            callableStatement.setFloat(5, product.getSale_price());
            callableStatement.setString(6, product.getText());
            callableStatement.setString(7,product.getCategory_id());

            int check_insert = callableStatement.executeUpdate();
            if (check_insert>0)
                System.out.printf("Thêm mới sản phẩm thành công!\n");
            else
                System.out.printf("Thêm mới sản phẩm thất bại :)))\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
