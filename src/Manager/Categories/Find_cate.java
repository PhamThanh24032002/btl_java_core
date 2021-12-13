package Manager.Categories;

import Connection.Connections;
import Entity.Category;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Find_cate {
    private Connection connections = Connections.getConnect();
    public Find_cate() {
    }

    public boolean find_cate(String id_wanted){
        CallableStatement callableStatement = null;
        String sql = "{call find_cate(?)}";
        ResultSet resultSet = null;
        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.setString(1,id_wanted);
            callableStatement.executeQuery();
            resultSet = callableStatement.getResultSet();
            if (!resultSet.isBeforeFirst()){
                System.out.println("Không thể tìm thấy danh mục, vui lòng thử lại sau!!");
                return false;
            }else{
                while (resultSet.next()){
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    boolean status = resultSet.getBoolean("status");
                    String parentId = resultSet.getString("parentId");
                    System.out.println("=============================================");
                    System.out.println("-- Id: " + id + " | Name: " + name + " | status: " + status + " --");
                    System.out.println("=============================================");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean find_cate_by_name(String name_wanted){
        CallableStatement callableStatement = null;
        String sql = "{call find_cate_by_name(?)}";
        ResultSet resultSet = null;
        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.setString(1,name_wanted);
            callableStatement.executeQuery();
            resultSet = callableStatement.getResultSet();
            if (!resultSet.isBeforeFirst()){
                System.out.println("Không thể tìm thấy danh mục, vui lòng thử lại sau!!");
                return false;
            }else{
                while (resultSet.next()){
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    boolean status = resultSet.getBoolean("status");
                    String parentId = resultSet.getString("parentId");
                    System.out.println("=============================================");
                    System.out.println("-- Id: " + id + " | Name: " + name + " | status: " + status + " --");
                    System.out.println("=============================================");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
