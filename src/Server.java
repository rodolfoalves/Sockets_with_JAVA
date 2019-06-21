import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("12345 open");

        Socket cliente = serverSocket.accept();
        System.out.println("New connection: client -> " + cliente.getInetAddress().getHostAddress());
        Scanner s = new Scanner(cliente.getInputStream());





        System.out.println("start the image copy server");

        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        System.out.println("Reading: " + System.currentTimeMillis());

        byte[] sizeAr = new byte[4];
        inputStream.read(sizeAr);
        int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

        byte[] imageAr = new byte[size];
        inputStream.read(imageAr);

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

        System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
        ImageIO.write(image, "jpg", new File("file/out/out.png"));


/*
        if (s.nextLine().equals("start the chat server")){
            System.out.println("start the chat server");
            while (s.hasNextLine()) {
                System.out.println(s.nextLine());
            }
        }
        else {
            System.out.println("start the image copy server");

            Socket socket = server.accept();
            InputStream inputStream = socket.getInputStream();

            System.out.println("Reading: " + System.currentTimeMillis());

            byte[] sizeAr = new byte[4];
            inputStream.read(sizeAr);
            int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

            byte[] imageAr = new byte[size];
            inputStream.read(imageAr);

            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

            System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
            ImageIO.write(image, "jpg", new File("file/out/foto.jpg"));

        }
*/
        s.close();
        serverSocket.close();
        cliente.close();
    }

}