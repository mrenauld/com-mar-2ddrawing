package com.mar.drawing.guiobjects;

import java.awt.Dimension;

public class DrawingUtils {

	public static double[][] getCoordArrayForPixelsOfSurface(
			final DrawingSurface surf) {
		final Dimension dimension = surf.getSize();

		final double[][] coordArray = new double[2][];
		coordArray[0] = new double[dimension.width];
		coordArray[1] = new double[dimension.height];

		final Pixel pixel = new Pixel();
		Coord coord;
		for (int i = 0; i < dimension.width; ++i) {
			pixel.w = i;
			coord = surf.pixelToCoord(pixel);
			coordArray[0][i] = coord.x;
		}

		for (int i = 0; i < dimension.height; ++i) {
			pixel.h = i;
			coord = surf.pixelToCoord(pixel);
			coordArray[1][i] = coord.y;
		}

		return coordArray;
	}

}