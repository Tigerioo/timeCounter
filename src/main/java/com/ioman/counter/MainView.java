package com.ioman.counter;

import com.ioman.counter.entity.TimerPanel;
import com.ioman.counter.timer.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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
public class MainView {
	
	private JFrame frame;
	private JPanel panel;
	
	public static void main(String[] args) {
		
		MainView view = new MainView();
		view.lunchFrame();
		
	}
	
	public void lunchFrame() {
		
		frame = new JFrame();
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		frame.setMinimumSize(new Dimension(680, 200));
		frame.setSize((int) width, (int) height);
		frame.setTitle("慢速烘箱定时提醒器");
		Image icon = Toolkit.getDefaultToolkit().getImage("");
		frame.setIconImage(icon);
		frame.setDefaultCloseOperation(3);
		
		System.out.println("width = " + width + ", height = " + height);
		
		frame.setLocation((int) width * 1 / 4, (int) height * 1 / 4);
		
		loadContent();
		
		frame.setVisible(true);
	}
	
	public void loadContent() {
		
		panel = new JPanel();
		
		int counterCount = 1;
		
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
	
	
}