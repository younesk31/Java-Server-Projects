package PizzaStuff;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class PizzaServer {
    boolean runAgain = true;
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private ArrayList<Pizzas> menu = new ArrayList<>();


//todo: 1
    // Skriv en server som indeholder en masse opskrifter på Pizzaer.
    // Disse opskrifter skal kunne forespørges og fremsendes til klienten.
    // Klienten skal kunne indsætte nye opskrifter. Alle nye opskrifter vil blive gemt i hukommelsen.

    public PizzaServer(int port) {
        while (true) {
            try {
                Pizzaer.addPizzas(menu);
                server = new ServerSocket(port);
                System.out.println("Waiting for a client ...");
                socket = server.accept();
                System.out.println("Client connected ...");
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                if (in.readUTF().equals("1") || runAgain) {
                    StringBuilder st = new StringBuilder();
                    for (Pizzas menuList : menu) {
                        st.append(menuList.getPizzaNr()).append(".  ").append("Pizza: ").append(menuList.getPizzaName()).append(": ").append(menuList.getPizzaFilling()).append(".....").append(menuList.getPizzaPrice()).append(",-\n");
                    }
                    System.out.println("Printing menu for Client ");
                    out.writeUTF(String.valueOf(st));
                } else if (in.readUTF().equals("2") || runAgain) {
                    String pizzaName;
                    String pizzaIng;
                    int pizzaPrice;
                    out.writeUTF("To make a pizza you need to input some stuff\n Input Pizza name:");
                    pizzaName = in.readUTF();
                    out.writeUTF("Input the ingrediens for the Pizza");
                    pizzaIng = in.readUTF();
                    out.writeUTF("Input the Pizza Price");
                    pizzaPrice = Integer.parseInt(in.readUTF());
                    out.writeUTF("Server added: " + pizzaName + " with " + pizzaIng + " for " + pizzaPrice + " kr.\nDoes that look correct? y/n");
                    System.out.println("Server added: " + pizzaName + " with " + pizzaIng + " for " + pizzaPrice + " kr.");
                    if (in.readUTF().equals("y")) {
                        menu.add(new Pizzas(menu.size() + 1, pizzaName, pizzaIng, pizzaPrice));
                        System.out.println("added pizza to menu");
                    } else {
                        System.out.println("error adding pizza to menu");
                    }
                }

                String line = "";
                while (!line.equals("Bye")) {
                    try {
                        line = in.readUTF();
                        if (line.equals("1")) {
                            System.out.println("client pressed 1");
                        } else {
                            System.out.println("client pressed somthing else");
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Closing connection");
                out.close();
                in.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        PizzaServer server = new PizzaServer(6000);
    }
}