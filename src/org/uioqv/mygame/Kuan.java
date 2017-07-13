package org.uioqv.mygame;

import java.awt.Graphics;

public class Kuan implements DrawAble {
	private int row;
	private int cal;
	public Kuan(int row, int cal) {
		// TODO Auto-generated constructor stub
		this.row = row;
		this.cal = cal;
	}
	@Override
	public void draw(Graphics g, int x, int y, int size) {
		// TODO Auto-generated method stub
		g.fillRect(x, y, size, size);
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCal() {
		return cal;
	}
	public void setCal(int cal) {
		this.cal = cal;
	}


}
