import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		String srcFileName = "img1";
		String srcFileExtension = "jpg";
		int numIterations = 100;
		boolean multiThread = true;
		int numThreads = 8;
		
		System.out.println("Starting...");
			
		try {
			File srcFile = new File("files/" + srcFileName + "." + srcFileExtension);
			BufferedImage srcImg = ImageIO.read(srcFile);
			BufferedImage dstImg = null;
			long startTime, endTime;
			
			startTime = System.nanoTime();
			System.out.println("Starting processing at " + startTime);
			
			for (int i = 0; i < numIterations; i++) {			
				int srcX = srcImg.getWidth();
				int srcY = srcImg.getHeight();
				int dstX = (int)Math.floor(srcX / 2);
				int dstY = (int)Math.floor(srcY / 2);
				dstImg = new BufferedImage(dstX, dstY, BufferedImage.TYPE_INT_RGB);
				int totalCount = dstX * dstY;
				
				if (multiThread) {
					runMultiThread(srcImg, dstImg, totalCount, numThreads);
				} else {
					runSingleThread(srcImg, dstImg, totalCount);
				}
				
//				srcImg = dstImg;
			}
			
			endTime = System.nanoTime();
			System.out.println("Ended processing at " + endTime);
			System.out.println("The processing took " + ((endTime - startTime) / 1000000) + " milliseconds.");
			
			File dstFile = new File("files/" + srcFileName + "_out" + "." + srcFileExtension);
			ImageIO.write(dstImg, srcFileExtension.toUpperCase(), dstFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Done.");
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
		int countPerThread = (int)Math.ceil(totalCount / numThreads);
		
		Thread[] myThreads = new Thread[numThreads];
		for (int i = 0; i < numThreads; i++) {
			myThreads[i] = new Thread(new MyRunnable(srcImg, dstImg, i * countPerThread, countPerThread));
			myThreads[i].start();
		}
		
		try {
			for (int i = 0; i < numThreads; i++) {
				myThreads[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
