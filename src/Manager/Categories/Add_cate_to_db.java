package Manager.Categories;

import Connection.Connections;
import Entity.Category;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Add_cate_to_db {
    private Connection connections = Connections.getConnect();

    public Add_cate_to_db() {
    }

    public void Add_cate(Category category){
        CallableStatement callableStatement = null;
        String sql = "{call add_category(?,?,?,?)}";
        try {
            callableStatement = connections.prepareCall(sql);

            callableStatement.setString(1,category.getId());
            callableStatement.setNString(2,category.getName());
            callableStatement.setBoolean(3,category.isStatus());
            callableStatement.setString(4,category.getParentId());

            int check_insert = callableStatement.executeUpdate();
            if (check_insert >0)
                System.out.printf("Đã thêm mới 1 danh mục\n");
            else
                System.out.printf("Không thể thêm mới danh mục\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
