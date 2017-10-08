package com.oracle.view;

import java.util.Scanner;

import com.oracle.entity.Player;
import com.oracle.service.PlayChess;

public class Chess {

	private Player p1 = new Player();
	private Player p2 = new Player();
	boolean flag = true;
	PlayChess chess = PlayChess.getInstance();

	// 扫描器
	@SuppressWarnings("resource")
	private String scannerString() {
		return new Scanner(System.in).next();
	}

	// 欢迎界面
	public void welcome() {
		System.out.println("------欢迎来到五子棋游戏------");
		System.out.println("  --------------------");
	}

	// 菜单界面
	public Integer enumLi() {
		System.out.println("1.选择棋盘大小--2.悔棋--3.从新开始--4.退出");
		try {
			Integer i = Integer.parseInt(scannerString());
			if (i >= 1 && i <= 4) {
				return i;
			}
			System.out.println("输入无效");
			enumLi();
		} catch (Exception e) {
			System.out.println("输入无效");
			enumLi();
		}
		return null;
	}

	// 棋盘大小
	public void boardSize() {
		System.out.println("1. 10 X 10");
		System.out.println("2. 15 X 15");
		System.out.println("3. 20 X 20");
		Integer size = null;
		try {
			size = Integer.parseInt(scannerString());
		} catch (Exception e) {
			System.out.println("输入无效,从新输入");
			boardSize();
		}
		switch (size) {
		case 1:
			System.out.println("选择10X10");
			chess.initBoard(10);
			break;

		case 2:
			System.out.println("选择15X15");
			chess.initBoard(15);
			break;

		case 3:
			System.out.println("选择20X20");
			chess.initBoard(20);
			break;

		default:
			System.out.println("选择无效 ,从新输入");
			boardSize();
			break;
		}
	}

	// 显示棋盘
	public void showBoard() {
		chess.drawingBoard();
	}

	// 输入坐标
	public void rowCol(boolean flag) {
		System.out.println("坐标：格式例如1,1");
		String s = null;
		if (flag) {
			s = p1.getChess();
			if (success(p2.getChess())) {
				System.out.println(p2.getName() + "胜利");
				play();
			} else {
				System.out.print(p1.getName() + "输入：");
			}
		} else {
			s = p2.getChess();
			if (success(p1.getChess())) {
				System.out.println(p1.getName() + "胜利");
				play();
			} else {
				System.out.print(p2.getName() + "输入：");
			}
		}
		String rolCol = scannerString();
		try {
			// Integer[] rowCol = chess.getRowCol(rolCol);
			chess.checkSize(rolCol);
			chess.checkHaveChess(rolCol);
			chess.setChess(s);
			System.out.println("输入Y确认,输入2悔棋，输入3从新开始，输入4退出");
			String str = scannerString();
			while (!str.equalsIgnoreCase("y") && !str.equalsIgnoreCase("2")
					&& !str.equalsIgnoreCase("3") && !str.equalsIgnoreCase("4")) {
				System.out.println("输入无效----");
				str = scannerString();
			}
			switch (str) {
			case "y":
				flag = !flag;
				showBoard();
				rowCol(flag);
				break;

			case "2":
				
				chess.backChess();
				showBoard();
				rowCol(flag);
				break;

			case "3":
				boardSize();
				break;

			case "4":
				System.exit(0);

			default:
				break;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			rowCol(flag);
		}

	}

	// 输入姓名
	public void setName() {
		System.out.print("player1姓名:");
		String name = scannerString();
		p1.setName(name);
		p1.setChess("*");
		System.out.println();
		System.out.print("player2姓名:");
		name = scannerString();
		p2.setName(name);
		p2.setChess("@");
		System.out.println();

	}

	// 判断成功
	public boolean success(String strChess) {
		return chess.success(strChess);
	}

	public void play() {
		welcome();
		while (true) {
			Integer enumLi = enumLi();
			switch (enumLi) {
			case 1:
				boardSize();
				showBoard();
				setName();
				rowCol(flag);

				break;

			case 2:
				//chess.backChess();
				break;

			case 3:
				boardSize();
				showBoard();
				setName();
				rowCol(flag);
				break;

			case 4:
				System.exit(0);
				break;

			default:
				System.out.println("输入无效");
				play();
				break;
			}
		}
	}
}
