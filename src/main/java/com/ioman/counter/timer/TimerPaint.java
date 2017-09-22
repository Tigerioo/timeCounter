package com.ioman.counter.timer;

import com.ioman.counter.entity.TimerPanel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Enumeration;

/**
 * <p>Title: com.ioman.counter</p>
 * <p/>
 * <p>
 * Description: 布局绘制类
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：6/9/17
 */
public class TimerPaint {
	
	private TimerPanel timerPanel;
	
	public TimerPaint(TimerPanel timerPanel) {
		this.timerPanel = timerPanel;
	}
	
	public JPanel paint(){
		
		JPanel panel = new JPanel();
		
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(1, 6));
		
		panel.add(titlePanel(timerPanel.getTitle()));
		
		panel.add(hourPanel(timerPanel.getZeroHour(), timerPanel.getOneHour(), timerPanel.getTwoHour(), timerPanel.getThreeHour()));
		panel.add(minutesPanel(timerPanel.getMinuteComboBox()));
		
		panel.add(usedTimeInfo(timerPanel.getTimeUsedText(), timerPanel.getTimeLeftText()));
		panel.add(leftTimeInfo(timerPanel.getTimeUsedDetail(), timerPanel.getTimeLeftDetail()));
		
		panel.add(controlButton(timerPanel.getStart(), timerPanel.getPause(), timerPanel.getStop()));
		
		BevelBorder raisedBevelBorder = (BevelBorder) BorderFactory.createRaisedBevelBorder();// 创建浮雕式边框
		panel.setBorder(raisedBevelBorder);
		
		return panel;
		
	}
	
	private JPanel titlePanel(JLabel title) {
		
		JPanel panel = new JPanel();
		
		panel.setBorder(new EmptyBorder(15, 5, 5, 5));
		panel.setLayout(new GridLayout(1, 1));
		
		panel.add(title);
		
		return panel;
	}
	
	/**
	 * 小时JPanel
	 *
	 * @return
	 */
	private JPanel hourPanel(JRadioButton zeroHour, JRadioButton oneHour, JRadioButton twoHour, JRadioButton threeHour) {
		
		JPanel hourPanel = new JPanel();
		hourPanel.setLayout(new GridLayout(4, 1));
		
		ButtonGroup group = new ButtonGroup();
		group.add(zeroHour);
		group.add(oneHour);
		group.add(twoHour);
		group.add(threeHour);
		
		hourPanel.add(zeroHour);
		hourPanel.add(oneHour);
		hourPanel.add(twoHour);
		hourPanel.add(threeHour);
		
		return hourPanel;
	}
	
	private JPanel minutesPanel(JComboBox minuteComboBox) {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		
		JLabel label = new JLabel("分钟");
		
		panel.add(minuteComboBox);
		panel.add(label);
		
		return panel;
	}
	
	private JPanel usedTimeInfo(JLabel usedTimeTitle, JLabel leftTitle) {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		
		panel.add(usedTimeTitle);
		panel.add(leftTitle);
		
		return panel;
	}
	
	private JPanel leftTimeInfo(JLabel usedTimeText, JLabel leftTime) {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		
		panel.add(usedTimeText);
		panel.add(leftTime);
		
		return panel;
	}
	
	private JPanel controlButton(JButton start, JButton pause, JButton stop) {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		
		panel.add(start);
		panel.add(pause);
		panel.add(stop);
		
		return panel;
	}
}
