package org.uioqv.mygame;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ContralForFood implements Runnable {

	private int timeout = 500;
	private Random rd = new Random(
			TimeUnit.MILLISECONDS.ordinal());
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(timeout);				
				if(SheGround.food.isEmpty()){
					SheGround.food.feed(
							rd.nextInt(SheGround.sheGround.BOX_ROW_MAX), 
							rd.nextInt(SheGround.sheGround.BOX_CAL_MAX));
//					System.out.println(SheGround.food);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			new RuntimeException("食物控制故障");
		}
		System.out.println("食物控制中止");		
	}

}
