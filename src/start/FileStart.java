package start;

import presentation.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileStart
{
    public static void main(String[] args) {
        int reportNumber = 1;
        Parser parser = new Parser();
        try {
            File myObj = new File(args[0]);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(parser.findCommand(data,reportNumber)) reportNumber++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}