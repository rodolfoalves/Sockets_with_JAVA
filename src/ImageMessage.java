import java.io.File;

public class ImageMessage {

    public static boolean testImage(String path){
        File file = new File(path, "foto.png");
        return file.exists();
    }

}
