package HomeWork;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HomeWork06 {
	public static void main(String[] args) throws IOException {
		
		FileInputStream fis = new FileInputStream("d:/D_Other/Tulips.jpg");
		
		FileOutputStream fos = new FileOutputStream("d:/D_Other/Copy_Tulips.jpg");
		
		BufferedInputStream bis = new BufferedInputStream(fis, 5);
		
		BufferedOutputStream bos = new BufferedOutputStream(fos, 5);
		
		int data = 0;
		
		while((data = fis.read()) != -1) {
			System.out.println();
			bos.write(data);
		}
		
		System.out.println();
		System.out.println("사진 복사 완료...");
		
		bos.flush();
		fis.close();
		bos.close();
	}
}
