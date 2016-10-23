package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
/**
 * 抽奖器程序运行面板
 * @author xianJieHao
 *
 */
public class LotteryPanel extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
    private Main main;
    private boolean isDraging = false;
    //鼠标按压时坐标
    int x,y;
    String name = "";
    String phone = "";
    int count = Var.SUM;
    
    private static final int WAIT = 1;
    private static final int START = 2;
    private static final int STOP = 3;
    int status = WAIT;
 
    private List<Person> results = new ArrayList<Person>();

	// 滚动栏Y坐标
	int offset = Var.ROLL_TOP;
    
	public LotteryPanel(Main main){
		this.main = main;
		this.setVisible(true);
		MouseAdapter l = addMouseEvent();
		this.addMouseListener(l);
		this.addMouseMotionListener(l);
	}
	
    /**  重写鼠标监听事件  */
	private MouseAdapter addMouseEvent() {
		MouseAdapter l = new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				Point p = e.getPoint();
				dealEvent(p.x,p.y);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				isDraging = true;
				x=e.getX();
				y=e.getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				isDraging = false;
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				if(isDraging){
					main.setLocation(e.getXOnScreen()-x, e.getYOnScreen()-y);
				}
			}

		};
		return l;
	}
	
	/**   处理鼠标监听事件   */
		private void dealEvent(int xx, int yy) {
			if(Var.clickMinButton(xx, yy)){
				main.setExtendedState(Frame.ICONIFIED);//最小化窗口
			}else if (Var.clickRestartButton(xx,yy)) {
				restart();
			}else if (Var.clickAddButton(xx,yy)) {
				restart();			
			    Main.card.show(main.getContentPane(), "add");
				repaint();
			}else if(Var.clickStartButton(xx, yy)){
				switch (status) {
				case START:
					status = STOP;
					break;
					
				case WAIT:
				case STOP:
					if(count <= 0){
						restart();
					}else{
						status = START;
					}
					break;
				default:
					break;
				}
			}else if(Var.clickCloseButton(xx, yy)){
				System.exit(0);
			}
		}
	

	//绘制面板
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(Var.back, 0, 0, null); 
		Var.paintRestart(g);
		Var.paintClose(g);
		Var.paintMin(g);
		Var.paintAddInfo(g);
		Var.paintAuthor(g);
		
		paintNameAndPhone(g);
		if(status == START){
			Var.paintStop(g);
		}else{
			Var.paintStart(g);
		}
		paintList(g);
		paintCount(g);
	}
	
	/** 画"抽奖名单" */
	public void paintNameAndPhone(Graphics g) {
		// 滚动栏
		g.setColor(new Color(0xFF0000));
		g.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		g.drawString(name, Var.ROLL_NAME, offset);
		g.drawString(phone, Var.ROLL_PHONE, offset);

	}

	/** 画抽奖名单栏 */
	public void paintList(Graphics g) {
		// 名单栏
		if (results.size() != 0) {
			for (int i = 0; i < results.size(); i++) {
				g.drawString(results.get(i).toString(), Var.LIST_X, Var.LIST_Y + i * Var.ROW_LEN);
			}
		}
	}


	/** 画抽奖剩余次数 */
	public void paintCount(Graphics g) {
		g.drawString(String.valueOf(count), Var.COUNT_X, Var.COUNT_Y);
	}

	private void restart() {
		offset = Var.ROLL_TOP;
		status = WAIT;
		name ="";
		phone = "";
		count = Var.SUM;
		results.clear();
	}
	
	@Override
	public void run() {
		while (true) {
			//刚进入界面 时信息是空的，需要随机显示一个信息
			if(name.equals("")||phone.equals("")){
				Person e = main.getRamdonInfo(Main.list);
				this.name = e.getName();
				this.phone = e.getPhone();
				offset = Var.ROLL_MIDDLE;
				repaint();
			}
			
			if (status == START) {
				offset = Var.ROLL_TOP;
				Person e = main.getRamdonInfo(Main.list);
				this.name = e.getName();
				this.phone = e.getPhone();
				while(offset < Var.ROLL_BOTTOM){
					 offset++;
					if(status == STOP){ //抽出一个中奖名单
						if(!results.contains(e)){
							results.add(e);
						}
						count = Var.SUM - results.size();
						if(count < 0){
							restart();
							break;
						}
						offset = Var.ROLL_MIDDLE;
						repaint();
					}
					try {
						Thread.sleep(1);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					repaint();
				}
				
			}
			if(status == WAIT){
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	

}
