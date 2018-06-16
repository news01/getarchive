package com.nsfy.util;

import java.io.File;

public class FileUtil {
	
	public void DelFile(String path){
		File file = new File(path);
		while(file.exists()){
			file.delete();
		}
	}
	
}
