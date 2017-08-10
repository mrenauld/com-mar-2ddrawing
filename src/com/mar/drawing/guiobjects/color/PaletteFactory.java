package com.mar.drawing.guiobjects.color;

public class PaletteFactory {

	public static Palette getBlackAndWhitePalette(final int nbColors) {
		final Palette palette = new Palette(nbColors);

		for (int i = 0; i < nbColors; ++i) {
			final float v = i / (float) nbColors;
			palette.setColorRGB(i, v, v, v);
		}

		return palette;
	}

	public static Palette getColorPalette1() {

		final float[][] rgb = { { 0f, 0f, 0f }, { 0f, 0f, 0.5f },
				{ 0f, 0f, 1f }, { 0f, 0.5f, 1f }, { 0.8f, 1f, 1f },
				{ 1f, 1f, 0.6f }, { 1f, 1f, 0f }, { 1f, 0.6f, 0f },
				{ 0.6f, 0.3f, 0f }, { 0.3f, 0.1f, 0f } };

		final Palette palette = new Palette();
		palette.setPalette(rgb);

		return palette;
	}

}