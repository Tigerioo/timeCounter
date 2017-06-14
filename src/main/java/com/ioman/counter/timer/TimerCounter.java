package com.ioman.counter.timer;

import com.ioman.counter.entity.TimerPanel;
import com.ioman.counter.util.AudioPlayer;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * <p>Title: com.ioman.counter</p>
 * <p/>
 * <p>
 * Description: 计时计算线程
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：6/9/17
 */
public class TimerCounter implements Runnable, ActionListener{
	
	private TimerPanel timerPanel;
	private long leftSec;//剩余秒数
	private long usedSec;//已经走的秒数
	private boolean isRunning;//是否在计时
	private boolean isAlarm;
	private JFrame frame;
	private String title;
	
	public TimerCounter(TimerPanel timerPanel, JFrame frame) {
		this.timerPanel = timerPanel;
		this.frame = frame;
		this.title = timerPanel.getTitle().getText();
	}
	
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
		
		reset();
		
		addToListener();
	}
	
	private void reset(){
		
		setButtonStatus();
		
		//设置总秒数
		initTotalSec();
		
		//重置时间提示文本
		resetTimeText();
	}
	
	private void resetTimeText(){
		
		if(isRunning) return;
		
		timerPanel.getTimeLeftDetail().setText(formatSecond(leftSec));
		timerPanel.getTimeUsedDetail().setText(formatSecond(usedSec));
	}
	
	private void initTotalSec(){
		
		if(isRunning) return;
		
		usedSec = 0;
		leftSec = 0;//初始化
		
		//统计小时
		if(timerPanel.getTwoHour().isSelected()){
			leftSec += 2 * 60 * 60;
		}else {
			leftSec += 3 * 60 * 60;
		}
		
		int minute = Integer.parseInt(timerPanel.getMinuteComboBox().getSelectedItem().toString());
		
		if(minute > 0) {
			leftSec += minute * 60;
		}
	}
	
	private void addToListener(){
		timerPanel.getStart().addActionListener(this);
		timerPanel.getPause().addActionListener(this);
		timerPanel.getStop().addActionListener(this);
		
		timerPanel.getTwoHour().addActionListener(this);
		timerPanel.getThreeHour().addActionListener(this);
		
		timerPanel.getMinuteComboBox().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					
					reset();
				}
			}
		});
	}
	
	/**
	 * Invoked when an action occurs.
	 *
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == timerPanel.getStart()){
			
			if(!isRunning && leftSec > 0){
				
				isRunning = true;//开始计时
				new Thread(new TickTickRunner()).start();
				setButtonStatus();
			}
			
		}else if(e.getSource() == timerPanel.getPause()) {
			
			if(isRunning){
				isRunning = false;
				setButtonStatus();
				timerPanel.getStop().setEnabled(true);
			}
			
			
		}else if(e.getSource() == timerPanel.getStop()){
			
			int status = JOptionPane.showConfirmDialog(frame, "确定要结束?", title, JOptionPane.YES_NO_OPTION);
			
			if(status == 0) {
				
				isRunning = false;
				isAlarm = false;
				leftSec = 0;
				usedSec = 0;
				
				reset();
			}
			
		}else if(e.getSource() == timerPanel.getTwoHour()) {
			
			reset();
		}else if(e.getSource() == timerPanel.getThreeHour()){
			
			reset();
		}
	}
	
	public void setButtonStatus(){
		
		if(isRunning){//如果正在计时中
			
			timerPanel.getTimeUsedDetail().setFont(new Font("Dialog", 1, 21));
			timerPanel.getTimeLeftDetail().setFont(new Font("Dialog", 1, 21));
			
			timerPanel.getStart().setEnabled(false);
			timerPanel.getPause().setEnabled(true);
			timerPanel.getStop().setEnabled(true);
		}else {
			
			timerPanel.getTimeUsedDetail().setFont(new Font("Dialog", 0, 14));
			timerPanel.getTimeLeftDetail().setFont(new Font("Dialog", 0, 14));
			
			timerPanel.getStart().setEnabled(true);
			timerPanel.getPause().setEnabled(false);
			timerPanel.getStop().setEnabled(false);
		}
	}
	
	/**
	 * 将秒转换成时间格式 "0 时 00 分 00 秒"
	 * @param seconds
	 * @return
	 */
	private String formatSecond(long seconds){
		
		long hour = seconds / 60 / 60;
		long min = (seconds - (hour * 60 * 60)) / 60;
		long sec = seconds - (hour * 60 * 60) - (min * 60);
		
		String minStr = "" + min;
		if(min < 10){
			minStr = "0" + min;
		}
		
		String secStr = "" + sec;
		if(sec < 10){
			secStr = "0" + sec;
		}
	
		return hour + " 时 " + minStr + " 分 " + secStr + " 秒 ";
	}
	
	
	class TickTickRunner implements Runnable {
		
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
			
			System.out.println("TickTick alive ... ");
			
			//时间还在，并且需要跑的时候
			while(isRunning && leftSec > 0){
				
				leftSec--;
				usedSec++;
				
				String leftTimeText = formatSecond(leftSec);
				String usedTimeText = formatSecond(usedSec);
				timerPanel.getTimeLeftDetail().setText(leftTimeText);
				timerPanel.getTimeUsedDetail().setText(usedTimeText);
				
				if (leftSec == 0){
					//播放声音
					isAlarm = true;
					new Thread(new AlarmAudio()).start();
					
//					JOptionPane.showMessageDialog(frame, timerPanel.getTitle().getText() + " 时间到！！！");
					int status = JOptionPane.showConfirmDialog(frame, timerPanel.getTitle().getText() + " 时间到！！！", title, JOptionPane.CLOSED_OPTION);
					if(status == 0){
						isAlarm = false;
					}
					isRunning = false;
					setButtonStatus();
					initTotalSec();
					resetTimeText();
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
				}
			}
			
			System.out.println("TickTick die ...");
		}
	}
	
	class AlarmAudio implements Runnable {
		
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
			
			System.out.println("AlarmPlayer alive ... ");
			
			//播放音频文件
			try{
				AudioInputStream ais = AudioSystem.getAudioInputStream(new File("alarm.wav"));
				AudioFormat aif = ais.getFormat();
				SourceDataLine sdl = null;
				DataLine.Info info = new DataLine.Info(SourceDataLine.class,aif);
				sdl = (SourceDataLine)AudioSystem.getLine(info);
				sdl.open(aif);
				sdl.start();
				
				//play
				int nByte = 0;
				byte[] buffer = new byte[128];
				while(isAlarm && nByte != -1){
					nByte = ais.read(buffer,0,128);
					if(nByte >= 0){
						int oByte = sdl.write(buffer, 0, nByte);
						//System.out.println(oByte);
					}
				}
				sdl.stop();
			}catch(UnsupportedAudioFileException e){
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动产生 catch 区块
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO 自动产生 catch 区块
				e.printStackTrace();
			} finally {
				System.out.println("AlarmPlayer die ...");
			}
		}
	}
}
