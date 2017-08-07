package com.ioman.counter;

import com.ioman.counter.entity.TimerPanel;
import com.ioman.counter.timer.*;
import com.ioman.counter.util.AudioPlayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * <p>Title: com.ioman.counter.view</p>
 * <p/>
 * <p>
 * Description: 首页View
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：6/8/17
 */
public class MainView implements ActionListener, ItemListener {
	
	private JFrame frame;
	private JPanel panel;
	
	private JMenuItem audio, version;
	
	public static void main(String[] args) {
		
		MainView view = new MainView();
		view.lunchFrame();
		
	}
	
	public void lunchFrame() {
		
		frame = new JFrame();
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		frame.setMinimumSize(new Dimension(680, 200));
		frame.setSize((int) width * 3/4, (int) height * 4/5);
		frame.setTitle("慢速烘箱定时提醒器");
		Image icon = Toolkit.getDefaultToolkit().getImage("sandglass.png");
		frame.setIconImage(icon);
		frame.setDefaultCloseOperation(3);
		
		System.out.println("width = " + width + ", height = " + height);
		
		frame.setLocation((int) width * 1 / 4, (int) height * 1 / 4);
		
		addMenu();
		
		loadContent();
		
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void addMenu(){
		
		//create the menu bar
		JMenuBar menuBar = new JMenuBar();
		
		//create the menu
		JMenu operMenu = new JMenu("操作");
		menuBar.add(operMenu);
		
		//create the menu item
		audio = new JMenuItem("测试声音");
		audio.getAccessibleContext().setAccessibleDescription(
				"播放结束提醒音频");
		operMenu.add(audio);
		
		//create the about menu
		JMenu aboutMenu = new JMenu("关于");
		menuBar.add(aboutMenu);
		
		version = new JMenuItem("版本");
		aboutMenu.add(version);
		
		//add listener
		audio.addActionListener(this);
		version.addActionListener(this);
		
		frame.setJMenuBar(menuBar);
	}
	
	public void loadContent() {
		
		panel = new JPanel();
		
		int counterCount = 8;
		
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(counterCount, 1));
		
		for (int i = 1; i <= counterCount; i++) {
			
			TimerPanel timerPanel = TimerBuilder.builder()
					.title("#" + i)
					.hour()
					.minute()
					.timeText()
					.timeDetail()
					.controlButton()
					.build();
			
			TimerPaint timerPaint = new TimerPaint(timerPanel);
			
			panel.add(timerPaint.paint());
			
			TimerCounter timer = new TimerCounter(timerPanel, frame);
			new Thread(timer).start();
		}
		
		frame.add(panel);
	}
	
	
	/**
	 * Invoked when an action occurs.
	 *
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == audio){
			new Thread(new AudioPlayer()).start();
		}else if(e.getSource() == version){
			JOptionPane.showMessageDialog(frame, "版本号： 0.2.3");
		}
	
	}
	
	/**
	 * Invoked when an item has been selected or deselected by the user.
	 * The code written for this method performs the operations
	 * that need to occur when an item is selected (or deselected).
	 *
	 * @param e
	 */
	public void itemStateChanged(ItemEvent e) {
	
	}
}