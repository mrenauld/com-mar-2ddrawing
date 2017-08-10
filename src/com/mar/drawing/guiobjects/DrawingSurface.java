package com.mar.drawing.guiobjects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 * Drawing surface for 2D drawing. There are two referentials:
 * <p>
 * 1) The "pixel" referential using {@link Pixel} objects to represent the
 * pixels of the surface. The axis are the following:
 *
 * <pre>
 * (0,0)      (W,0)
 *      +----+
 *      |    |
 *      |    |
 *      +----+
 * (0,H)      (W,H)
 * </pre>
 *
 * with W and H the width and height of the surface.
 * <p>
 * 2) The "coordinate" referential using {@link Coord} objects to represent the
 * coordinates mapped to the pixels. The axis are the following:
 *
 * <pre>
 *     ^ y
 *     |
 *     |
 *     +----> x
 * </pre>
 *
 * The two referentials are mapped as follows: the center pixel of the surface
 * (W/2,H/2) is mapped to the coordinate {@link #coordCenter}. The width/height
 * in pixels of the surface is mapped to
 * {@link #coordLengthX}/{@link #coordLengthY}. For instance, with
 * {@link #coordCenter}=(0,0) and {@link #coordLengthX}={@link #coordLengthY}=2,
 * the corner pixels of the surface are mapped as follows:
 *
 * <pre>
 *  (-1,1)      (1,1)
 *        +----+
 *        |    |
 *        |    |
 *        +----+
 * (-1,-1)      (1,-1)
 * </pre>
 *
 * @author mrenauld
 *
 */
public class DrawingSurface extends JPanel implements MouseListener, KeyListener {

    private static final long serialVersionUID = -4781541505424780115L;

    /** Default panel width. */
    public static final int DEFAULT_WIDTH = 500;
    /** Default panel height. */
    public static final int DEFAULT_HEIGHT = 500;

    /** Coordinates of the center of the displayed plane. */
    protected Coord coordCenter = new Coord(0.0, 0.0);
    /** Length of the X-axis of the displayed plane. */
    protected double coordLengthX = 1.0;
    /** Length of the Y-axis of the displayed plane. */
    protected double coordLengthY = 1.0;

    /** Pixel of the last mousePressed event. */
    protected Pixel mousePressed = new Pixel();
    /** Pixel of the last mouseReleased event. */
    protected Pixel mouseReleased = new Pixel();

    /**
     * Constructs a new DrawingSurface with default size. This also calls the
     * method initCompute().
     */
    public DrawingSurface() {
        /* Dimension. */
        final Dimension dimension = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setSize(dimension);
        setPreferredSize(dimension);

        /* Listeners. */
        addMouseListener(this);
        addKeyListener(this);

        /* Set focus for the KeyListener. */
        setFocusable(true);
        this.requestFocus();

        /* Initialization. */
        initVariables();
        initCompute();
    }

    /**
     * Returns a {@link Coord} object representing the center of the displayed
     * plane.
     *
     * @return
     */
    public Coord getCoordCenter() {
        return coordCenter;
    }

    /**
     * Returns the coordinate length of the X-axis of the displayed plane.
     *
     * @return
     */
    public double getCoordLengthX() {
        return coordLengthX;
    }

    /**
     * Returns the coordinate length of the Y-axis of the displayed plane.
     *
     * @return
     */
    public double getCoordLengthY() {
        return coordLengthY;
    }

    @Override
    public void keyPressed(final KeyEvent e) {
    }

    @Override
    public void keyReleased(final KeyEvent e) {
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
    }

    @Override
    public void mouseExited(final MouseEvent e) {
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        mousePressed.w = e.getX();
        mousePressed.h = e.getY();
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        mouseReleased.w = e.getX();
        mouseReleased.h = e.getY();
        this.requestFocus();
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    /**
     * Returns the {@link Pixel} mapped to the specified {@link Coord}.
     *
     * @param coord
     * @return
     */
    protected Pixel coordToPixel(final Coord coord) {
        final Dimension size = this.getSize();
        final int w = (int) Math.round((coord.x - coordCenter.x + coordLengthX / 2.0) / coordLengthX * size.width);
        final int h = (int) Math.round((coordCenter.y - coord.y + coordLengthY / 2.0) / coordLengthY * size.height);
        return new Pixel(w, h);
    }

    /**
     * Paints the panel.
     *
     * @param g
     */
    protected void doDrawing(final Graphics g) {
        // To fill.
    }

    /**
     * Initialization.
     */
    protected void initCompute() {
        // To fill.
    }

    /**
     * Method used in child classes to override some variables values before the
     * initCompute() step.
     */
    protected void initVariables() {

    }

    /**
     * Returns the {@link Coord} mapped to the specified {@link Pixel}.
     *
     * @param pixel
     * @return
     */
    protected Coord pixelToCoord(final Pixel pixel) {
        final Dimension size = this.getSize();
        final double x = ((double) pixel.w) / size.width * coordLengthX - coordLengthX / 2.0 + coordCenter.x;
        final double y = -((double) pixel.h) / size.height * coordLengthY + coordLengthY / 2.0 + coordCenter.y;
        return new Coord(x, y);
    }

    /**
     * Draws the axis.
     *
     * @param g
     */
    protected void showAxis(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));

        Coord x1 = new Coord(coordCenter.x - coordLengthX / 2.0, 0.0);
        Coord x2 = new Coord(coordCenter.x + coordLengthX / 2.0, 0.0);
        Coord y1 = new Coord(0.0, coordCenter.y - coordLengthY / 2.0);
        Coord y2 = new Coord(0.0, coordCenter.y + coordLengthY / 2.0);

        Pixel px1 = coordToPixel(x1);
        Pixel px2 = coordToPixel(x2);
        Pixel py1 = coordToPixel(y1);
        Pixel py2 = coordToPixel(y2);

        g2d.drawLine(px1.w, px1.h, px2.w, px2.h);
        g2d.drawLine(py1.w, py1.h, py2.w, py2.h);
    }

    /**
     * Draws the background of the panel in white and a grid in gray. The grid
     * size is 1.0 in coordinate referential.
     *
     * @param g
     */
    protected void showGrid(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.WHITE);
        Dimension size = this.getSize();
        g.fillRect(0, 0, size.width, size.height);

        g.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(1));

        int minX = (int) (coordCenter.x - coordLengthX / 2.0);
        int maxX = (int) (coordCenter.x + coordLengthX / 2.0);
        int minY = (int) (coordCenter.y - coordLengthY / 2.0);
        int maxY = (int) (coordCenter.y + coordLengthY / 2.0);
        for (int i = minX; i <= maxX; ++i) {
            Pixel pix1 = coordToPixel(new Coord(i, minY));
            Pixel pix2 = coordToPixel(new Coord(i, maxY));
            g2d.drawLine(pix1.w, pix1.h, pix2.w, pix2.h);
        }
        for (int i = minY; i <= maxY; ++i) {
            Pixel pix1 = coordToPixel(new Coord(minX, i));
            Pixel pix2 = coordToPixel(new Coord(maxX, i));
            g2d.drawLine(pix1.w, pix1.h, pix2.w, pix2.h);
        }
    }

    /**
     * Draws a black dot at the specified {@link Pixel}.
     *
     * @param g
     * @param pPixel
     */
    protected void showPixel(Graphics g, Pixel pPixel) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(5));

        g2d.drawLine(pPixel.w, pPixel.h, pPixel.w, pPixel.h);
    }
}
