import java.awt.Color;
import java.awt.image.BufferedImage;

public class MyRunnable implements Runnable {
	
	BufferedImage srcImg, dstImg;
	int start, count;
	int startDstX, startDstY;
	
	public MyRunnable(BufferedImage srcImg, BufferedImage dstImg, int start, int count) {
		this.srcImg = srcImg;
		this.dstImg = dstImg;
//		this.start = start;
		this.count = count;
		
		this.startDstX = start % dstImg.getWidth();
		this.startDstY = (int)Math.floor(start / dstImg.getWidth());
	}

	@Override
	public void run() {
		int currDstX = startDstX, currDstY = startDstY;
		Color[] currSrcColors = new Color[4];
		Color currAverageColor;
		
		for (int i = 0; i < count; i++) {
			// TODO
			currAverageColor = 
			dstImg.setRGB(currDstX, currDstY, );
		}
	}
	
	Color getAverageColor(Color... colors) {
		int red = 0, green = 0, blue = 0;
		
		for (Color c : colors) {
			red += c.getRed();
			green += c.getGreen();
			blue += c.getBlue();
		}
		
		int numColors = colors.length;
		red /= numColors;
		green /= numColors;
		blue /= numColors;
		
		return new Color(red, green, blue);
	}

}
