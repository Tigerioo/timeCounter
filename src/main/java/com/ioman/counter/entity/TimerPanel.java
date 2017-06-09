package com.ioman.counter.entity;

import javax.swing.*;

/**
 * <p>Title: com.ioman.counter.entity</p>
 * <p/>
 * <p>
 * Description: 单个timer实体
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：6/9/17
 */
public class TimerPanel {
	
	private JLabel title;//闹钟标题
	private ButtonGroup hourGroup;
	private JComboBox minuteComboBox;//选择分钟
	private JLabel timeUsedText;//使用时间文本
	private JLabel timeLeftText;//剩余时间文本
	private JLabel timeUsedDetail;//使用时间内容
	private JLabel timeLeftDetail;//剩余时间内容
	private JButton start;//开始按钮
	private JButton pause;//暂停按钮
	private JButton stop;//停止按钮
	
	public JLabel getTitle() {
		return title;
	}
	
	public void setTitle(JLabel title) {
		this.title = title;
	}
	
	public ButtonGroup getHourGroup() {
		return hourGroup;
	}
	
	public void setHourGroup(ButtonGroup hourGroup) {
		this.hourGroup = hourGroup;
	}
	
	public JComboBox getMinuteComboBox() {
		return minuteComboBox;
	}
	
	public void setMinuteComboBox(JComboBox minuteComboBox) {
		this.minuteComboBox = minuteComboBox;
	}
	
	public JLabel getTimeUsedText() {
		return timeUsedText;
	}
	
	public void setTimeUsedText(JLabel timeUsedText) {
		this.timeUsedText = timeUsedText;
	}
	
	public JLabel getTimeLeftText() {
		return timeLeftText;
	}
	
	public void setTimeLeftText(JLabel timeLeftText) {
		this.timeLeftText = timeLeftText;
	}
	
	public JLabel getTimeLeftDetail() {
		return timeLeftDetail;
	}
	
	public void setTimeLeftDetail(JLabel timeLeftDetail) {
		this.timeLeftDetail = timeLeftDetail;
	}
	
	public JLabel getTimeUsedDetail() {
		return timeUsedDetail;
	}
	
	public void setTimeUsedDetail(JLabel timeUsedDetail) {
		this.timeUsedDetail = timeUsedDetail;
	}
	
	public JButton getStart() {
		return start;
	}
	
	public void setStart(JButton start) {
		this.start = start;
	}
	
	public JButton getPause() {
		return pause;
	}
	
	public void setPause(JButton pause) {
		this.pause = pause;
	}
	
	public JButton getStop() {
		return stop;
	}
	
	public void setStop(JButton stop) {
		this.stop = stop;
	}
}
