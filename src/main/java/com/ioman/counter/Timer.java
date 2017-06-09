package com.ioman.counter;

import com.ioman.counter.entity.TimerPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * <p>Title: com.ioman.counter</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：6/9/17
 */
public class Timer implements Runnable, ActionListener{
	
	private TimerPanel timerPanel;
	
	public Timer(TimerPanel timerPanel) {
		this.timerPanel = timerPanel;
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
		
		addToListener();
		
		
	}
	
	private void addToListener(){
		timerPanel.getStart().addActionListener(this);
		timerPanel.getPause().addActionListener(this);
		timerPanel.getStop().addActionListener(this);
		
		timerPanel.getMinuteComboBox().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
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
			timerPanel.getTimeUsedText().setText("I'm changed!!!");
		}
	}
}
