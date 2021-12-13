package Entity;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import Connection.Connections;
public class Product {
    private String id;
    private String name;
    private boolean status;
    private float price;
    private float sale_price;
    private String text;
    private String category_id;
    private Scanner scanner = new Scanner(System.in);
    private static List<Product> productList = new ArrayList<>();
    public Product() {
    }

    public Product(String id, String name, boolean status, float price, float sale_price, String text, String category_id) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.price = price;
        this.sale_price = sale_price;
        this.text = text;
        this.category_id = category_id;
    }

    public static List<Product> getData(){
        Connection connections = Connections.getConnect();
        CallableStatement callableStatement = null;
        String sql = "{call display_all()}";
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
                String category_id = resultSet.getString("category_id");
                Product product = new Product(id,name,status,price,sale_price,description,category_id);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public void inputData(){
        getData();
        inputId();
        inputName();
        inputStatus();
        inputPrice();
        inputSalePrice();
        inputText();
        inputCategoryId();
    }

    public void inputId(){
        boolean check = true;
        do {
            check = true;
            System.out.println("Mời bạn nhập id của sản phẩm: ");
            String NewId = scanner.nextLine();
            List<Product> check_duplicate_id = productList.stream().filter(x->x.getId().equals(NewId)).collect(Collectors.toList());
            if (NewId.isEmpty()){
                System.err.println("Mã sản phẩm không được rỗng!!");
                check = false;
            }else {
                if (NewId.length()!=4){
                    System.err.println("Mã sản phẩm bao gồm 4 ký tự");
                    check = false;
                }
                if (!NewId.startsWith("C")){
                    System.err.println("Mã sản phẩm phải bắt đầu là ký tự “C” ");
                    check = false;
                }
                if (check_duplicate_id.size()>0){
                    System.err.println("Mã sản phẩm này đã tồn tại, vui lòng điền lại!");
                    check = false;
                }
                this.setId(NewId);
            }
        }while (!check);
    }

    public void inputName(){
        boolean check = true;
        do {
            check = true;
            System.out.println("Mời bạn nhập tên sản phẩm: ");
            String NewName = scanner.nextLine();

            long check_duplicate_name = productList.stream().filter(x->x.getName().equalsIgnoreCase(NewName)).count();
            if (NewName.isEmpty()){
                System.err.println("Tên sản phẩm không được rỗng!");
                check = false;
            }else {
                if (NewName.length()<6 || NewName.length() >50){
                    System.err.println("Tên sản phẩm phải từ 6-50 ký tự!");
                    check = false;
                }
                if (check_duplicate_name>0){
                    System.err.println("Tên sản phẩm đã tồn tại!!");
                    check = false;
                }
                this.setName(NewName);
            }
        }while (!check);

    }

    public void inputStatus(){
        boolean check = true;
        do {
            check = true;
            System.out.println("Vui lòng nhập trạng thái mới (true/false), mặc định là false: ");
            String newStatus = scanner.nextLine();

            if (newStatus.isEmpty()){
                System.err.println("Trạng thái của sản phẩm không được rỗng!!");
                check = false;
            }else {
                if (!newStatus.contains("true") && !newStatus.contains("false")){
                    System.err.println("Trạng thái nhập phải là true/false !!");
                    check = false;
                }
                this.status = Boolean.parseBoolean(newStatus);
            }
        }while (!check);

    }

    public void inputPrice(){
        boolean check = true;
        do {
            check = true;
            System.out.println("Vui lòng nhập giá của sản phẩm: ");
            String newPrice = scanner.nextLine();
            if (newPrice.isEmpty()){
                System.err.println("Giá sản phẩm không được rỗng !!");
                check = false;
            }else{
                String regex = "[+]?[0-9]+";
                if (!newPrice.matches(regex) || Float.parseFloat(newPrice)==0){
                    System.err.println("Giá sản phẩm phải là số và không được âm!!");
                    check = false;
                }
                this.setPrice(Float.parseFloat(newPrice));
            }
        }while (!check);
    }

    public void inputSalePrice(){
        boolean check = true;
        do {
            check = true;
            System.out.println("Vui lòng nhập giá KM của sản phẩm: ");
            String newSalePrice = scanner.nextLine();
            if (newSalePrice.isEmpty()){
                System.err.println("Giá KM sản phẩm không được rỗng !!");
                check = false;
            }else{
                String regex = "[+]?[0-9]+";
                if (!newSalePrice.matches(regex)){
                    System.err.println("Giá sản phẩm phải là số và không được âm!!");
                    check = false;
                }
                if (Float.parseFloat(newSalePrice)>=this.getPrice()){
                    System.err.println("Giá Khuyến mại phải nhỏ hơn giá gốc!!");
                    check = false;
                }
                this.setSale_price(Float.parseFloat(newSalePrice));
            }
        }while (!check);
    }

    public void inputText(){
        boolean check = true;
        do {
            check = true;
            System.out.println("Vui lòng nhập mô tả của sản phẩm: ");
            String newDes = scanner.nextLine();
            if (newDes.isEmpty()){
                System.err.println("Mô tả của sản phẩm không được rỗng !!");
                check = false;
            }
            this.setText(newDes);
        }while (!check);
    }

    public void inputCategoryId(){
        boolean check = true;
        do {
            check = true;
            System.out.println("Vui lòng nhập mã của danh mục: ");
            String newCateId = scanner.nextLine();
            if (newCateId.isEmpty()){
                System.err.println("Mã danh mục không được rỗng !!");
                check = false;
            }else{
                Category category = new Category("getData");
                List<Category> categoryList = category.getData();
                long checkExistingCateId = categoryList.stream().filter(x->x.getId().equals(newCateId)).count();

                if (checkExistingCateId == 0){
                    System.err.println("Mã danh mục không tồn tại, vui lòng kiểm tra lại!!!");
                    check = false;
                }
                this.setCategory_id(newCateId);
            }
        }while (!check);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getSale_price() {
        return sale_price;
    }

    public void setSale_price(float sale_price) {
        this.sale_price = sale_price;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", price=" + price +
                ", sale_price=" + sale_price +
                ", text='" + text + '\'' +
                ", category_id='" + category_id + '\'' +
                '}';
    }
}
