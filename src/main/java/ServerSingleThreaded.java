import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerSingleThreaded {
    public static void main(String[] args) throws IOException {
        //Connect on port 8080, and listen
        ServerSocket server = new ServerSocket(8080);
        while (true) {
            Socket client = server.accept();
            System.out.println("client connected: " + client.getInetAddress().getHostName());

            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

            String order = reader.readLine();
            System.out.println("order: " + order);

            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/html; charset=utf-8");
            writer.println("");
            writer.println("<h1>Hello!</h1>");
            writer.println("<p>Server hour: " + new Date() + "</p>");

            writer.flush();
            client.close();
        }
    }
}
