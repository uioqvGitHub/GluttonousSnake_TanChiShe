package org.uioqv.mygame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Begin implements DrawAble{
	private int row = 12;
	private int cal = 2;
	private int x = -1;
	private int y = -1;
	private int size = -1;
	@Override
	public void draw(Graphics g, int x, int y, int size) {
		// TODO Auto-generated method stub
		this.x = x;
		this.y = y;
		this.size = size;
		Font f = g.getFont();
		g.setFont(new Font(null, Font.BOLD, size));
		drawString(g, "欢迎来到贪吃蛇游戏！！！", x, y, size);
		drawString(g, "一一一开始游戏！！！", x, y, size);
		g.setFont(f);
	}
	public void drawString(Graphics g, String s, int x, int y, int size){
		g.drawString(s, x, y+(cal-2)*size);
		cal++;
		if(s.length() > row)
			row = s.length();
	}
	public int getRow(){
		return row;
	}
	public int getCal(){
		return cal;
	}
	//点击到有效范围返回true
	public boolean isClicked(int x, int y){
//		if()
		return true;
	}
	class MyClinkForBegin extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

}
