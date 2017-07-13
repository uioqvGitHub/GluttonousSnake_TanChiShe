package org.uioqv.mygame;

import java.util.concurrent.TimeUnit;


public class ContralForShe implements Runnable{
	private int timeout = 200;
	
	public boolean isDeath() {
//		She.oShe.
		return false;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(timeout);	
				SheGround.oneShe.move();	
				SheGround.sheGround.check();		
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			new RuntimeException("蛇控制故障");
		}
		System.out.println("蛇控制中止");
	}

}
