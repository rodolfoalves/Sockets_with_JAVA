import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends javax.swing.JFrame {
///media/rodolfo/41e1c934-05a6-4c76-8623-46344d412648/Redes 2/trabalhoSockets/file/foto.png
//if (teclado.nextLine().equals("imagem"))
    public static void main(String args[]) throws UnknownHostException, IOException, InterruptedException {
        Socket cliente = new Socket("localhost", 12345);
        Scanner teclado = new Scanner(System.in);
        System.out.println("The client has connectd!");

        PrintStream saida = new PrintStream(cliente.getOutputStream());


        saida.println("start the image copy server");
        OutputStream outputStream = cliente.getOutputStream();

        BufferedImage image = ImageIO.read(new File("file/in/teste.png"));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);

        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        outputStream.write(size);
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.flush();
        System.out.println("Flushed: " + System.currentTimeMillis());

        Thread.sleep(120000);
        System.out.println("Closing: " + System.currentTimeMillis());




        /*
        if (teclado.nextLine().equals("text")){
            saida.println("start the chat server");
            while (teclado.hasNextLine()) {
                saida.println(teclado.nextLine());
            }
        }
        else {
            saida.println("start the image copy server");
            OutputStream outputStream = cliente.getOutputStream();

            BufferedImage image = ImageIO.read(new File("file/in/foto.jpg"));

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", byteArrayOutputStream);

            byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
            outputStream.write(size);
            outputStream.write(byteArrayOutputStream.toByteArray());
            outputStream.flush();
            System.out.println("Flushed: " + System.currentTimeMillis());

            Thread.sleep(120000);
            System.out.println("Closing: " + System.currentTimeMillis());
        }
        */



        saida.close();
        teclado.close();
        cliente.close();
    }
}
