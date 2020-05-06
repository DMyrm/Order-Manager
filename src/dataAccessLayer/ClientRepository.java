package dataAccessLayer;

import databaseConnection.DBConnection;
import model.Client;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientRepository
{
    DBConnection conn = new DBConnection();

    public void addClient(Client client)
    {
        try(Connection c = conn.createConnection())
        {
            String query = "INSERT INTO Client (Name,Location)" + "Values (?,?)";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1,client.getName());
            statement.setString(2,client.getLocation());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteClient(Client client)
    {
        try(Connection c = conn.createConnection())
        {
            String query = "delete from Client where Name = ? and Location = ?";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, client.getName());
            statement.setString(2, client.getLocation());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Client> findClients()
    {
        String prest= "SELECT * FROM Client";
        ArrayList<Client> clients = new ArrayList<>();
        try(Connection c = conn.createConnection()) {
            PreparedStatement statement = c.prepareStatement(prest);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                Client client = new Client();
                client.setName(rs.getString("Name"));
                client.setLocation(rs.getString("Location"));
                clients.add(client);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clients;
    }
    public Client findOne(String name)
    {
        String prest= "select * from Client where Name = ?";
        Client client = new Client();
        try(Connection c = conn.createConnection()) {
            PreparedStatement statement = c.prepareStatement(prest);
            statement.setString(1,name);

            ResultSet rs = statement.executeQuery();
            if(rs.next())
            {
                client.setName(rs.getString("Name"));
                client.setLocation(rs.getString("Location"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return client;
    }

}
