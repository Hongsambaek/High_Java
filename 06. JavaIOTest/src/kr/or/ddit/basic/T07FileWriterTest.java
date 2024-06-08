package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class T07FileWriterTest {
	public static void main(String[] args) throws IOException {
		
		// 사용자가 입력한 내용을 그대로 파일에 저장하기
		
		// 콘솔(표준 입출력장치)과  연결된 입력용 문자 스트림 객체 생성하기
		// InputStreamReader → 바이트 기반스트림을 문자기반 스트림으로 변환해 주기 위한 보조 스트림
		InputStreamReader isr = new InputStreamReader(System.in);
		
		FileWriter fw = new FileWriter("d:/D_Other/testChar.txt");
		
		int data = 0;
		
		System.out.println("아무거나 입력하시오.");
		
		// 콘솔에서 입력할 때 입력 끝(EOF)을 나타내기 위해서는 Ctrl+Z 키를 누르면 된다.
		while((data = isr.read()) != -1) {
			fw.write(data);
		}
		System.out.println("출력 작업 끝...");
		
		isr.close();
		fw.close();
	}
}
