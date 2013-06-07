package com.ipensee;

import java.util.concurrent.TimeoutException;

public class Entry {
	public static void execute(Thread task, long timeout) throws TimeoutException {
        task.start();
        try {
        	task.join(timeout);
        } catch (InterruptedException e) {
        /* if somebody interrupts us he knowswhat he is doing */
        }
        if (task.isAlive()) {
//        	task.interrupt();
//        	((ReadRSS) task).setStop(true);
        	System.out.println("Rss读取超时...");
        	System.exit(0);
//        	throw new TimeoutException("Rss读取超时...");
        }
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RssHandler.open();
			execute(new ReadRSS(), 1000*60*10);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			RssHandler.close();
		}
	}

}
