import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public EchoServer(int port) {
        while (true) {
            try {
                server = new ServerSocket(port);
                System.out.println("Waiting for a client ...");
                socket = server.accept();
                System.out.println("Client accepted");
                in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                out = new DataOutputStream(socket.getOutputStream());
                String line = "";

                while (!line.equals("Over")) {
                    try {
                        line = in.readUTF();
                        if (line.equals("are you here")) {
                            out.writeUTF("Server: im listening");
                            System.out.println("Client: asking if im listening");
                        } else {
                            System.out.println("Client: "+line);
                            out.writeUTF("Server: "+line);
                        }
                    } catch (IOException i) {
                        System.out.println(i);
                    }
                }
                System.out.println("Closing connection");
                out.close();
                in.close();
                socket.close();
            } catch (IOException i) {
                System.out.println(i);
            }
        }
    }

    public static void main(String args[]) {
        EchoServer server = new EchoServer(5000);
    }
}
