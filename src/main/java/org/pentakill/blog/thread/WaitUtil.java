package org.pentakill.blog.thread;

public class WaitUtil {

	public static void WaitSecond(int sencond) {
		try {
			Thread.sleep(sencond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
