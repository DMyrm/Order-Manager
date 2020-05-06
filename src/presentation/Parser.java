package presentation;

import businessLayer.BusinessClass;
import model.Client;
import model.Order;
import model.Product;

import javax.swing.border.Border;
import java.util.ArrayList;

public class Parser
{
    BusinessClass businessClass = new BusinessClass();

    public boolean findCommand(String command,int reportNumber)
    {
        String[] phrase = command.split(" ");
        if(phrase[0].equals("Insert") || phrase[0].equals("insert"))
        {
            if(phrase[1].equals("Client:") || phrase[1].equals("client:"))
            {
                Client client = new Client();
                client.setName(phrase[2]+" "+phrase[3].substring(0, phrase[3].length() - 1));
                client.setLocation(phrase[4]);
                businessClass.clientRepository.addClient(client);
            }
            if(phrase[1].equals("Product:") || phrase[1].equals("product:"))
            {
                Product product = new Product();
                product.setName(phrase[2].substring(0, phrase[2].length() - 1));
                product.setAmount(Integer.parseInt(phrase[3].substring(0, phrase[3].length() - 1)));
                product.setPrice(Float.parseFloat(phrase[4]));
                if(!businessClass.productRepository.addProductsAmount(product.getAmount(),product))
                    businessClass.productRepository.addProduct(product);
            }
        }

        if(phrase[0].equals("Order:") || phrase[0].equals("order:"))
        {
            Order order = new Order();
            order.setClientName(phrase[1]+" "+phrase[2].substring(0, phrase[2].length() - 1));
            order.setProductName(phrase[3].substring(0, phrase[3].length() - 1));
            int requiredAmount = Integer.parseInt(phrase[4]);

            Product product = businessClass.productRepository.findOne(order.getProductName());
            Client client = businessClass.clientRepository.findOne(order.getClientName());
            businessClass.newOrder(businessClass.orderRepository,businessClass.productRepository,order,client,product,requiredAmount);
        }

        if(phrase[0].equals("Delete") || phrase[0].equals("delete"))
        {
            if(phrase[1].equals("Client:") || phrase[1].equals("client:"))
            {
                Client client = new Client();
                client.setName(phrase[2]+" "+phrase[3].substring(0, phrase[3].length() - 1));
                client.setLocation(phrase[4]);
                businessClass.clientRepository.deleteClient(client);
            }
            if(phrase[1].equals("Product:") || phrase[1].equals("product:"))
            {
                businessClass.productRepository.deleteProduct(phrase[2]);
            }
        }

        if(phrase[0].equals("Report") || phrase[0].equals("report"))
        {
            if(phrase[1].equals("Client") || phrase[1].equals("client"))
            {
                businessClass.reportClient(reportNumber);
                return true;
            }
            if(phrase[1].equals("Product") || phrase[1].equals("product"))
            {
                businessClass.reportProducts(reportNumber);
                return true;
            }
            if(phrase[1].equals("Order") || phrase[1].equals("order"))
            {
                businessClass.reportOrders(reportNumber);
                return true;
            }
        }
        return false;
    }
}
