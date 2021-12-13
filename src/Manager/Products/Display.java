package Manager.Products;

import Connection.Connections;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Display {
    private Connection connections = Connections.getConnect();
    public Display() {
    }

    public void Display_all(){
        CallableStatement callableStatement = null;
        String sql = "{call display_product()}";
        ResultSet resultSet = null;
        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.executeQuery();
            resultSet = callableStatement.getResultSet();
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                boolean status = resultSet.getBoolean("status");
                float price = resultSet.getFloat("price");
                float sale_price = resultSet.getFloat("sale_price");
                String description = resultSet.getString("description");
                String CateName = resultSet.getString("cateName");
                System.out.println("=============================================");
                System.out.println("-- Id: " + id + " | Name: " + name + " | price: " + price +
                        " | sale_price: " + sale_price + " | description: " + description +
                        " | created_at: " + resultSet.getDate("created_at") +
                        " | Category: "  + CateName  + " | status: " + (status==true?"Active":"Non Active") + " --");
                System.out.println("=============================================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Display_by_name(String name_given){
        CallableStatement callableStatement = null;
        String sql = "{call display_by_name(?)}";
        ResultSet resultSet = null;
        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.setString(1,name_given);
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

    public void soft_pro_by_price_desc(){
        CallableStatement callableStatement = null;
        String sql = "{call sort_pro_by_price_desc()}";
        ResultSet resultSet = null;
        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.executeQuery();
            resultSet = callableStatement.getResultSet();
            System.out.println("Sản phẩm theo giá giảm dần");
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                boolean status = resultSet.getBoolean("status");
                float price = resultSet.getFloat("price");
                float sale_price = resultSet.getFloat("sale_price");
                String description = resultSet.getString("description");
                String CateName = resultSet.getString("cateName");
                System.out.println("=============================================");
                System.out.println("-- Id: " + id + " | Name: " + name + " | price: " + price +
                        " | sale_price: " + sale_price + " | description: " + description +
                        " | created_at: " + resultSet.getDate("created_at") +
                        " | Category: "  + CateName  + " | status: " + (status==true?"Active":"Non Active") + " --");
                System.out.println("=============================================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void soft_pro_by_price_asc(){
        CallableStatement callableStatement = null;
        String sql = "{call sort_pro_by_price_asc()}";
        ResultSet resultSet = null;
        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.executeQuery();
            resultSet = callableStatement.getResultSet();
            System.out.println("Sản phẩm theo giá tăng dần");
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                boolean status = resultSet.getBoolean("status");
                float price = resultSet.getFloat("price");
                float sale_price = resultSet.getFloat("sale_price");
                String description = resultSet.getString("description");
                String CateName = resultSet.getString("cateName");
                System.out.println("=============================================");
                System.out.println("-- Id: " + id + " | Name: " + name + " | price: " + price +
                        " | sale_price: " + sale_price + " | description: " + description +
                        " | created_at: " + resultSet.getDate("created_at") +
                        " | Category: "  + CateName  + " | status: " + (status==true?"Active":"Non Active") + " --");
                System.out.println("=============================================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
