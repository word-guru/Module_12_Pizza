import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String pizzaName = null;

        System.out.println("Choose a pizza");
        System.out.println("Margarita: enter 1");
        System.out.println("Four cheeses: enter 2");
        System.out.println("Capriccioza: enter 3");
        System.out.println("Hawaiian: enter 4");
        System.out.print("Please make a choice: ");
        int choice = new Scanner(System.in).nextInt();

        if(choice==1){
            pizzaName = "Margarita";
        }
        else if(choice==2){
            pizzaName = "Four cheeses";
        }
        else if(choice==3){
            pizzaName = "Capriccioza";
        }
        else if(choice==4){
            pizzaName = "Hawaiian";
        }

        Pizza pizza = new Pizza(pizzaName);

        InetAddress ia = InetAddress.getByName("10.3.60.112");
        int port = 19999;
        byte[] dataSend = pizza.name.getBytes();
        DatagramPacket packetSend = new DatagramPacket(dataSend, dataSend.length, ia, port);
        DatagramSocket socketSend = new DatagramSocket();
        socketSend.send(packetSend);

        DatagramSocket socketGet = new DatagramSocket(port);
        byte[] buffer = new byte[10240];
        DatagramPacket packetGet = new DatagramPacket(buffer, buffer.length);
        socketGet.receive(packetGet);
        byte[] dataGet = packetGet.getData();
        String strGet = new String(dataGet, 0, dataGet.length);
        System.out.println(strGet);
    }
}