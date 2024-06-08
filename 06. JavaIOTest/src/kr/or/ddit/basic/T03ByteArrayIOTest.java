package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class T03ByteArrayIOTest {
	
	public static void main(String[] args) {
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		// 직접 복사하는 방법
//		outSrc = new byte[inSrc.length];
//		for(int i = 0; i<inSrc.length; i++) {
//			outSrc[i] = inSrc[i];
//		}
		
//		System.out.println("직접 복사 후 outSrc => " + Arrays.toString(outSrc));
//		
//		// arraycopy 이용한 방법
//		
//		System.arraycopy(inSrc, 0, outSrc, 0, inSrc.length);
//		
//		System.out.println("arraycopy 복사 후 outSrc => " + Arrays.toString(outSrc));
	
		// 스트림 클래스를 이용하는 방법
		
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int data = 0;
		
		while((data = bais.read()) != -1) {
			baos.write(data);
		}
		
		outSrc = baos.toByteArray();
		
		System.out.println("스트림 클래스이용하여 복사 후 outSrc → " + Arrays.toString(outSrc));
		
		
		
		
		
	}
	
	
}
