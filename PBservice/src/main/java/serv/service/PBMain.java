package serv.service;

import serv.dbase.DataBase;

import org.springframework.stereotype.Service;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

@XmlRootElement(name = "Serv")
@Service
public class PBMain {
    private DataBase base;
    private LinkedList<String> actions;
    private byte[] picture;

    public PBMain() {
        base = new DataBase();
        actions = new LinkedList<String>();

        Thread toBase = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (actions.size() > 0) {
                        String[] words = actions.pollFirst().split(" ");
                        try {
                            base.insertPixel((new String(words[0]).getBytes("UTF-8"))[0], Integer.parseInt(words[1]));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        Thread update = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    picture = base.getPixelMap();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        System.out.println("bruh");
        toBase.start();
        update.start();
    }

    public boolean insert(String order) {
        try{
            actions.addLast(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public byte[] updatePicture() {
        return picture;
    }

}
