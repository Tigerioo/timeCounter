package com.ioman.counter.util;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MP3Player {
	
	private String fileName;
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	//    程序退出时执行的代码
	public void doShutDownWork() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					Toolkit.getDefaultToolkit().beep();
//					play();
					play();
				} catch (Exception ex) {
				}
			}
		});
	}
	
	//播放音频文件
	public void play(){
		try{
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));
			AudioFormat aif = ais.getFormat();
			SourceDataLine sdl = null;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class,aif);
			sdl = (SourceDataLine)AudioSystem.getLine(info);
			sdl.open(aif);
			sdl.start();
			
			//play
			int nByte = 0;
			byte[] buffer = new byte[128];
			while(nByte != -1){
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
		}
	}
	
}