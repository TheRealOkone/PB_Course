package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class Gui extends JFrame {
    public TestPaint area;
    private  JPanel panel_cursor_zoom;
    private BufferedImage imageCursorZoomFragment = null;
    private final static short zoomScaleRate = 5;
    public static BufferedImage image ;
    JLabel lb;
    private JPanel rightPanel;
    private JButton sub;
    private JPanel btnPanel;
    private JToggleButton btnRed;
    private JToggleButton btnGreen;
    private JToggleButton btnBlack;
    private JToggleButton btnYellow;
    private JToggleButton btnBlue;
    private JToggleButton btnWhite;
    private JToggleButton btnPurple;
    private int lastX;
    private int lastY;


    private BufferedImage getImage() {
        return image;
    }

    public Gui() {

        super();

        area = new TestPaint();
        setMinimumSize(new Dimension(500, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        lb = new JLabel("");
        lb.setPreferredSize(new Dimension(50, 100));
        sub = new JButton("submit");
        rightPanel.add(lb);
        this.add(area);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(sub, BorderLayout.PAGE_END);

        btnPanel = new JPanel();
        btnPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 3;
        c.gridheight = 2;


        ButtonGroup bg = new ButtonGroup();

        btnRed = new JToggleButton();
        btnGreen = new JToggleButton();
        btnBlack = new JToggleButton();
        btnYellow = new JToggleButton();
        btnBlue = new JToggleButton();
        btnWhite = new JToggleButton();

        JToggleButton[] btns = {btnRed, btnGreen, btnBlack, btnYellow, btnBlue, btnWhite};

        for (int i = 0; i < btns.length; i++) {
            btns[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            btns[i].setPreferredSize(new Dimension(20, 20));
            if(i > 2)
                c.gridy = 2;
            btnPanel.add(btns[i], c);
            bg.add(btns[i]);

        }



        btnRed.setBackground(new Color(254, 37, 0));

        btnGreen.setBackground(new Color(92, 191, 13));
        btnBlack.setBackground(new Color(0, 0, 0));
        btnYellow.setBackground(new Color(255, 207, 74));
        btnBlue.setBackground(new Color(7, 75, 243));
        btnWhite.setBackground(new Color(255, 255, 255));





        rightPanel.add(btnPanel);



        panel_cursor_zoom = new JPanel() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                try {
                    g.drawImage(imageCursorZoomFragment, 0, 0, imageCursorZoomFragment.getWidth() * zoomScaleRate,
                            imageCursorZoomFragment.getHeight() * zoomScaleRate, null);
                    int x = imageCursorZoomFragment.getWidth() * zoomScaleRate / 2;
                    int y = imageCursorZoomFragment.getHeight() * zoomScaleRate / 2;
                    g.drawLine(x, 0, x, imageCursorZoomFragment.getHeight() * zoomScaleRate);
                    g.drawLine(0, y, imageCursorZoomFragment.getWidth() * zoomScaleRate, y);
                } catch (NullPointerException e) {

                }
            }
        };

        panel_cursor_zoom.setPreferredSize(new Dimension(105, 105));

        rightPanel.add(panel_cursor_zoom, BorderLayout.CENTER);


        MouseMotionAdapter ms = new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                area.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                int x = e.getX() - area.getWidth()/2 + 200;
                int y = e.getY() - area.getHeight()/2 + 200;
                if(x >= 0 && x <= 399){
                    lastX = x;
                }
                if (y >= 0 && y <= 399) {
                    lastY = y;
                }


                lb.setText("<html>" + "x = " + lastX + "<br>" + "y = " + lastY + "</html>");

                if(e.getX() > 20 && e.getX() < area.getWidth() - 20 && e.getY() > 20 && e.getY() < area.getHeight() - 20) {

                    imageCursorZoomFragment = getImage().getSubimage(e.getX() - 10, e.getY() - 10, 21, 21);
                    panel_cursor_zoom.repaint();
                }

            }


        };

        area.addMouseMotionListener(ms);
        area.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton()==MouseEvent.BUTTON1) {
                    int x = e.getX() - area.getWidth()/2 + 200;
                    int y = e.getY() - area.getHeight()/2 + 200;
                    if(x >= 0 && x <= 399){
                        lastX = x;
                    }
                    if (y >= 0 && y <= 399) {
                        lastY = y;
                    }
                    area.removeMouseMotionListener(ms);
                    lb.setText("<html>" + "x = " + lastX + "<br>" + "y = " + lastY + "</html>");
                }
                else if(e.getButton() == MouseEvent.BUTTON3) {
                    area.addMouseMotionListener(ms);
                }
            }

        });

        sub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(bg.getSelection() != null) {
                    sub.setText("submit");
                    if(btnRed.isSelected())
                        client.client.insert("1 " + String.valueOf((lastY) * 400 + lastX));
                    if(btnGreen.isSelected())
                        client.client.insert("2 " + String.valueOf((lastY) * 400 + lastX));
                    if(btnBlack.isSelected())
                        client.client.insert("3 " + String.valueOf((lastY) * 400 + lastX));
                    if(btnYellow.isSelected())
                        client.client.insert("4 " + String.valueOf((lastY) * 400 + lastX));
                    if(btnBlue.isSelected())
                        client.client.insert("5 " + String.valueOf((lastY) * 400 + lastX));
                    if (btnWhite.isSelected())
                        client.client.insert("6 " + String.valueOf((lastY) * 400 + lastX));
                    area.addMouseMotionListener(ms);
                    area.repaint();
                    panel_cursor_zoom.repaint();
                }
                else {
                    sub.setText("Please choose a color");
                }
            }
        });
    }


}