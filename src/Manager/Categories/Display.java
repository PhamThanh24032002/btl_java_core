package Manager.Categories;

import Entity.Category;
import Connection.Connections;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Display {
    private Connection connections = Connections.getConnect();
    public Display() {
    }

    public void Display_all(){
        CallableStatement callableStatement = null;
        String sql = "{call display_category()}";
        ResultSet resultSet = null;

        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.executeQuery();
            resultSet = callableStatement.getResultSet();
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                boolean status = resultSet.getBoolean("status");
                String parentId = resultSet.getString("parentId");
                System.out.println("=============================================");
                System.out.println("-- Id: " + id + " | Name: " + name + " | status: " + status + " --");
                System.out.println("=============================================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void display_by_id(String id_given){
        CallableStatement callableStatement = null;
        String sql = "{call find_cate(?)}";
        ResultSet resultSet = null;
        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.setString(1,id_given);
            callableStatement.executeQuery();
            resultSet = callableStatement.getResultSet();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void renderTree(List<Category> data, String parentId, String tabs, String idx) {
        int index = 1;
        idx = idx + (idx.isEmpty() ? "" : ".");
        for (Category c : data) {
            if (c.getParentId().equals(parentId)) {
                System.out.println(tabs + idx + index + ". " +c.getName());
                if (data.stream().anyMatch(x -> x.getParentId().equals(c.getId()))) {
                    renderTree(data, c.getId(), tabs + "\t", idx + index);
                }
                index++;
            }
        }
    }

}
