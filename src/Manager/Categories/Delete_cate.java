package Manager.Categories;

import Connection.Connections;
import Entity.Category;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Delete_cate {
    private Connection connections = Connections.getConnect();
    public Delete_cate() {
    }

    public void Delete(String idDelete){
        CallableStatement callableStatement = null;
        String sql = "{call delete_category(?)}";
        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.setString(1,idDelete);
            int check_insert = callableStatement.executeUpdate();

            if (check_insert >0)
                System.out.printf("Xóa danh mục thành công\n");
            else
                System.out.printf("Không thể xóa danh mục\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
