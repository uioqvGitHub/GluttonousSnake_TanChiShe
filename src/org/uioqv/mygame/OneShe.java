package org.uioqv.mygame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;


public class OneShe implements DrawAble {
	private int sheLenght = 0;
	private FangXiang FX = FangXiang.SHANG;
	private She head;
	private She tail;
	List<She> she = new ArrayList<She>();
	public OneShe( int row, int cal, int firstLenght){
		head = new She(row, cal);
		tail = head;
		add(head);
		for(int i = 1; i<firstLenght; i++){
			add();
		}
	}
	public OneShe() {
		this(15, 15, 5);
	}
	private void add(She s){
		she.add(s);
		tail = s;
		sheLenght++;
	}
	private void add(int row, int cal) {
		add(new She(row, cal));
	}
	public void add() {
		int r = tail.getRow();
		int c = tail.getCal();
		switch(getTailFX()){
		case SHANG : r--; break;
		case XIA : r++; break;
		case ZUO : c--; break;
		case YOU : c++; break;
		}
		add(r, c);
	}
	private FangXiang getTailFX() {
		if(she.size()<2) return FangXiang.contraryFangXiang(FX);
		She s = she.get(she.size()-2);
		int c = tail.getCal() - s.getCal();
		int r = tail.getRow() - s.getRow();
		if(r<0) return FangXiang.SHANG;
		if(r>0) return FangXiang.XIA;
		if(c<0) return FangXiang.ZUO;
		if(c>0) return FangXiang.YOU;
		throw new RuntimeException("蛇尾巴行进方向判断故障");
	}
	public synchronized void setFX( FangXiang f) {
		if(FangXiang.isInvalid(f, FX)){
			System.out.println("无效方向！！");
			return;
		}
		FX = f;                                                                                              
	}
	public synchronized FangXiang getFX(){
		return FX;
	}
	public void move() {
		She s1 = new She(head), s2;
		head.move(getFX());
		for(int i = 1; i<she.size(); i++){
			She s = she.get(i);
			s2 = new She(s);
			s.move(s1);
			s1 = s2;				
		}
	}
	public void draw(Graphics g, int x, int y, int size){
		Color c = g.getColor();
		g.setColor(Color.BLUE);
		for(She s : she) {
			s.draw(g, x, y, size);
		}
		g.setColor(c);
	}
	//发出信号，越过边界或者咬到自己发出false 否则true
	public boolean signal(int width, int height){
		int[][] count = new int[width][height];
		for(int i = 0; i<count.length; i++)
			for(int j = 0; j<count[i].length; j++)
				count[i][j] = 0;
		try {
			for(She s : she){
				int num = ++count[s.getRow()][s.getCal()];
				if(num>1)
					return false;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	public boolean isAdult(int broundSize){
		return sheLenght >= broundSize;
	}
	public int getMyLenght(){ return sheLenght; }
	public int getRow() { return head.getRow(); }
	public int getCal() { return head.getCal(); }
	public String toString() {
		return she.toString();
	}
	
	private class She extends Kuan implements DrawAble{
		private final int id = getMyLenght();
		public She(int row, int cal) {
			super(row, cal);
		}
		public She(She s){
			this(s.getRow(),s.getCal());
		}
		public void move(FangXiang f){
			switch(f){
			case SHANG : setRow(getRow()-1); break;
			case XIA : setRow(getRow()+1);  break;
			case ZUO : setCal(getCal()-1);  break;
			case YOU : setCal(getCal()+1);  break;
			}
		}
		public void move(She s) {
			setRow(s.getRow());
			setCal(s.getCal());
		}
		public String toString() {
			return "she " + id + " " +
					getRow() +" : " +getCal();
		}

		@Override
		public void draw(Graphics g, int x, int y, int size) {
			// TODO Auto-generated method stub
			int x1 = x+size*getCal();
			int y1 = y+size*getRow();
			if(id == 0){		
				g.fillOval(x1, y1, size, size);
			}else{				
				super.draw(g, x1, y1, size);
			}
		}
	}


}


