package serv.service;
import serv.dbase.DataBase;
import org.springframework.stereotype.Service;
import javax.xml.bind.annotation.XmlRootElement;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@XmlRootElement(name = "Serv")
@Service
public class PBMain {
    private DataBase base;
    private final BlockingQueue<String> actions = new LinkedBlockingQueue<>();
    private byte[] picture;

    public PBMain() {
        try {
            base = DataBase.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Thread toBase = new Thread(() -> {
            while (true) {
                try {
                    String[] words = actions.take().split(" ");
                    base.insertPixel(words[0].getBytes(StandardCharsets.UTF_8)[0], Integer.parseInt(words[1]));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });
        Thread update = new Thread(() -> {
            while (true) {
                try {
                    picture = base.getPixelMap();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    return;
                }
            }

        });
        toBase.start();
        update.start();
    }

    public boolean insert(String order) {
        try{
            actions.offer(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public byte[] updatePicture(){
        byte[] x;
        try {
            x = base.getPixelMap();
        } catch (SQLException e) {
            e.printStackTrace();
            x = picture;
        }
        return x;
    }

    @Override
    protected void finalize(){
        base.close();
    }

}
