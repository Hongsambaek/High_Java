package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 문자기반의 Buffered 스트림 예제
 * @author PC-27
 *
 */
public class T12BufferedIOTest {
	public static void main(String[] args) throws IOException {
		
		
		FileReader fr = new FileReader("./src/kr/or/ddit/basic/T11BufferedIOTest.java");
		
		BufferedReader br = new BufferedReader(fr);
		
		String readStr = "";
		int cnt = 1;
		while((readStr = br.readLine()) != null) {
			System.out.printf("%4d : %s\n", cnt++, readStr);
		}
		
		fr.close();
	}
}
