import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Thread thread1 = new Thread(){
            public void run(){
                Server.startServer();
            }
        };

        Thread thread2 = new Thread(){
          public void run(){
              Client.startClient();
          }
        };

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
