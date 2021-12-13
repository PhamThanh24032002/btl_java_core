package Views;

import Connection.Connections;
import Controller.ControllerAll;
import Entity.Category;
import Entity.Product;
import Manager.Categories.Add_cate_to_db;
import Manager.Categories.Delete_cate;
import Manager.Categories.Display;
import Manager.Categories.Find_cate;
import Manager.Products.Add_product;
import Manager.Products.Find_product;
import Manager.Products.Update_product;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Program {
	public Program() {
	}

	public static void main(String[] args) {
		Connection connections = Connections.getConnect();
		Locale local = new Locale("vi", "VN");
		main_Menu(local);
	}
	
	public static void main_Menu(Locale local) {
		Scanner sc = new Scanner(System.in);
		ResourceBundle bundle = null;
		
		while (true) {
			bundle = ResourceBundle.getBundle("Lang.language", local);
			System.out.println(bundle.getString("main_Menu_Frame"));
			System.out.println(bundle.getString("main_Menu_1"));
			System.out.println(bundle.getString("main_Menu_2"));
			System.out.println(bundle.getString("main_Menu_3"));
			System.out.println(bundle.getString("main_Menu_4"));
			System.out.print(bundle.getString("choose"));
			int choice = Integer.parseInt(sc.nextLine());
			ControllerAll controllerAll = new ControllerAll();
			switch (choice) {
				case 1:

					controllerAll.category_Menu(local);
					break;
				case 2:
					controllerAll.product_Menu(local);
					break;
				case 3:
					System.out.println(bundle.getString("main_Language_Frame"));
					System.out.println(bundle.getString("main_Language_1"));
					System.out.println(bundle.getString("main_Language_2"));
					System.out.print(bundle.getString("choose"));
					int lang = Integer.parseInt(sc.nextLine());

					switch (lang) {
					case 1:
						local =  new Locale("vi", "VN");
						break;
					case 2:
						local =  new Locale("en", "US");
						break;
					default:
						break;
					}
					System.out.println(bundle.getString("switch_lang"));
					break;
				case 4:
					System.exit(0);
					break;
				default:
					System.out.println("Lựa chọn không hợp lệ");
					break;
				}
		}
	}



}
