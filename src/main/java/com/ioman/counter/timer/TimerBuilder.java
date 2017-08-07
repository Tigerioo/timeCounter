package com.ioman.counter.timer;

import com.ioman.counter.entity.TimerPanel;

import javax.swing.*;
import java.awt.*;

/**
 * <p>Title: com.ioman.counter</p>
 * <p/>
 * <p>
 * Description: 组件建造者
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：6/9/17
 */
public class TimerBuilder {
	
	private JLabel title;//闹钟标题
	private JRadioButton twoHour;//2小时选项
	private JRadioButton threeHour;//3小时选项
	private JComboBox minuteComboBox;//选择分钟
	private JLabel timeUsedText;//使用时间文本
	private JLabel timeLeftText;//剩余时间文本
	private JLabel timeUsedDetail;//使用时间内容
	private JLabel timeLeftDetail;//剩余时间内容
	private JButton start;//开始按钮
	private JButton pause;//暂停按钮
	private JButton stop;//停止按钮
	
	public static TimerBuilder builder(){
		return new TimerBuilder();
	}
	
	public TimerBuilder title(String title){
		
		JLabel label = new JLabel(title);
		label.setFont(new Font("Dialog", 1, 20));
		label.setForeground(Color.RED);
		
		this.title = label;
		
		return this;
	}
	
	public TimerBuilder hour(){
		
		JRadioButton twoHour = new JRadioButton("2小时", true);
		JRadioButton threeHour = new JRadioButton("3小时");
		
		this.twoHour = twoHour;
		this.threeHour = threeHour;
		
		return this;
	}
	
	public TimerBuilder minute(){
		
		final JComboBox minuteComboBox = new JComboBox(new String[]{"0", "1", "2", "3", "4", "5", "6", "7",
				"8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
				"22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37",
				"38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51",
				"52", "53", "54", "55", "56", "57", "58", "59"});
		
		minuteComboBox.setPreferredSize(new Dimension(150, 26));
		
		this.minuteComboBox = minuteComboBox;
		
		return this;
	}
	
	public TimerBuilder timeText(){
		JLabel timeUsedText = new JLabel("开始时间: ");
		JLabel timeLeftText = new JLabel("剩余时间: ");
		this.timeUsedText = timeUsedText;
		this.timeLeftText = timeLeftText;
		
		return this;
	}
	
	public TimerBuilder timeDetail(){
		
		JLabel timeUsedDetail = new JLabel("0 小时 00 分 00 秒");
		JLabel timeLeftDetail = new JLabel("0 小时 00 分 00 秒");
		timeLeftDetail.setForeground(Color.RED);
		this.timeUsedDetail = timeUsedDetail;
		this.timeLeftDetail = timeLeftDetail;
		
		return this;
	}
	
	public TimerBuilder controlButton(){
		
		JButton start = new JButton("开始计时");
		JButton pause = new JButton("暂停计时");
		JButton stop = new JButton("停止计时");
		stop.setForeground(Color.BLUE);
		
		this.start = start;
		this.pause = pause;
		this.stop = stop;
		
		return this;
	}
	
	public TimerPanel build(){
		
		TimerPanel panel = new TimerPanel();
		panel.setTitle(title);
		panel.setTwoHour(twoHour);
		panel.setThreeHour(threeHour);
		panel.setMinuteComboBox(minuteComboBox);
		panel.setTimeUsedText(timeUsedText);
		panel.setTimeLeftText(timeLeftText);
		panel.setTimeUsedDetail(timeUsedDetail);
		panel.setTimeLeftDetail(timeLeftDetail);
		panel.setStart(start);
		panel.setPause(pause);
		panel.setStop(stop);
		
		return panel;
	}
}
