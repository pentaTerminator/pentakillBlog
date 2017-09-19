package org.pentakill.blog.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.util.Assert;

public class CommonUtils {

	public static int getMax(int[] arrs) {
		if (arrs == null) {
			Assert.isNull(arrs, "数组为空，无法获取最大值");
		}
		int min = Integer.MIN_VALUE;
		for (int i = 0; i < arrs.length; i++) {
			if (arrs[i] > min) {
				min = arrs[i];
			}
		}
		return min;
	}

	public static int getValue(int[] arrs) {
		int ret = 0;
		for (int i = 0; i < arrs.length; i++) {
			ret += arrs[i];
		}
		return ret;
	}

	public static int[] getArrasMaxValue(int[][] arrass) {

		int[] ret = new int[arrass.length];

		ExecutorService threadPool = Executors.newFixedThreadPool(arrass.length);
		for (int i = 0; i < arrass.length; i++) {
			final int[] temp = arrass[i];
			Future<Integer> future = threadPool.submit(new Callable<Integer>() {
				@SuppressWarnings("static-access")
				public Integer call() throws Exception {
					return new CommonUtils().getMax(temp);
				}
			});
			try {
				ret[i] = future.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	
}