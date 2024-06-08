package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class T06FileStreamTest {
	public static void main(String[] args) throws IOException {
		
		// 파일에 출력하기
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream("d:/D_Other/out.txt");
			
			for(char ch = 'a'; ch<='z'; ch++) {
				fos.write(ch);
			}
			
			System.out.println("파일에 쓰기 작업 완료...");
			
			
		}catch(IOException ex) {
			
			ex.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		FileInputStream fis = new FileInputStream("d:/D_Other/out.txt");
		
		int data = 0;
		
		while((data = fis.read()) != -1) {
			System.out.print((char)data);
		}
		
		System.out.println();
		System.out.println("파일 일기 작업 완료.....");
		
		
	}
}
