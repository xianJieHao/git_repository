package main;

import java.awt.CardLayout;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
/**
 * 抽奖器主框架JFrame
 * 包含了两个面板组件：
 * 抽奖面板lotteryPanel
 * 添加名单面板AddPanel
 * 使用卡片布局，两者交替使用
 * @author xianJieHao
 *
 */
public  class Main extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//定义组件
	private AddPanel addInfo;
	private LotteryPanel lottery;
	static CardLayout card; 
	static List<Person> list;
	//定义文件属性
	static File file = new File("list.txt");  

	public Main() {
		this.setSize(Var.back.getWidth(), Var.back.getHeight());
		this.setUndecorated(true);
		this.isAlwaysOnTop();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		card = new CardLayout();
		this.setLayout(card);
		addInfo = new AddPanel(this);
		lottery = new LotteryPanel(this);
		this.add(lottery,"lottery");
		this.add(addInfo,"add");
		Main.list = loadList();
	}
	
	/**
	 * 读取文件
	 * @return
	 */
	public  List<Person> loadList(){
		return IOUtil.loadData(file);	
	}
   
	/**
	 * 从list中得到随机的名单
	 */
	public Person getRamdonInfo(List<Person> list){
		return list.get((int) (Math.random() * list.size()));
	}
	
	private void start() {
		Thread t = new Thread(lottery);
		t.start();
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Main g = new Main();
		g.start();
	}
	
}
