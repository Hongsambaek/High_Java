package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;

public class T05FileStreamTest {
	public static void main(String[] args) {
		// 한글은 3byte String타입에서 char로 나타내야함. 안그러면 깨짐
		
		
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("d:/D_Other/test2.txt");
					
			int data = 0;
			
			while((data = fis.read()) != -1) {
				System.out.print((char)data);
			}
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
}
