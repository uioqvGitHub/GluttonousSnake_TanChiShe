package org.uioqv.mygame;

import javax.swing.JFrame;

import static org.uioqv.mygame.SwingConsole.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.awt.event.*;

public class SheGround extends JFrame implements DrawAble{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//SheGround参数，行数，列数，Kuan大小
	public final int BOX_ROW_MAX = 30;
	public final int BOX_CAL_MAX = 30;
	public final int BOX_SIZE = 25;
	//空隙常量
	public final int SHANGBU_KONGXI = 50;
	public final int ZUOBU_KONGXI = 100;
	public final int YOUBU_KONGXI = 100;
	public final int XIABU_KONGXI = 50;
	//主体常量 蛇，食物，场地 //初始化顺序不可变食物要依据场地参数
	public static final SheGround sheGround = new SheGround();
	public static final OneShe oneShe = new OneShe();
	public static final OneFood food = new OneFood();
	//蛇最成长极限
	public final int SHE_MAX_SIZE = 30;	
	//当前界面状态初始为开始界面
	public JieMian status = JieMian.BEGIN;
	//键盘侦听
	private final KeyListener keyAdapter = new MyKeyAdapterForGame();
	//图像缓冲区
	private Image bufferImage;
	private ExecutorService exec;
	
	private SheGround(int x, int y, String title) {
		setLocation(x, y);
//		setUndecorated(true);
		getContentPane().setBackground(Color.BLACK);
		exec = Executors.newCachedThreadPool(
				new ThreadFactory() {			
					@Override
					public Thread newThread(Runnable r) {
						// TODO Auto-generated method stub
						Thread t = new Thread(r);
						t.setDaemon(true);
						return t;
					}
				});
	}	
	
	private SheGround(){
		this(100, 100, "小蛇");
	}
	
	public ExecutorService getThreadPool(){
		return exec;
	}
	
	public void endGame(){
		exec.shutdownNow();
		removeKeyListener(keyAdapter);
		if(exec.isShutdown())
			System.out.println("游戏结束");
		
	}
	public void check() {
		if(food.getRow() == oneShe.getRow()
				&& food.getCal() == oneShe.getCal()){
			food.eatUp();
			oneShe.add();
		}
		
		if(!oneShe.signal(BOX_ROW_MAX, BOX_CAL_MAX)){
			endGame();
		}
		if(oneShe.isAdult(SHE_MAX_SIZE)){
			endGame();
			System.out.println("恭喜过关！！");
		}
	}
	private class MyKeyAdapterForGame extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			super.keyPressed(e);
			switch(e.getKeyCode()) {
			case KeyEvent.VK_UP :  
				oneShe.setFX(FangXiang.SHANG); break;
			case KeyEvent.VK_DOWN : 
				oneShe.setFX(FangXiang.XIA); break;
			case KeyEvent.VK_LEFT : 
				oneShe.setFX(FangXiang.ZUO); break;
			case KeyEvent.VK_RIGHT : 
				oneShe.setFX(FangXiang.YOU); break;
			}
		}
	}
	public void begin(){
		addKeyListener(keyAdapter);
		exec.execute(new ContralForShe());
		exec.execute(new ContralForScreen());
		exec.execute(new ContralForFood());
	}


	@Override
	public void draw(Graphics g, int x, int y, int size) {
		Color c = g.getColor();
	    g.setColor(Color.WHITE);
	    g.fillRect(0, 0, getWidth(), getHeight());
	    g.setColor(c);
	    //根据当前状态画出对应的界面
	    switch (status) {
		case BEGIN:
			 Begin b = new Begin();
			 b.draw(g, getWidth()/2-b.getRow()*BOX_SIZE ,
					 getHeight()/2-b.getCal()*BOX_SIZE, size);
			break;
		case GAME:
		    drawGame(g, x, y, size); 
		    break;
		case STOP:	break;
		case END:	break;
		case SET:	break;
		}
	}
	public void drawGame(Graphics g, int x, int y, int size){
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.drawRect(ZUOBU_KONGXI, SHANGBU_KONGXI, 
				BOX_ROW_MAX * BOX_SIZE, BOX_CAL_MAX*BOX_SIZE);
		g.setColor(c);
		oneShe.draw(g,ZUOBU_KONGXI, SHANGBU_KONGXI, BOX_SIZE);
		food.draw(g, ZUOBU_KONGXI, SHANGBU_KONGXI, BOX_SIZE);
	}
	@Override
	public void paint(Graphics g) {
	// TODO Auto-generated method stub
		if(bufferImage==null){
		    // 创建缓冲区对象
			bufferImage = createImage(getWidth(), getHeight());
		}
	    Graphics bufferG = bufferImage.getGraphics();
		draw(bufferG, ZUOBU_KONGXI, SHANGBU_KONGXI, BOX_CAL_MAX);;
		g.drawImage(bufferImage, 0, 0, this);
	}
	public int getWidth(){
		return  BOX_ROW_MAX * BOX_SIZE + ZUOBU_KONGXI + YOUBU_KONGXI;
	}
	public int getHeight(){
		return BOX_CAL_MAX * BOX_SIZE + SHANGBU_KONGXI + XIABU_KONGXI;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sheGround.setTitle("abc");
		run(sheGround, sheGround.getWidth(), sheGround.getHeight());
		
	}

}
