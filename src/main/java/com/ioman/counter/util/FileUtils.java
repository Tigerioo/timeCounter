package com.ioman.counter.util;

import java.io.*;

/**
 * <p>Title: com.ioman.counter.util</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/7/17
 */
public class FileUtils {

	private static final String PATH = "./data";
	
	private static void checkDir(){
		File path = new File(PATH);
		if(!path.isDirectory()){
			path.mkdir();
		}
	}
	
	public static void write(String title, long usedSec, long leftSec){
		
		checkDir();
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		try {
			
			File file = new File(PATH + File.separator + title);
			if(!file.exists()){
				file.createNewFile();
			}
			
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(usedSec + "#" + leftSec);
			
			System.out.println("Done");
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (bw != null)
					bw.close();
				
				if (fw != null)
					fw.close();
				
			} catch (IOException ex) {
				
				ex.printStackTrace();
				
			}
			
		}
	}

	public static String read(String title) {
		
		checkDir();
		
		BufferedReader br = null;
		FileReader fr = null;
		
		try {
			
			File file = new File(PATH + File.separator + title);
			if(!file.exists()){
				file.createNewFile();
			}
			
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			String secondsInfo;
			
			if ((secondsInfo = br.readLine()) != null) {
				return secondsInfo;
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (br != null)
					br.close();
				
				if (fr != null)
					fr.close();
				
			} catch (IOException ex) {
				
				ex.printStackTrace();
				
			}
			
		}
		return "0#0";
	}
	
	public static void main(String[] args) throws IOException {
		FileUtils.write("#2", 100, 100);
		System.out.println(FileUtils.read("#2"));
	}
}
