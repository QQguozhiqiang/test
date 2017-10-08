package com.oracle.service;

import java.util.Arrays;

import com.oracle.entity.Board;

public class PlayChess {

	private static PlayChess chess = null;
	// 棋盘
	private Board board = Board.getInstance();

	private PlayChess() {
	}

	public static PlayChess getInstance() {
		if (chess == null) {
			synchronized (PlayChess.class) {
				if (chess == null) {
					chess = new PlayChess();
				}
			}
		}
		return chess;
	}

	// 初始化棋盘
	public void initBoard(Integer size) {
		String[][] boards = new String[size][size];
		for (String[] str : boards) {
			Arrays.fill(str, "+");
		}
		board.setBoards(boards);
	}

	// 画棋盘
	public void drawingBoard() {
		String[][] boards = board.getBoards();
		for (String[] str : boards) {
			for (String s : str) {
				System.out.print(s + " ");
			}
			System.out.println();
		}
		board.setBoards(boards);
		/*
		 * for(String[] str:boards){ for(String s:str){ System.out.print(s+" ");
		 * } System.out.println(); }
		 */
		// System.out.println(Arrays.toString(boards));
	}

	// 输入坐标
	public Integer[] getRowCol(String rowsCols) throws Exception {
		String[] rowCol = rowsCols.split(",");
		// System.out.println(Arrays.toString(rowCol));
		if (rowCol.length != 2) {
			throw new Exception("输入坐标格式有误");
		}
		Integer row;
		Integer col;
		try {
			row = Integer.parseInt(rowCol[0]);
			col = Integer.parseInt(rowCol[1]);
		} catch (Exception e) {
			throw new Exception("横/纵坐标格式错误");
		}
		return new Integer[] { row, col };
	}

	// 校验坐标
	public void checkSize(String rowsCols) throws Exception {
		Integer[] rowCol = getRowCol(rowsCols);
		if (rowCol[0] < 0 || rowCol[0] > board.getBoards()[0].length-1) {
			throw new Exception("横坐标范围错误");
		} else if (rowCol[1] < 0 || rowCol[1] > board.getBoards().length-1) {
			throw new Exception("纵坐标范围错误");
		}

	}

	// 校验横坐标
	public boolean checkRowSize(Integer row) {

		if (row <= 0 || row >= board.getBoards()[0].length - 1) {
			return false;
		}
		return true;
	}

	// 校验纵坐标
	public boolean checkColSize(Integer col) {

		if (col <= 0 || col >= board.getBoards()[0].length - 1) {
			return false;
		}
		return true;
	}

	// 校验坐标是否有棋子
	public void checkHaveChess(String rowsCols) throws Exception {
		Integer[] rowCol = getRowCol(rowsCols);
		if (!board.getBoards()[rowCol[0]][rowCol[1]].equals("+")) {
			throw new Exception("此步期已经有棋子！");
		}
		board.setLastStep(rowsCols);
		board.setRow(rowCol[0]);
		board.setCol(rowCol[1]);
	}

	// 下棋
	public void setChess(String chess) {
		board.getBoards()[board.getRow()][board.getCol()] = chess;
	}

	// 悔棋
	public void backChess() {
		board.getBoards()[board.getRow()][board.getCol()] = "+";
	}

	// 从新开始
	public void backPlay(Integer size) {
		initBoard(size);
	}

	// 校验成功
	public boolean success(String chess) {
		int count = 1;
		String[][] boards = board.getBoards();
		Integer row = board.getRow();
		Integer col = board.getCol();
		if (row != null && row > 0) {
			while (boards[--row][col].equals(chess)) {
				++count;
				if (!checkRowSize(row)) {
					break;
				}
			}
			row = board.getRow();
			int i = board.getBoards()[0].length - 1;
			System.out.println(i);
		}
		if (row != null && row < board.getBoards()[0].length - 1) {
			while (boards[++row][col].equals(chess)) {
				++count;
				if (!checkRowSize(row)) {
					break;
				}
			}
			if (count >= 5)
				return true;
			count = 0;
			row = board.getRow();
		}
		if (col != null && col > 0) {
			while (boards[row][--col].equals(chess)) {
				++count;
				if (!checkColSize(col)) {
					break;
				}
			}
			col = board.getCol();
		}
		if (col != null && col < board.getBoards().length - 1) {
			while (boards[row][++col].equals(chess)) {
				count++;
				if (!checkColSize(col)) {
					break;
				}
			}
			if (count >= 5)
				return true;
			count = 0;
			col = board.getCol();
		}
		if (row != null && row > 0 && col != null && col > 0) {
			while (boards[--row][--col].equals(chess)) {
				count++;
				if (!checkRowSize(row) && !checkColSize(col)) {
					break;
				}
			}
			row = board.getRow();
			col = board.getCol();
		}
		if (row != null && col != null && row < board.getBoards()[0].length - 1
				&& col < board.getBoards().length - 1) {
			while (boards[++row][++col].equals(chess)) {
				count++;
				if (!checkRowSize(row) && !checkColSize(col)) {
					break;
				}
			}
			if (count >= 5)
				return true;
			count = 0;
			row = board.getRow();
			col = board.getCol();
		}
		if (row != null && col != null && row > 0
				&& col < board.getBoards().length - 1) {
			while (boards[--row][++col].equals(chess)) {
				count++;
				if (!checkRowSize(row) && !checkColSize(col)) {
					break;
				}
			}
			row = board.getRow();
			col = board.getCol();
		}
		if (row != null && col != null && row < board.getBoards()[0].length - 1
				&& col > 0) {
			while (boards[++row][--col].equals(chess)) {
				count++;
				if (!checkRowSize(row) && !checkColSize(col)) {
					break;
				}
			}
			if (count >= 5)
				return true;
		}
		System.out.println(count);
		return false;

		
	}

}
