package Others;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class CopyPaste {
	
	public static void copyFile() 
			 throws IOException{
		
		String Spath = "E:\\Automation\\Sorce\\READ.xlsx";
		String Dpath = "E:\\Automation\\copied\\READ2.xlsx";
		
		File from = new File(Spath);
		File to = new File(Dpath) ;
		
			     FileUtils.copyFile(from, to);
			    }

}
