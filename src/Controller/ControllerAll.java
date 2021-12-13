package Controller;

import Entity.Category;
import Entity.Product;
import Manager.Categories.Add_cate_to_db;
import Manager.Categories.Delete_cate;
import Manager.Categories.Display;
import Manager.Categories.Find_cate;
import Manager.Products.Add_product;
import Manager.Products.Find_product;
import Manager.Products.Update_product;
import Views.Program;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControllerAll {
    private static boolean check = true;
    private static String regex = "[+]?[0-9]+";
    public static void category_Menu(Locale local) {
        Scanner sc = new Scanner(System.in);
        ResourceBundle bundle = null;

        while (true) {
            bundle = ResourceBundle.getBundle("Lang.language", local);
            System.out.println(bundle.getString("category_Menu_Frame"));
            System.out.println(bundle.getString("category_Menu_1"));
            System.out.println(bundle.getString("category_Menu_2"));
            System.out.println(bundle.getString("category_Menu_3"));
            System.out.println(bundle.getString("category_Menu_4"));
            System.out.println(bundle.getString("category_Menu_5"));
            System.out.print(bundle.getString("choose"));
            int category_Menu_Choice = Integer.parseInt(sc.nextLine());

            switch (category_Menu_Choice) {
                case 1:
                    category_Display(local);
                    break;
                case 2:
                    category_Add(local);
                    break;
                case 3:
                    category_Delete(local);
                    break;
                case 4:
                    category_Find_By_Name(local);
                    break;
                case 5:
                    Program program = new Program();
                    program.main_Menu(local);
                default:
                    System.err.println("Who?");
                    break;
            }
        }
    }

    public static void category_Display(Locale local) {
        Scanner sc = new Scanner(System.in);
        ResourceBundle bundle = null;

        while (true) {
            bundle = ResourceBundle.getBundle("Lang.language", local);
            System.out.println(bundle.getString("category_Display_Frame"));
            System.out.println(bundle.getString("category_Display_1"));
            System.out.println(bundle.getString("category_Display_2"));
            System.out.println(bundle.getString("category_Display_3"));
            do {
                System.out.print(bundle.getString("choose"));
                String category_Display_Choice = sc.nextLine();
                if (category_Display_Choice.isEmpty()){
                    System.err.println("Vui lòng điền lựa chọn của bạn. ");
                    check = false;
                }else {
                    if (!category_Display_Choice.matches(regex)){
                        System.err.println("Lựa chọn của bạn phải là số nguyên. Vui lòng điền lại!!");
                        check = false;
                    }else {
                        switch (Integer.parseInt(category_Display_Choice)) {
                            case 1:
                                //ham hien thi danh sach cay danh muc
                                Display display = new Display();
                                Category category = new Category("GetData");
                                List<Category> data = category.getData();
                                display.renderTree(data, "0", "", "");
                                break;
                            case 2:
                                // tim danh muc theo id
                                find_cate_by_id();
                                break;
                            case 3:
                                category_Menu(local);
                                break;
                            default:
                                System.err.println("Who?");
                                break;
                        }
                    }
                }
            }while (!check);

        }
    }
    public static void find_cate_by_id(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mời bạn nhập id danh mục để xem chi tiết: ");
        String id_wanted = scanner.nextLine();
        Find_cate find_cate = new Find_cate();
        find_cate.find_cate(id_wanted);
    }
    public static void category_Add(Locale local) {
        Scanner scanner = new Scanner(System.in);

        boolean check = true;
        String total;
        do {
            check = true;
            System.out.println("Mời bạn nhập số lượng danh mục cần thêm");
            total = scanner.nextLine();
            String regex = "[+]?[0-9]+";

            if (total.isEmpty()) {
                System.out.println("Vui lòng điền số lượng danh mục!!");
                check = false;
            }else {
                if (!total.matches(regex)) {
                    System.err.println("Số lượng phải là số nguyên !!");
                    check = false;
                }
                if (Integer.parseInt(total)==0){
                    System.err.println("Số lượng ít nhất phải là 1");
                    check = false;
                }
            }
        } while (!check);
        System.out.println(total);
        for (int i = 0; i < Integer.parseInt(total); i++) {
            System.out.println("Danh mục thứ " + (i+1));
            Category category = new Category();
            Add_cate_to_db add_cate_to_db = new Add_cate_to_db();
            add_cate_to_db.Add_cate(category);
        }

    }

    public static void category_Delete(Locale local) {
        Scanner sc = new Scanner(System.in);
        ResourceBundle bundle = null;
        bundle = ResourceBundle.getBundle("Lang.language", local);
        System.out.println(bundle.getString("category_Delete_Frame"));

        System.out.println(bundle.getString("category_Delete_1"));
        String cateID = sc.nextLine();
        //ham xoa danh muc
        Find_cate find_cate = new Find_cate();
        if (find_cate.find_cate(cateID)){
            Delete_cate delete_cate = new Delete_cate();
            delete_cate.Delete(cateID);
        }else
            System.out.println("Không thể tìm thấy danh mục");
    }

    public static void category_Find_By_Name(Locale local) {
        Scanner sc = new Scanner(System.in);
        ResourceBundle bundle = null;
        bundle = ResourceBundle.getBundle("Lang.language", local);
        System.out.println(bundle.getString("category_Find_By_Name_Frame"));
        System.out.println(bundle.getString("category_Find_By_Name"));
        String cateName = sc.nextLine();
        //ham tim danh muc
        Find_cate find_cate = new Find_cate();
        find_cate.find_cate_by_name(cateName);
    }

    public static void product_Menu(Locale local) {
        Scanner sc = new Scanner(System.in);
        ResourceBundle bundle = null;

        while (true) {
            bundle = ResourceBundle.getBundle("Lang.language", local);
            System.out.println(bundle.getString("product_Menu_Frame"));
            System.out.println(bundle.getString("product_Menu_1"));
            System.out.println(bundle.getString("product_Menu_2"));
            System.out.println(bundle.getString("product_Menu_3"));
            System.out.println(bundle.getString("product_Menu_4"));
            System.out.println(bundle.getString("product_Menu_5"));
            System.out.print(bundle.getString("choose"));
            int category_Menu_Choice = Integer.parseInt(sc.nextLine());

            switch (category_Menu_Choice) {
                case 1:
                    product_Add(local);
                    break;
                case 2:
                    product_Info(local);
                    break;
                case 3:
                    product_Sort(local);
                    break;
                case 4:
                    product_Edit(local);
                    break;
                case 5:
                    Program program = new Program();
                    program.main_Menu(local);
                default:
                    System.err.println("Who?");
                    break;
            }
        }
    }

    public static void product_Edit(Locale local) {
        Scanner sc = new Scanner(System.in);
        ResourceBundle bundle = null;

        bundle = ResourceBundle.getBundle("Lang.language", local);
        System.out.println(bundle.getString("product_Edit_Frame"));

        System.out.println(bundle.getString("product_Edit_1"));
        String proID = sc.next();
        product_Edit_Info(local, proID);
    }

    public static void product_Edit_Info(Locale local, String proID) {
        Scanner sc = new Scanner(System.in);
        ResourceBundle bundle = null;
        while (true) {
            bundle = ResourceBundle.getBundle("Lang.language", local);
            System.out.println(bundle.getString("product_Edit_Frame_2"));
            System.out.println(bundle.getString("product_Edit_2"));
            System.out.println(bundle.getString("product_Edit_3"));
            System.out.println(bundle.getString("product_Edit_4"));
            System.out.println(bundle.getString("product_Edit_5"));
            System.out.println(bundle.getString("product_Edit_6"));
            System.out.println(bundle.getString("product_Edit_7"));
            System.out.println(bundle.getString("product_Edit_8"));
            System.out.println(bundle.getString("product_Edit_9"));
            System.out.println(bundle.getString("product_done_edit"));
            System.out.print(bundle.getString("choose"));
            int product_Sort_Choice = Integer.parseInt(sc.nextLine());
            Product updateNewPr = new Product();
            Update_product update_product = new Update_product();
            updateNewPr.getData();
            switch (product_Sort_Choice) {
                case 1:
                    //ham update id moi
                    updateNewPr.inputId();
                    //update bên db
                    update_product.update_id(proID,updateNewPr.getId());
                    break;
                case 2:
                    //ham update ten moi
                    updateNewPr.inputName();
                    update_product.update_name(proID,updateNewPr.getName());
                    break;
                case 3:
                    //ham update status moi
                    updateNewPr.inputStatus();
                    update_product.update_status(proID,updateNewPr.isStatus());
                    break;
                case 4:
                    updateNewPr.inputPrice();
                    //ham update price moi
                    update_product.update_price(proID,updateNewPr.getPrice());
                    break;
                case 5:
                    updateNewPr.inputText();
                    //ham update descript moi
                    update_product.update_des_pro(proID,updateNewPr.getText());
                    break;
                case 6:
                    updateNewPr.inputCategoryId();
                    //ham update newProCate moi
                    update_product.update_cate_id_pro(proID,updateNewPr.getCategory_id());
                    break;
                case 7:
                    updateNewPr.inputSalePrice();
                    update_product.update_sale_price(proID,updateNewPr.getSale_price());
                    break;
                case 8:
                    return;
                default:
                    System.err.println("Who?");
                    break;
            }
        }

    }

    public static void product_Sort(Locale local) {
        Scanner sc = new Scanner(System.in);
        ResourceBundle bundle = null;
        while (true) {
            bundle = ResourceBundle.getBundle("Lang.language", local);
            System.out.println(bundle.getString("product_Sort_Frame"));
            System.out.println(bundle.getString("product_Sort_1"));
            System.out.println(bundle.getString("product_Sort_2"));
            System.out.println(bundle.getString("product_Sort_3"));
            System.out.print(bundle.getString("choose"));
            int product_Sort_Choice = Integer.parseInt(sc.nextLine());
            Manager.Products.Display display = new Manager.Products.Display();
            switch (product_Sort_Choice) {
                case 1:
                    //ham sap xep product gia tang dan
                    display.soft_pro_by_price_asc();
                    break;
                case 2:
                    //ham sap xep product gia giam dan
                    display.soft_pro_by_price_desc();
                    break;
                case 3:
                    product_Menu(local);
                    break;
                default:
                    System.err.println("Who?");
                    break;
            }
        }
    }

    public static void product_Add(Locale local) {
        Scanner scanner = new Scanner(System.in);

        boolean check = true;
        String total;
        do {
            check = true;
            System.out.println("Mời bạn nhập số lượng sản phẩm cần thêm");
            total = scanner.nextLine();
            String regex = "[+]?[0-9]+";

            if (total.isEmpty()) {
                System.out.println("Vui lòng điền số lượng sản phẩm!!");
                check = false;
            }else {
                if (!total.matches(regex)) {
                    System.err.println("Số lượng phải là số nguyên !!");
                    check = false;
                }
                if (Integer.parseInt(total)==0){
                    System.err.println("Số lượng ít nhất phải là 1");
                    check = false;
                }
            }
        } while (!check);
        for (int i = 0; i < Integer.parseInt(total); i++) {
            System.out.println("Sản phẩm thứ " + (i+1));
            Product newProduct = new Product();
            newProduct.inputData();
            Add_product add_product = new Add_product();
            add_product.add_Pro(newProduct);
        }

    }

    public static void product_Info(Locale local) {
        Scanner sc = new Scanner(System.in);
        ResourceBundle bundle = null;

        while (true) {
            bundle = ResourceBundle.getBundle("Lang.language", local);
            System.out.println(bundle.getString("product_Info_Frame"));
            System.out.println(bundle.getString("product_Info_1"));
            System.out.println(bundle.getString("product_Info_2"));
            System.out.println(bundle.getString("product_Info_3"));
            System.out.print(bundle.getString("choose"));
            int category_Display_Choice = Integer.parseInt(sc.nextLine());

            switch (category_Display_Choice) {
                case 1:
                    Scanner scanner = new Scanner(System.in);
                    //ham hien thi sp theo danh muc
                    Manager.Products.Display display = new Manager.Products.Display();
                    display.Display_all();
                    System.out.println("Bạn có muốn xuất dữ liệu ra file Product.txt (Y/N) ??");
                    boolean check = true;
                    do {
                        check = true;
                        System.out.println("Vui lòng điền lựa chọn của bạn: ");
                        String check_req = scanner.nextLine();
                        if (check_req.isEmpty()){

                            check = false;
                        }else {
                            if (!check_req.equalsIgnoreCase("Y") && !check_req.equalsIgnoreCase("N") && !check_req.equalsIgnoreCase("Yes") && !check_req.equalsIgnoreCase("No")){
                                System.err.println("Chỉ được chọn Yes hoặc No");
                                check = false;
                            }else {
                                if (check_req.equalsIgnoreCase("Y") || check_req.equalsIgnoreCase("Yes")){
                                    try {
                                        File newFile = new File("Product.txt");
                                        FileWriter fileWriter = new FileWriter(newFile);

                                        Product product = new Product();
                                        List<Product> productList = product.getData();
                                        fileWriter.write("Thông tin sản phẩm: \n");
                                        productList.forEach(p ->{
                                            try {
                                                fileWriter.write(p.toString());
                                                fileWriter.write("\n");
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        });
                                        fileWriter.write("File được ghi tự động bởi chương trình của Tiến Bùi!!!");
                                        fileWriter.close();
                                        System.out.println("Đã ghi thành công ra file!!!");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }else {
                                    break;
                                }
                            }

                        }
                    }while (!check);
                    break;
                case 2:
                    System.out.println(bundle.getString("product_Info_Find_By_Name"));
                    String nameWanted = sc.nextLine();
                    //ham findbyName product
                    Find_product find_product = new Find_product();
                    find_product.find_by_name(nameWanted);
                    break;
                case 3:
                    product_Menu(local);
                    break;
                default:
                    System.err.println("Who?");
                    break;
            }
        }
    }

}
