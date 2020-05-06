package start;

import java.sql.*;
import java.sql.SQLException;

import businessLayer.BusinessClass;
import dataAccessLayer.ClientRepository;
import dataAccessLayer.OrderRepository;
import dataAccessLayer.ProductRepository;
import databaseConnection.DBConnection;
import model.Client;
import model.Order;
import model.Product;

public class Main
{
	public static void main(String args[])
	{
		DBConnection conn = new DBConnection();
		//Connection c = conn.createConnection();

		Client client = new Client();
		client.setLocation("Bucuresti");
		client.setName("Ion Popescu");

		ClientRepository cr = new ClientRepository();
		//cr.addClient(client);
		//cr.deleteClient("Mihai Danila");

		Product product = new Product();
		product.setAmount(15);
		product.setName("apples");
		product.setPrice(1);

		ProductRepository pr = new ProductRepository();
		//pr.addProduct(product);
		//pr.deleteProduct("apples");
		//pr.orderUpdateProduct(15,product);

		//Order order = new Order();
		//order.setProductName("apples");
		//order.setClientName("Ion Popescu");
		//order.setProductAmount(5);

		OrderRepository or = new OrderRepository();
		//or.createOrder(order);
		/*String prest= "SELECT * FROM Client";
		try {
			PreparedStatement statement = c.prepareStatement(prest);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString("Name"));
				System.out.println(rs.getString("Location"));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}*/

		BusinessClass bs = new BusinessClass();
		//bs.newOrder(or,pr,client,product,10);
		/*String presto= "SELECT * FROM `Order`";
		try {
			PreparedStatement statement = c.prepareStatement(presto);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString("OrderID"));
				System.out.println(rs.getString("ClientName"));
				System.out.println(rs.getString("ProductName"));
				System.out.println(rs.getString("ProductAmount"));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		String prest= "SELECT * FROM Product";
		try {
			PreparedStatement statement = c.prepareStatement(prest);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString("Name"));
				System.out.println(rs.getString("Amount"));
				System.out.println(rs.getString("Price"));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}*/

		//bs.newOrder(or,pr,client,product,15);
		//bs.newOrder(or,pr,client,product,5);
		//bs.newOrder(or,pr,client,product,5);
		//bs.reportOrders();
	}
}
