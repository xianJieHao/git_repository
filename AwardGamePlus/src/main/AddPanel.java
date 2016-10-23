package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * 抽奖器添加名单面板
 * @author xianJieHao
 *
 */
public class AddPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Main main;
    private boolean isDraging = false;
    //鼠标按压时坐标
    int x,y;
    
    JTextArea jta ;
	JTextField name; 
	JTextField tel ;
	
	public AddPanel(Main mainGameFrame){
		this.main = mainGameFrame;
		this.setSize(600,500);
		this.setLayout(null);
		this.setVisible(true);
		MouseAdapter l = addMouseEvent();
		this.addMouseListener(l);
		this.addMouseMotionListener(l);
		
		name = new JTextField();
		tel =  new JTextField();
		
		//姓名，电话
		name.setBounds(71, 172, 140, 70);
		name.setSize(140,70);
		name.setBackground(new Color(237,203,168));
		name.setFont(new Font(Font.SANS_SERIF, Font.HANGING_BASELINE, 18));
		this.add(name);
		tel.setBounds(257, 172, 280, 70);
		tel.setSize(285, 70);
		tel.setBackground(new Color(237,203,168));
		tel.setFont(new Font(Font.SANS_SERIF, Font.HANGING_BASELINE, 20));
		this.add(tel);

		// 初始化text
		jta = new JTextArea(5,10);
		jta.setEditable(false);
		jta.setBackground(new Color(178,87,64));
		jta.setFont(new Font(Font.SANS_SERIF, Font.HANGING_BASELINE, 15));
		jta.setMargin(new Insets(10, 10, 10, 10));
		JScrollPane jsc = new JScrollPane(jta);
		jsc.setBounds(587,100,210,300);
		jsc.setVisible(true);
		jsc.setBorder(null);
		this.add(jsc);
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
				 Main.list = main.loadList();
				 Main.card.show(main.getContentPane(), "lottery");
			}else if(Var.clickCloseButton(xx, yy)){
				System.exit(0);
			}else if (Var.clickSaveButton(xx,yy)){
				//读取name,tel
				String oneName = name.getText();
				String oneTel=tel.getText();
				//System.out.println(oneName+":"+oneTel);
				
				if(!oneName.equals("")&&!oneTel.equals("")){
					int i = setMess(oneName,oneTel);
					if(i>0){ //添加成功
						//抽奖面板重新读取文件数据
						name.setText(" ");
						tel.setText(" ");
					}
				}
				
			}
		}

	//绘制背景
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(Var.add_back, 0, 0, null); 
		Var.paintClose(g);
		Var.paintMin(g);
		Var.paintRestart(g);
		Var.paintSaveButton(g);
		//读取名单
		getMess();
	}
	
	// 定义一个方法用于取出数据文件中的数据
		public void getMess() {
			jta.setText(""); // 添空jta
			try {
				FileReader fr = new FileReader(Main.file); // FileReader 用于读取字符流
				BufferedReader bufferfr = new BufferedReader(fr);
				String s = null;
				int i = 0;
				while ((s = bufferfr.readLine()) != null) {
					if(s.equals("")){
						continue;
					}
					i++;
					if (i == 1 && jta.getText().length() != 0)
						jta.insert(s, 0);
					else
						jta.append(s + "\n");
				}
				bufferfr.close();
				fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 定义一个方法用于向数据文件中添加数据
		public int setMess(String name, String tel) {
			int i = 0;
			try {
				FileWriter fw = new FileWriter(Main.file, true); // FileWriter
					                     // 用于写入字符流，追加到原有内容之后
				
				// BufferedReader从字符输入流中读取文本
				BufferedWriter bufferfw = new BufferedWriter(fw);
				bufferfw.newLine();
				// 通过循环读出数组里的信息并写入文件中
				bufferfw.write(name + "," + tel);
				// 开启新的一行
				bufferfw.newLine();
				bufferfw.close();
				fw.close();
				i = 1;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			getMess();
			return i; // 返回是否添加成功
		}
	
}
