package client;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TestPaint extends JPanel {

    public TestPaint() {
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Gui.image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = Gui.image.createGraphics();
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(0, 0, getWidth(), getHeight());
        byte[] pix = client.client.update();
        for (int i = 0; i < pix.length; i++) {

            int x = (i % 400) + getWidth() / 2 - 200;
            int y = (int) (i / 400) + getHeight() / 2 - 200;
            char t = (char) (pix[i]);
            switch (t) {
                case '1' -> {
                    g2.setColor(new Color(254, 37, 0));
                    g2.fillRect(x, y, 1, 1);
                }
                case '2' -> {
                    g2.setColor(new Color(92, 191, 13));
                    g2.fillRect(x, y, 1, 1);
                }
                case '3' -> {
                    g2.setColor(new Color(0, 0, 0));
                    g2.fillRect(x, y, 1, 1);
                }
                case '4' -> {
                    g2.setColor(new Color(255, 207, 74));
                    g2.fillRect(x, y, 1, 1);
                }
                case '5' -> {
                    g2.setColor(new Color(7, 75, 243));
                    g2.fillRect(x, y, 1, 1);
                }
                case '6' -> {
                    g2.setColor(new Color(255, 255, 255));
                    g2.fillRect(x, y, 1, 1);
                }
                default -> {
                    g2.setColor(Color.white);
                    g2.fillRect(x, y, 1, 1);
                }
            }
        }

        g.drawImage(Gui.image, 0, 0, this);

    }
}
