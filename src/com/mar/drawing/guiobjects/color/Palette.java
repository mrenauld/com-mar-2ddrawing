package com.mar.drawing.guiobjects.color;

import java.awt.Color;

import com.mar.algotools.mathematics.utils.MathOps;

public class Palette {

	/** RGB colors. */
	private float[][] paletteRGB;

	public Palette() {
		paletteRGB = new float[0][0];
	}

	public Palette(final int nbColors) {
		paletteRGB = new float[nbColors][3];
	}

	public Color getColor(final int idx) {
		return new Color(paletteRGB[idx][0], paletteRGB[idx][1],
				paletteRGB[idx][2]);
	}

	public Color getColorMod(int idx) {
		idx = MathOps.mod(idx, paletteRGB.length);
		return getColor(idx);
	}

	public float[] getColorRGB(final int idx) {
		return paletteRGB[idx];
	}

	public float[] getColorRGBMod(int idx) {
		idx = MathOps.mod(idx, paletteRGB.length);
		return getColorRGB(idx);
	}

	public int getNbColors() {
		return paletteRGB[0].length;
	}

	public float[][] getPalette() {
		return paletteRGB;
	}

	public void setColorRGB(final int idx, final float r, final float g,
			final float b) {
		paletteRGB[idx][0] = r;
		paletteRGB[idx][1] = g;
		paletteRGB[idx][2] = b;
	}

	public void setPalette(final float[][] paletteRGB) {
		this.paletteRGB = paletteRGB;
	}
}