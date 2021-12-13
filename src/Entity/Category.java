package Entity;

import Connection.Connections;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Category {
    private String id;
    private String name;
    private boolean status;
    private String parentId;
    private Scanner scanner = new Scanner(System.in);
    private List<Category> categoryList = new ArrayList<>();

    public Category() {
        getData();
        inputData();
    }

    public Category(String check){
    }


    public Category(String id, String name, boolean status, String parentId) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.parentId = parentId;
    }

    public List<Category> getData(){
        Connection connections = Connections.getConnect();
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
                Category category = new Category(id,name,status,parentId);
                categoryList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    public void inputData(){
        inputId();
        inputName();
        inputStatus();
        inputParentId();
    }

    public void inputId(){
//        Mã danh mục – Phải là số nguyên lớn hơn 0, duy nhất.
        boolean check = true;
        do {
            check = true;
            System.out.println("Mời bạn nhập id của danh mục: ");
            String NewId = scanner.nextLine();
            List<Category> check_duplicate_id = categoryList.stream().filter(x->x.getId().equals(NewId)).collect(Collectors.toList());
            if (NewId.isEmpty() || Integer.parseInt(NewId)<=0){
                System.err.println("Mã danh mục không được rỗng và phải lớn hơn 0");
                check = false;
            }
            if (check_duplicate_id.size()>0){
                System.err.println("Mã danh mục này đã tồn tại, vui lòng chọn danh mục khác!!");
                check = false;
            }
            this.setId(NewId);
        }while (!check);
    }

    public void inputName(){
//        Tên danh mục – Phải gồm từ 6-30 ký tự.
        boolean check = true;
        do {
            check = true;
            System.out.println("Mời bạn nhập tên danh mục: ");
            String NewName = scanner.nextLine();

            long check_duplicate_name = categoryList.stream().filter(x->x.getName().equals(NewName)).count();
            if (NewName.isEmpty()){
                System.err.println("Tên danh mục không được rỗng!");
                check = false;
            }
            if (NewName.length()<6 || NewName.length() >30){
                System.err.println("Tên danh mục phải từ 6-30 ký tự!");
                check = false;
            }
            if (check_duplicate_name>0){
                System.err.println("Tên danh mục đã tồn tại!!");
                check = false;
            }
            this.setName(NewName);
        }while (!check);

    }

    public void inputStatus(){
        boolean check = true;
        do {
            check = true;
            System.out.println("Vui lòng nhập trạng thái mới (true/false), mặc định là false: ");
            String newStatus = scanner.nextLine();
            if (!newStatus.contains("true") && !newStatus.contains("false")){
                System.err.println("Trạng thái nhập phải là true/false !!");
                check = false;
            }
            if (newStatus.isEmpty()){
                this.status = false;
            }else {
                this.status = Boolean.parseBoolean(newStatus);
            }
        }while (!check);

    }

    public void inputParentId(){
        boolean check = true;

        do {
            check = true;
            System.out.println("Vui lòng nhập mã danh mục cha, mặc định là 0: ");
            String newParentId = scanner.nextLine();

            long check_existing = categoryList.stream().filter(x->x.getId().equals(newParentId)).count();
            if (newParentId.isEmpty()){
                this.setParentId("0");
                check = true;
            }else {
                if (categoryList.isEmpty()){
                    this.setParentId(newParentId);
                    check = true;
                }else {
                    if (check_existing==0){
                        System.err.println("Danh mục cha này không tồn tại, vui lòng nhập lại!!");
                        check = false;
                    }else{
                        this.setParentId(newParentId);
                        check = true;
                    }
                }
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}
