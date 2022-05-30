import java.awt.Color;
import java.awt.image.BufferedImage;

public class MyRunnable implements Runnable {
	
	BufferedImage srcImg, dstImg;
	int start, count;
	int startDstX, startDstY;
	
	public MyRunnable(BufferedImage srcImg, BufferedImage dstImg, int start, int count) {
		this.srcImg = srcImg;
		this.dstImg = dstImg;
		this.start = start;
		this.count = count;
		
		this.startDstX = start % dstImg.getWidth();
		this.startDstY = (int)Math.floor(start / dstImg.getWidth());
	}

	@Override
	public void run() {
		int currDstX = startDstX, currDstY = startDstY;
		int currSrcX, currSrcY;
		Color[] currSrcColors = new Color[4];
		Color currAverageColor;
		
		for (int i = 0; i < count; i++) {
			currSrcX = 2 * currDstX;
			currSrcY = 2 * currDstY;
			
			currSrcColors[0] = new Color(srcImg.getRGB(currSrcX, currSrcY));
			currSrcColors[1] = new Color(srcImg.getRGB(currSrcX + 1, currSrcY));
			currSrcColors[2] = new Color(srcImg.getRGB(currSrcX, currSrcY + 1));
			currSrcColors[3] = new Color(srcImg.getRGB(currSrcX + 1, currSrcY + 1));
			
			currAverageColor = getAverageColor(currSrcColors);
			dstImg.setRGB(currDstX, currDstY, currAverageColor.getRGB());
			
			int[] temp = getNextCoords(currDstX, currDstY, dstImg.getWidth());
			currDstX = temp[0];
			currDstY = temp[1];
		}
	}
	
	int[] getNextCoords(int currX, int currY, int maxX) {
		currX++;
		if (currX == maxX) {
			currX = 0;
			currY++;
		}
		
		int[] ret = {currX, currY}; 
		return ret;
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
