package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 将抽奖器需要的静态变量和静态方法抽取到这里
 * 需要使用再调用
 * @author xianJieHao
 *
 */
public class Var {
	
	/** 定义属性 */
	//游戏抽奖次数
	static final int SUM = 5;
	
	/** 定义图形各个组件坐标 */
	// 抽奖按钮坐标
 	static final int START_BUTTON_X = 180;
 	static final int START_BUTTON_Y = 300;
    
	// 最小化坐标
	static final int MIN_BUTTON_X = 795;
	static final int MIN_BUTTON_Y = 0;
		
	// 关闭按钮坐标
	static final int COLSE_BUTTON_X = 840;
	static final int CLOSE_BUTTON_Y = 13;
		
	// 添加名单坐标
	static final int ADD_BUTTON_X = 600;
	static final int ADD_BUTTON_Y = 480;
	
	// 重新抽奖坐标
    static final int RESTART_BUTTON_X = 640;
    static final int RESTART_BUTTON_Y = 430;
	
	// 滚动栏开始Y坐标
	static final int ROLL_TOP = 180;
	//滚动栏下部Y坐标
	static final int ROLL_BOTTOM = 250;
	//滚动栏停止时文字显示在中间的位置Y
	static final int ROLL_MIDDLE = 220;
	// 中奖名单坐标
	static final int LIST_X = 590;
	
    static final int LIST_Y = 120;
    
	// 中奖名单 行间距离
	static final int ROW_LEN = 40;
	
	// 滚动栏名字X坐标
	static final int ROLL_NAME = 85;
	
	// 滚动栏号码X坐标
	static final int ROLL_PHONE = 290;
	
	// 抽奖剩余次数坐标
	static final int COUNT_X = 320;
	static final int COUNT_Y = 410;

	private static int SAVE_BUTTON_X = 180;
	private static int SAVE_BUTTON_Y = 300;
	
	//初始化静态资源
	public static BufferedImage add;
	public static BufferedImage again;
	public static BufferedImage back;
	public static BufferedImage close;
	public static BufferedImage icon;
	public static BufferedImage min;
	public static BufferedImage start;
	public static BufferedImage stop;
	public static BufferedImage add_back;

	static {
		try {
			add = ImageIO.read(Main.class.getResource("add.png"));
			again = ImageIO.read(Main.class.getResource("again.png"));
			back = ImageIO.read(Main.class.getResource("back.png"));
			close = ImageIO.read(Main.class.getResource("close.png"));
			icon = ImageIO.read(Main.class.getResource("icon.jpg"));
			min = ImageIO.read(Main.class.getResource("min.png"));
			start = ImageIO.read(Main.class.getResource("start.png"));
			stop = ImageIO.read(Main.class.getResource("stop.png"));
			add_back = ImageIO.read(Main.class.getResource("add_back.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	 // 判断点击最小化按钮
	public static boolean clickMinButton(int xx,int yy) {
		return xx > MIN_BUTTON_X && xx < MIN_BUTTON_X + min.getWidth() && yy > MIN_BUTTON_Y
				&& yy < MIN_BUTTON_Y + min.getHeight();
	}

	// 判断点击关闭按钮
	public static boolean clickCloseButton(int xx,int yy) {
		return xx > COLSE_BUTTON_X && xx < COLSE_BUTTON_X + close.getWidth() && yy > CLOSE_BUTTON_Y
				&& yy < CLOSE_BUTTON_Y + close.getHeight();

	}
	
	// 判断点击添加按钮
	public static boolean clickAddButton(int xx,int yy) {
		return xx > ADD_BUTTON_X && xx < ADD_BUTTON_X + add.getWidth() && yy > ADD_BUTTON_Y
				&& yy < ADD_BUTTON_Y + add.getHeight();
	}
	
	// 判断点击开始抽奖按钮
	public static boolean clickStartButton(int xx,int yy) {
		return xx > START_BUTTON_X && xx < START_BUTTON_X + start.getWidth() && yy > START_BUTTON_Y
				&& yy < START_BUTTON_Y + start.getHeight();
	}
	
	// 判断点击重新抽奖按钮
	public static boolean clickRestartButton(int xx,int yy) {
		return xx > RESTART_BUTTON_X && xx < RESTART_BUTTON_X + again.getWidth() && yy > RESTART_BUTTON_Y
				&& yy < RESTART_BUTTON_Y + again.getHeight();
	}


	
	/** 画签名 */
	public static void paintAuthor(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font(Font.SANS_SERIF, Font.HANGING_BASELINE, 18));
		g.drawString("jsd_1607_406 xianJieHao", 20, 30);
	}

	/** 画添加名单按钮 */
	public static void paintAddInfo(Graphics g) {
		g.drawImage(Var.add, ADD_BUTTON_X, ADD_BUTTON_Y, null);
	}


	/** 画重新抽奖 */
	public static void paintRestart(Graphics g) {
		g.drawImage(Var.again, RESTART_BUTTON_X, RESTART_BUTTON_Y, null);
	}

	/** 画关闭按钮 */
	public static void paintClose(Graphics g) {
		g.drawImage(Var.close, COLSE_BUTTON_X, CLOSE_BUTTON_Y, null);
	}

	/** 画最小化按钮 */
	public static void paintMin(Graphics g) {
		g.drawImage(Var.min, MIN_BUTTON_X, MIN_BUTTON_Y, null);
	}

	/** 画开始按钮 */
	public static void paintStart(Graphics g) {	
			g.drawImage(Var.start, START_BUTTON_X, START_BUTTON_Y, null);
	}
	
	/** 画暂停按钮 */
	public static void paintStop(Graphics g) {	
			g.drawImage(Var.stop, START_BUTTON_X, START_BUTTON_Y, null);
	}
	
	/** 画添加名单按钮 */
	public static void paintSaveButton(Graphics g) {
		g.drawImage(add, SAVE_BUTTON_X,SAVE_BUTTON_Y, null);
	}
	/** 判断点击添加按钮 */
	public static boolean clickSaveButton(int xx,int yy) {
		return xx >SAVE_BUTTON_X && xx < SAVE_BUTTON_X + add.getWidth() && yy > SAVE_BUTTON_Y
						&& yy < SAVE_BUTTON_Y + add.getHeight();
	}

}
