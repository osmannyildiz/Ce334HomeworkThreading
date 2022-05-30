import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		String srcFileName = "img1";
		String srcFileExtension = "jpg";
		boolean multiThread = false;
		int numThreads = 32;
		
		System.out.println("start");
			
		try {
			File srcFile = new File("files/" + srcFileName + "." + srcFileExtension);
			BufferedImage srcImg = ImageIO.read(srcFile);
			int srcX = srcImg.getWidth();
			int srcY = srcImg.getHeight();
			int dstX = (int)Math.floor(srcX / 2);
			int dstY = (int)Math.floor(srcY / 2);
			BufferedImage dstImg = new BufferedImage(dstX, dstY, BufferedImage.TYPE_INT_RGB);
			int totalCount = dstX * dstY;
			
			// TODO Timer start
			if (multiThread) {
				runMultiThread(srcImg, dstImg, totalCount, numThreads);
			} else {
				runSingleThread(srcImg, dstImg, totalCount);
			}
			// TODO Timer end
			
			File dstFile = new File("files/" + srcFileName + "_out" + "." + srcFileExtension);
			ImageIO.write(dstImg, srcFileExtension.toUpperCase(), dstFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("end");
	}
	
	static void runSingleThread(BufferedImage srcImg, BufferedImage dstImg, int totalCount) {
		MyRunnable myRunnable = new MyRunnable(srcImg, dstImg, 0, totalCount);
		Thread myThread = new Thread(myRunnable);
		myThread.start();
		try {
			myThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	static void runMultiThread(BufferedImage srcImg, BufferedImage dstImg, int totalCount, int numThreads) {
		// TODO
		return;
	}

}
