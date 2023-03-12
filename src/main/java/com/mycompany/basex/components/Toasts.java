package com.mycompany.basex.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.Timer;

public class Toasts extends JComponent {

    private static final long serialVersionUID = 1L;
    
    private static final int TOAST_DURATION = 2000;

    private final String message;

    public Toasts(String message, int x, int y) {
        this.message = message;
    }

    public void display() {
        JWindow window = new JWindow();
        Point mousePos = MouseInfo.getPointerInfo().getLocation();
        int x = mousePos.x;
        int y = mousePos.y;

        JPanel panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                int width = g.getFontMetrics().stringWidth(message);
                int height = g.getFontMetrics().getHeight();

                g.setColor(Color.black);
                g.fillRect(10, 10, width + 30, height + 10);
                g.setColor(Color.black);
                g.drawRect(10, 10, width + 30, height + 10);

                g.setColor(new Color(255, 255, 255, 240));
                g.drawString(message, 25, 27);

                int opacity = 250;
                for (int i = 0; i < 4; i++) {
                    opacity -= 60;
                    g.setColor(new Color(0, 0, 0, opacity));
                    g.drawRect(10 - i, 10 - i, width + 30 + i * 2, height + 10 + i * 2);
                }
            }
        };
        
        window.setBackground(new Color(0, 0, 0, 0));
        window.add(panel);
        window.setLocation(x, y);
        window.setSize(300, 100);
        window.setVisible(true);

        Timer timer = new Timer(TOAST_DURATION / 10, new ActionListener() {
            float opacity = 1f;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacity -= 0.1f;
                if (opacity <= 0.5f) {
                    ((Timer) e.getSource()).stop();
                    window.setVisible(false);
                    window.dispose();
                } else {
                    window.setOpacity(opacity);
                }
            }
        });
        timer.setInitialDelay(TOAST_DURATION);
        timer.start();
    }
}
