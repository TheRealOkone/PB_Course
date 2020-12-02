package client;

import serv.service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import javax.print.attribute.standard.NumberUp;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicCheckBoxUI;
import javax.swing.plaf.basic.BasicRadioButtonUI;
import javax.swing.plaf.metal.MetalCheckBoxUI;
import javax.swing.plaf.metal.MetalRadioButtonUI;
import javax.swing.plaf.synth.SynthRadioButtonUI;
import javax.xml.ws.WebServiceFeature;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class client {
    public static PBSOAPService client;

    public static void main(String[] args) {

        Gui a = new Gui();
        PBSOAPServiceService ser = new PBSOAPServiceService();

        client = ser.getPBSOAPServicePort();


        while(true) {
            a.area.repaint();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}













