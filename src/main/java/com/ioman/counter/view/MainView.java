package com.ioman.counter.view;

import com.sun.media.sound.WaveFileReader;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>Title: com.ioman.counter.view</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：6/8/17
 */
public class MainView implements ActionListener, KeyListener {
	
	private JFrame frame;
	private JPanel panel;
	private JRadioButton hourBtn1, hourBtn2;
	private JButton start, pause, stop;
	private JComboBox minuteComboBox;
	private JLabel usedTimeText;
	private int count;
	private boolean isRunning;
	
	public static void main(String[] args) {
		
		MainView view = new MainView();
		view.lunchFrame();
		
	}
	
	public void lunchFrame(){
		
		frame = new JFrame();
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		frame.setMinimumSize(new Dimension(680, 200));
		frame.setSize((int)width, (int)height);
		frame.setTitle("慢速烘箱定时提醒器");
		Image icon = Toolkit.getDefaultToolkit().getImage("");
		frame.setIconImage(icon);
		frame.setDefaultCloseOperation(3);
		
		System.out.println("width = " + width + ", height = " + height);
		
		frame.setLocation((int)width * 1/4, (int)height * 1/4);
		
		loadContent();
		
		frame.setVisible(true);
	}
	
	public void loadContent(){
		
		panel = new JPanel();
		
		int counterCount = 1;
		
		panel.setBorder(new EmptyBorder(5,5,5,5));
		panel.setLayout(new GridLayout(counterCount, 1));
		
		for (int i=1; i<= counterCount; i++){
			panel.add(loadSingleCounter(i));
		}
		
		frame.add(panel);
	}
	
	public JPanel loadSingleCounter(int i){
		
		JPanel panel = new JPanel();
		
		panel.setBorder(new EmptyBorder(5,5,5,5));
		panel.setLayout(new GridLayout(1, 6));
		
		panel.add(titlePanel(i));
		
		panel.add(hourPanel());
		panel.add(minutesPanel());
		
		panel.add(usedTimeInfo());
		panel.add(leftTimeInfo());
		
		panel.add(controlButton());
		
		BevelBorder raisedBevelBorder = (BevelBorder)BorderFactory.createRaisedBevelBorder();// 创建浮雕式边框
		panel.setBorder(raisedBevelBorder);
		
		return panel;
	}
	
	private JPanel titlePanel(int i){
		
		JPanel panel = new JPanel();
		
		panel.setBorder(new EmptyBorder(15,5,5,5));
		panel.setLayout(new GridLayout(1, 1));
		
		JLabel label = new JLabel("#" + i);
		label.setFont(new Font("Dialog", 1, 20));
		label.setForeground(Color.RED);
		panel.add(label);
		
		return panel;
	}
	
	/**
	 * 小时JPanel
	 * @return
	 */
	private JPanel hourPanel(){
		
		JPanel hourPanel = new JPanel();
		
		hourPanel.setLayout(new GridLayout(2, 1));
		
		hourBtn1 = new JRadioButton("2小时", true);
		hourBtn2 = new JRadioButton("3小时");
		
		ButtonGroup hourGroup = new ButtonGroup();
		hourGroup.add(hourBtn1);
		hourGroup.add(hourBtn2);
		
		hourPanel.add(hourBtn1);
		hourPanel.add(hourBtn2);
		
		return hourPanel;
	}
	
	private JPanel minutesPanel(){
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(1, 1));
		
		minuteComboBox = new JComboBox(new String[]{"1", "2", "3", "4", "5", "6", "7",
				"8", "9", "10", "11", "12", "13", "14","15", "16", "17", "18", "19", "20", "21",
				"22", "23", "24", "25", "26", "27", "28","29", "30","31", "32", "33", "34", "35", "36", "37",
				"38", "39", "40", "41", "42", "43", "44","45", "46", "47", "48", "49", "50", "51",
				"52", "53", "54", "55", "56", "57", "58","59"});
		
		minuteComboBox.setPreferredSize(new Dimension(150, 26));
		
		minuteComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					System.out.println("选择的值：" + minuteComboBox.getSelectedItem());
				}
			}
		});
		
		JLabel label = new JLabel("分钟");
		
		panel.add(minuteComboBox);
		panel.add(label);
		
		return panel;
	}
	
	private JPanel usedTimeInfo(){
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(2, 1));
		
		JLabel usedTimeTitle = new JLabel("使用时间：");
		JLabel leftTitle = new JLabel("剩余时间：");
		
		panel.add(usedTimeTitle);
		panel.add(leftTitle);
		
		return panel;
	}
	
	private JPanel leftTimeInfo(){
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(2, 1));
		
		usedTimeText = new JLabel("0 小时 00 分 01 秒");
		JLabel leftTime = new JLabel("0 小时 00 分 01 秒");
		
		panel.add(usedTimeText);
		panel.add(leftTime);
		
		return panel;
	}
	
	private JPanel controlButton(){
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(3, 1));
		
		start = new JButton("开始计时");
		pause = new JButton("暂停计时");
		stop = new JButton("停止计时");
		
		start.addActionListener(this);
		pause.addActionListener(this);
		stop.addActionListener(this);
		
		panel.add(start);
		panel.add(pause);
		panel.add(stop);
		
		return panel;
	}
	
	/**
	 * Invoked when an action occurs.
	 *
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == start){
			
			new Thread(new TimeCounter()).start();
		}
	}
	
	class TimeCounter implements Runnable{
		
		/**
		 * When an object implementing interface <code>Runnable</code> is used
		 * to create a thread, starting the thread causes the object's
		 * <code>run</code> method to be called in that separately executing
		 * thread.
		 * <p>
		 * The general contract of the method <code>run</code> is that it may
		 * take any action whatsoever.
		 *
		 * @see Thread#run()
		 */
		public void run() {
			
			if(isRunning) return ;
			
			isRunning = true;
			for (int i = 1; i<=6; i++){
				usedTimeText.setText("0 小时 00 分 " + i + " 秒");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
				}
			}
			
			isRunning = false;
		}
	}
	
	/**
	 * Invoked when a key has been typed.
	 * See the class description for {@link KeyEvent} for a definition of
	 * a key typed event.
	 *
	 * @param e
	 */
	public void keyTyped(KeyEvent e) {
		
	}
	
	/**
	 * Invoked when a key has been pressed.
	 * See the class description for {@link KeyEvent} for a definition of
	 * a key pressed event.
	 *
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		
	}
	
	/**
	 * Invoked when a key has been released.
	 * See the class description for {@link KeyEvent} for a definition of
	 * a key released event.
	 *
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		
	}
}
