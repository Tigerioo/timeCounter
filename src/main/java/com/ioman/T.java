package com.ioman;

/**
 * <p>Title: com.ioman</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/13/17
 */
public class T {
	
	public static void main(String[] args) throws InterruptedException {
		/**
		 * 1502589192655
		 1502589202656
		 1502589212660
		 1502589222664
		 */
		
		long startTimeMillis = 1502589192615L;
		long endTimeMillis = 1502589222624L;
		
		long currentTimeMillis = 1502589202686L;
		
		long usedSec = (currentTimeMillis - startTimeMillis)/1000;
		long leftSec = (endTimeMillis - currentTimeMillis)/1000;
		
		System.out.println("usedSec=" + usedSec + ", leftSec=" + leftSec);
	}
	
}
