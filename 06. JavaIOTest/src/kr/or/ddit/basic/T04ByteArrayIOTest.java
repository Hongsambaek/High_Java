package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04ByteArrayIOTest {
	
	public static void main(String[] args) throws IOException {
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4];
		
		
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int readBytes = 0;
		
		while((readBytes = bais.read(temp)) != -1) {
			baos.write(temp, 0, readBytes);
			
			System.out.println("temp => " + Arrays.toString(temp));
		}
		
		outSrc = baos.toByteArray();
		
		System.out.println("스트림 클래스이용하여 복사 후 outSrc → " + Arrays.toString(outSrc));
		
		
		
		
		
	}
	
	
}
