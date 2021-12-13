package Manager.Products;

import Connection.Connections;
import Entity.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Update_product {
    private Connection connections = Connections.getConnect();
    public Update_product() {
    }

    public boolean update(Product product,String oldId){
        String sql = "{call update_product(?,?,?,?,?,?,?,?)}";
        CallableStatement callableStatement = null;
        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.setString(1,oldId);
            callableStatement.setString(2, product.getId());
            callableStatement.setNString(3, product.getName());
            callableStatement.setBoolean(4, product.isStatus());
            callableStatement.setFloat(5, product.getPrice());
            callableStatement.setFloat(6, product.getSale_price());
            callableStatement.setString(7, product.getText());
            callableStatement.setString(8,product.getCategory_id());

            int check_insert = callableStatement.executeUpdate();
            if (check_insert>0)
                System.out.printf("Thêm mới sản phẩm thành công!\n");
            else
                System.out.printf("Thêm mới sản phẩm thất bại :)))\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void update_id(String oldId, String newId){
        String sql = "{call update_id_pro(?,?)}";
        CallableStatement callableStatement = null;
        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.setString(1,oldId);
            callableStatement.setString(2, newId);

            int check_insert = callableStatement.executeUpdate();
            if (check_insert>0)
                System.out.printf("Cập nhật id sản phẩm thành công!\n");
            else
                System.out.printf("Cập nhật id sản phẩm thất bại :)))\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update_name(String oldId, String newName){
        String sql = "{call update_name_pro(?,?)}";
        CallableStatement callableStatement = null;
        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.setString(1,oldId);
            callableStatement.setString(2, newName);

            int check_insert = callableStatement.executeUpdate();
            if (check_insert>0)
                System.out.printf("Cập nhật tên sản phẩm thành công!\n");
            else
                System.out.printf("Cập nhật tên sản phẩm thất bại :)))\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update_status(String oldId, boolean newStatus){
        String sql = "{call update_status_pro(?,?)}";
        CallableStatement callableStatement = null;
        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.setString(1,oldId);
            callableStatement.setBoolean(2, newStatus);

            int check_insert = callableStatement.executeUpdate();
            if (check_insert>0)
                System.out.printf("Cập nhật trạng thái sản phẩm thành công!\n");
            else
                System.out.printf("Cập nhật trạng thái sản phẩm thất bại :)))\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update_price(String oldId, float newPrice){
        String sql = "{call update_price_pro(?,?)}";
        CallableStatement callableStatement = null;
        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.setString(1,oldId);
            callableStatement.setFloat(2, newPrice);

            int check_insert = callableStatement.executeUpdate();
            if (check_insert>0)
                System.out.printf("Cập nhật giá sản phẩm thành công!\n");
            else
                System.out.printf("Cập nhật giá sản phẩm thất bại :)))\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update_sale_price(String oldId, float newSalePrice){
        String sql = "{call update_sale_price_pro(?,?)}";
        CallableStatement callableStatement = null;
        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.setString(1,oldId);
            callableStatement.setFloat(2, newSalePrice);

            int check_insert = callableStatement.executeUpdate();
            if (check_insert>0)
                System.out.printf("Cập nhật giá khuyến mại sản phẩm thành công!\n");
            else
                System.out.printf("Cập nhật giá khuyến mại sản phẩm thất bại :)))\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update_des_pro(String oldId, String newDes){
        String sql = "{call update_des_pro(?,?)}";
        CallableStatement callableStatement = null;
        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.setString(1,oldId);
            callableStatement.setString(2, newDes);

            int check_insert = callableStatement.executeUpdate();
            if (check_insert>0)
                System.out.printf("Cập nhật mô tả sản phẩm thành công!\n");
            else
                System.out.printf("Cập nhật mô tả sản phẩm thất bại :)))\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update_cate_id_pro(String oldId, String newCateId){
        String sql = "{call update_cate_id_pro(?,?)}";
        CallableStatement callableStatement = null;
        try {
            callableStatement = connections.prepareCall(sql);
            callableStatement.setString(1,oldId);
            callableStatement.setString(2, newCateId);

            int check_insert = callableStatement.executeUpdate();
            if (check_insert>0)
                System.out.printf("Cập nhật mã danh mục của sản phẩm thành công!\n");
            else
                System.out.printf("Cập nhật mã danh mục của sản phẩm thất bại :)))\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
