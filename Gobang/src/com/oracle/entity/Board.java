package com.oracle.entity;

/**
 * 棋盘
 * 单例模式
 * @author gzq
 *
 */
public class Board {

	//横坐标
	private Integer row;
	//纵坐标
	private Integer col;
	//上一步
	private String lastStep;
	//棋盘
	private String[][] boards;
	//Board对象
	private static Board board = null;
	private Board(){}
	public static Board getInstance(){
		if(board == null){
			synchronized (Board.class) {
				if(board == null){
					board = new Board();					
				}
			}
		}
		
		return board;
	}
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public Integer getCol() {
		return col;
	}
	public void setCol(Integer col) {
		this.col = col;
	}
	public String getLastStep() {
		return lastStep;
	}
	public void setLastStep(String lastStep) {
		this.lastStep = lastStep;
	}
	public String[][] getBoards() {
		return boards;
	}
	public void setBoards(String[][] boards) {
		this.boards = boards;
	}
}
