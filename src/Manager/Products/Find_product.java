package Manager.Products;

import Connection.Connections;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Find_product {
    private Connection connections = Connections.getConnect();
    public Find_product() {

    }

    public boolean find(String id_wanted){
        CallableStatement callableStatement = null;
        String sql = "{call find_product(?)}";
        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.setString(1,id_wanted);

            boolean check = callableStatement.execute();

            if (check){
                System.out.printf("Đã tìm thấy sp có id là: " + id_wanted);
                System.out.printf("Nhấn 1 để hiển thị, 0 để thoát");
            }else
                System.out.printf("K tìm thấy sản phẩm này");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean find_by_name(String name_wanted){
        CallableStatement callableStatement = null;
        String sql = "{call find_pro_by_name(?)}";
        ResultSet resultSet = null;
        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.setString(1,name_wanted);

            callableStatement.execute();
            resultSet = callableStatement.getResultSet();

            if (resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    boolean status = resultSet.getBoolean("status");
                    float price = resultSet.getFloat("price");
                    float sale_price = resultSet.getFloat("sale_price");
                    String description = resultSet.getString("description");
                    String category_id = resultSet.getString("category_id");
                    System.out.println("=============================================");
                    System.out.println("-- Id: " + id + " | Name: " + name + " | price: " + price +
                            " | sale_price: " + sale_price + " | description: " + description +
                            " | created_at: " + resultSet.getDate("created_at") + " | Category: "  + category_id  + " | status: " + status + " --");
                    System.out.println("=============================================");
                }
            }else
                System.out.printf("K tìm thấy sản phẩm có tên là " + name_wanted + "!! \n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
