package Manager.Products;

import Connection.Connections;
import Entity.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Delete_product {
    private Connection connections = Connections.getConnect();
    public Delete_product() {
    }

    public boolean Delete(String IdDelete){
        CallableStatement callableStatement = null;
        String sql = "{call delete_product(?)}";

        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.setString(1,IdDelete);
            boolean check_delete = callableStatement.execute();

            if (check_delete)
                System.out.printf("Xóa thành công 1 sản phẩm!!!\n");
            else
                System.out.printf("Không thể xóa sản phẩm này @@\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
