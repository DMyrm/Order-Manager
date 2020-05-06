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

public class ProductRepository
{
    DBConnection conn = new DBConnection();

    public void addProduct(Product product)
    {
        try(Connection c = conn.createConnection())
        {
            String query = "INSERT INTO Product (Name,Amount,Price)" + "Values (?,?,?)";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1,product.getName());
            statement.setInt(2,product.getAmount());
            statement.setFloat(3,product.getPrice());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteProduct(String Name)
    {
        try(Connection c = conn.createConnection())
        {
            String query = "delete from Product where Name = ?";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, Name);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateProduct (int amount,Product product)
    {
        try(Connection c = conn.createConnection())
        {
            String query = "update Product set Amount = ? where Name = ?";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setInt(1, amount);
            statement.setString(2, product.getName());
            statement.execute();

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public ArrayList<Product> findProducts()
    {
        String prest= "SELECT * FROM Product";
        ArrayList<Product> products = new ArrayList<>();
        try(Connection c = conn.createConnection()) {
            PreparedStatement statement = c.prepareStatement(prest);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                Product product = new Product();
                product.setName(rs.getString("Name"));
                product.setAmount(rs.getInt("Amount"));
                product.setPrice(rs.getFloat("Price"));
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }
    public boolean addProductsAmount(int amountAdded,Product product)
    {
        String prest= "select * from Product where Name = ?";
        try(Connection c = conn.createConnection()) {
            PreparedStatement statement = c.prepareStatement(prest);
            statement.setString(1,product.getName());

            ResultSet rs = statement.executeQuery();
            if(rs.next())
            {
                updateProduct(product.getAmount()+amountAdded,product);
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Product findOne(String name)
    {
        String prest= "select * from Product where Name = ?";
        Product product = new Product();
        try(Connection c = conn.createConnection()) {
            PreparedStatement statement = c.prepareStatement(prest);
            statement.setString(1,name);

            ResultSet rs = statement.executeQuery();
            if(rs.next())
            {
                product.setName(rs.getString("Name"));
                product.setAmount(rs.getInt("Amount"));
                product.setPrice(rs.getFloat("Price"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }
}
