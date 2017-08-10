package com.mar.drawing.guiobjects;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawingFrame extends JFrame {

    private static final long serialVersionUID = 2367300060124574610L;

    public static final int DEFAULT_POSX = 200;

    public static final int DEFAULT_POSY = 50;

    protected final DrawingSurface surface;
    private JPanel contentPanel;

    public DrawingFrame(final DrawingSurface pSurface) {
        surface = pSurface;
        init();
    }

    private void init() {
        contentPanel = new JPanel();
        contentPanel.add(surface);

        setContentPane(contentPanel);

        this.setSize(surface.getWidth(), surface.getHeight());
        this.setLocation(DEFAULT_POSX, DEFAULT_POSY);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
