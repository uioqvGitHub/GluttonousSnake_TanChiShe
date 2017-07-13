package org.uioqv.mygame;

import java.util.concurrent.TimeUnit;

public class ContralForScreen implements Runnable {
	private int timeout = 100;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(timeout);

				SheGround.sheGround.repaint();
			}
		} catch(InterruptedException e){

			new RuntimeException("屏幕控制故障");
		}
		System.out.println("屏幕控制中止");
	}
}
