package org.uioqv.mygame;

import java.awt.Color;
import java.awt.Graphics;

public class OneFood implements DrawAble{
	private final Food emptyFood = new Food(-1, -1){
		public void draw(Graphics g, int x, int y, int size) {
		}
	};
	private Food food = emptyFood;
	public void feed(int x, int y){
		food = new Food(x, y);
	}
	public boolean isEmpty(){
		return food == emptyFood;
	}
	public void eatUp(){
		food = emptyFood;
	}
	public int getRow() { return food.getRow(); }
	public int getCal() { return food.getCal(); }
	public String toString(){
		return food.toString();
	}
	
	public void draw(Graphics g, int x, int y, int size) {
		// TODO Auto-generated method stub
		Color c =g.getColor();
		g.setColor(Color.WHITE);
		food.draw(g, x, y, size);
		g.setColor(c);
	}
	
	private class Food extends Kuan implements DrawAble {
		public Food(int row, int cal){
			super(row, cal);
		}
		public String toString() {
			return "food " + getRow() + " : " + getCal(); 
		}

		@Override
		public void draw(Graphics g, int x, int y, int size) {
			// TODO Auto-generated method stub
			int x1 = x+size*getCal();
			int y1 = y+size*getRow();		
			g.fillOval(x1, y1, size, size);
		}
	}
}
