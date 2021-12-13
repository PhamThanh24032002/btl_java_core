package Manager.Categories;

import Connection.Connections;
import Entity.Category;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Update_cate {
    private Connection connections = Connections.getConnect();
    public Update_cate() {
    }

    public boolean update(Category category,String oldId){
        CallableStatement callableStatement = null;
        String sql = "{call edit_category(?,?,?,?,?)}";

        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.setString(1,oldId);
            callableStatement.setString(2,category.getId());
            callableStatement.setNString(3,category.getName());
            callableStatement.setBoolean(4,category.isStatus());
            callableStatement.setString(5,category.getParentId());

            int check_update = callableStatement.executeUpdate();
            if (check_update>0)
                System.out.printf("Cập nhật thành công!!\n");
            else
                System.out.printf("Cập nhật thất bại @@\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
