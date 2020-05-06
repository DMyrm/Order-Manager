package businessLayer;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import dataAccessLayer.ClientRepository;
import dataAccessLayer.OrderRepository;
import dataAccessLayer.ProductRepository;
import model.Client;
import model.Order;
import model.Product;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.stream.Stream;

public class BusinessClass
{
    public ClientRepository clientRepository = new ClientRepository();
    public ProductRepository productRepository = new ProductRepository();
    public OrderRepository orderRepository = new OrderRepository();


    public boolean newOrder(OrderRepository orderRepository, ProductRepository productRepository,Order order,Client client,
                            Product product,int requiredAmount)
    {
        //Generate Bill
        if(requiredAmount < product.getAmount()) {
            //Order order = new Order();
            //order.setClientName(client.getName());
            //order.setProductName(product.getName());
            order.setProductAmount(requiredAmount);
            order.setOrderPrice(requiredAmount*product.getPrice());

            orderRepository.createOrder(order);
            productRepository.updateProduct(product.getAmount()-requiredAmount,product);

            order.setOrderID(orderRepository.findOne(order));

            printBill(order,client,product,requiredAmount);
            return true;
        }
        else{
            printOutOfStockMessage(product);
            return false;
        }

    }

    public void reportClient(int c)
    {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("ClientReport"+c+".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();

        PdfPTable table = new PdfPTable(2);
        //addTable
        Stream.of("Name", "Location")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);});
        for (Client client: clientRepository.findClients())
        {
            table.addCell(client.getName());
            table.addCell(client.getLocation());
        }

        try {
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
    }

    public void reportProducts(int p)
    {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("ProductReport"+p+".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();

        PdfPTable table = new PdfPTable(3);
        //addTable
        Stream.of("Name", "Amount","Price")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);});
        for (Product product: productRepository.findProducts())
        {
            table.addCell(product.getName());
            table.addCell(String.valueOf(product.getAmount()));
            table.addCell(String.valueOf(product.getPrice()));
        }

        try {
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
    }

    public void reportOrders(int o)
    {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("OrderReport"+o+".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();

        PdfPTable table = new PdfPTable(5);
        //addTable
        Stream.of("OrderID", "ClientName","ProductName","ProductAmount","OrderPrice")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);});
        for (Order order: orderRepository.findOrders())
        {
            table.addCell(String.valueOf(order.getOrderID()));
            table.addCell(order.getClientName());
            table.addCell(order.getProductName());
            table.addCell(String.valueOf(order.getProductAmount()));
            table.addCell(String.valueOf(order.getOrderPrice()));
        }

        try {
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
    }

    public void printBill(Order order,Client client,Product product,int requiredAmount)
    {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Bill"+order.getOrderID()+".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk(client.getName() + " ordered " + requiredAmount +' '+ product.getName()
                + " for a total of " + requiredAmount*product.getPrice() +".", font);
        try {
            document.add(chunk);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
    }

    public void printOutOfStockMessage(Product product)
    {
        Document document = new Document();
        try {
        PdfWriter.getInstance(document, new FileOutputStream("OutOfStockOrder"+product.getName()+".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("The " + product.getName() + " product " + "is out of stock for the moment.", font);
        try {
            document.add(chunk);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
    }
}
