package moc.lab.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class FileManager {

	private static final String FILE_NAME =  "/test.txt";
	
	private static FileInputStream is = null ;
	private static FileOutputStream os = null;
	
	private static InputStreamReader reader = null;
	private static OutputStreamWriter writer = null;
	
	
	private static BufferedReader bfReader = null;
	private static BufferedWriter bfWriter = null;
	 	 
	public static void writeScore(int score){
		try {
			os = new FileOutputStream(FILE_NAME, true);
			
			if( os != null ) {
				 writer = new OutputStreamWriter(os);
				 bfWriter = new BufferedWriter(writer);
				 bfWriter.write(String.valueOf( score )+"\r\n");
				 bfWriter.flush();
			 }
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
				writer.close( );
				bfWriter.close( );
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	 }
	 
	 public static ArrayList<String> readScores(){
		 try {
			 is = new FileInputStream(FILE_NAME);
			 
			 if( is != null ) {
				 reader = new InputStreamReader(is);
				 bfReader = new BufferedReader(reader);
				 
				 ArrayList<String> list = new ArrayList<String>();
				 String line;
				 
				 while((line = bfReader.readLine())!= null){
					 list.add(line);
				 }
				 
				 return list;
			 }
		 } catch( IOException e ) {
			 e.printStackTrace();
		 } finally {
				try {
					is.close();
					reader.close( );
					bfReader.close( );
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		 return null;
	 }
	
}
