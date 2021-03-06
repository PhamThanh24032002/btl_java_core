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
            System.out.println("M???i b???n nh???p id c???a s???n ph???m: ");
            String NewId = scanner.nextLine();
            List<Product> check_duplicate_id = productList.stream().filter(x->x.getId().equals(NewId)).collect(Collectors.toList());
            if (NewId.isEmpty()){
                System.err.println("M?? s???n ph???m kh??ng ???????c r???ng!!");
                check = false;
            }else {
                if (NewId.length()!=4){
                    System.err.println("M?? s???n ph???m bao g???m 4 k?? t???");
                    check = false;
                }
                if (!NewId.startsWith("C")){
                    System.err.println("M?? s???n ph???m ph???i b???t ?????u l?? k?? t??? ???C??? ");
                    check = false;
                }
                if (check_duplicate_id.size()>0){
                    System.err.println("M?? s???n ph???m n??y ???? t???n t???i, vui l??ng ??i???n l???i!");
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
            System.out.println("M???i b???n nh???p t??n s???n ph???m: ");
            String NewName = scanner.nextLine();

            long check_duplicate_name = productList.stream().filter(x->x.getName().equalsIgnoreCase(NewName)).count();
            if (NewName.isEmpty()){
                System.err.println("T??n s???n ph???m kh??ng ???????c r???ng!");
                check = false;
            }else {
                if (NewName.length()<6 || NewName.length() >50){
                    System.err.println("T??n s???n ph???m ph???i t??? 6-50 k?? t???!");
                    check = false;
                }
                if (check_duplicate_name>0){
                    System.err.println("T??n s???n ph???m ???? t???n t???i!!");
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
            System.out.println("Vui l??ng nh???p tr???ng th??i m???i (true/false), m???c ?????nh l?? false: ");
            String newStatus = scanner.nextLine();

            if (newStatus.isEmpty()){
                System.err.println("Tr???ng th??i c???a s???n ph???m kh??ng ???????c r???ng!!");
                check = false;
            }else {
                if (!newStatus.contains("true") && !newStatus.contains("false")){
                    System.err.println("Tr???ng th??i nh???p ph???i l?? true/false !!");
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
            System.out.println("Vui l??ng nh???p gi?? c???a s???n ph???m: ");
            String newPrice = scanner.nextLine();
            if (newPrice.isEmpty()){
                System.err.println("Gi?? s???n ph???m kh??ng ???????c r???ng !!");
                check = false;
            }else{
                String regex = "[+]?[0-9]+";
                if (!newPrice.matches(regex) || Float.parseFloat(newPrice)==0){
                    System.err.println("Gi?? s???n ph???m ph???i l?? s??? v?? kh??ng ???????c ??m!!");
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
            System.out.println("Vui l??ng nh???p gi?? KM c???a s???n ph???m: ");
            String newSalePrice = scanner.nextLine();
            if (newSalePrice.isEmpty()){
                System.err.println("Gi?? KM s???n ph???m kh??ng ???????c r???ng !!");
                check = false;
            }else{
                String regex = "[+]?[0-9]+";
                if (!newSalePrice.matches(regex)){
                    System.err.println("Gi?? s???n ph???m ph???i l?? s??? v?? kh??ng ???????c ??m!!");
                    check = false;
                }
                if (Float.parseFloat(newSalePrice)>=this.getPrice()){
                    System.err.println("Gi?? Khuy???n m???i ph???i nh??? h??n gi?? g???c!!");
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
            System.out.println("Vui l??ng nh???p m?? t??? c???a s???n ph???m: ");
            String newDes = scanner.nextLine();
            if (newDes.isEmpty()){
                System.err.println("M?? t??? c???a s???n ph???m kh??ng ???????c r???ng !!");
                check = false;
            }
            this.setText(newDes);
        }while (!check);
    }

    public void inputCategoryId(){
        boolean check = true;
        do {
            check = true;
            System.out.println("Vui l??ng nh???p m?? c???a danh m???c: ");
            String newCateId = scanner.nextLine();
            if (newCateId.isEmpty()){
                System.err.println("M?? danh m???c kh??ng ???????c r???ng !!");
                check = false;
            }else{
                Category category = new Category("getData");
                List<Category> categoryList = category.getData();
                long checkExistingCateId = categoryList.stream().filter(x->x.getId().equals(newCateId)).count();

                if (checkExistingCateId == 0){
                    System.err.println("M?? danh m???c kh??ng t???n t???i, vui l??ng ki???m tra l???i!!!");
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
