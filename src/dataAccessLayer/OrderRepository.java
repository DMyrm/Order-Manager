package dataAccessLayer;

import databaseConnection.DBConnection;
import model.Client;
import model.Order;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderRepository
{
    DBConnection conn = new DBConnection();

    public void createOrder(Order order)
    {
        try(Connection c = conn.createConnection())
        {
            String query = "INSERT INTO `Order` (ClientName,ProductName,ProductAmount,OrderPrice)"
                    + "Values (?,?,?,?)";

            PreparedStatement statement = c.prepareStatement(query);
            //statement.setInt(1,order.getOrderID());
            statement.setString(1,order.getClientName());
            statement.setString(2,order.getProductName());
            statement.setInt(3,order.getProductAmount());
            statement.setFloat(4,order.getOrderPrice());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Order> findOrders()
    {
        String prest= "SELECT * FROM `Order`";
        ArrayList<Order> orders = new ArrayList<>();
        try(Connection c = conn.createConnection()) {
            PreparedStatement statement = c.prepareStatement(prest);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                Order order = new Order();
                order.setOrderID(rs.getInt("OrderID"));
                order.setClientName(rs.getString("ClientName"));
                order.setProductName(rs.getString("ProductName"));
                order.setProductAmount(rs.getInt("ProductAmount"));
                order.setOrderPrice(rs.getFloat("OrderPrice"));
                orders.add(order);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }

    public int findOne(Order order)
    {
        int orderID = 0;
        String prest= "select max(OrderID) from `Order` where ClientName = ? and ProductName = ? " +
                "and ProductAmount = ?  and OrderPrice = ?";
        try(Connection c = conn.createConnection()) {
            PreparedStatement statement = c.prepareStatement(prest);
            statement.setString(1,order.getClientName());
            statement.setString(2,order.getProductName());
            statement.setInt(3,order.getProductAmount());
            statement.setFloat(4,order.getOrderPrice());

            ResultSet rs = statement.executeQuery();
            if(rs.next())
            {
                orderID = rs.getInt("max(OrderID)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderID;
    }
}
