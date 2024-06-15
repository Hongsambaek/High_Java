package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {
   // 파일 서버는 접속한 클라이언트가 요청한 파일이 있는지 확인 후 존재하면 해당 파일을 클라이언트에게 전송
   public void serverStart() {
      ServerSocket serverSocket = null;
      try {
         serverSocket = new ServerSocket(7777);
         System.out.println("파일서버 준비 완료 ....");

         while (true) {
            Socket socket = serverSocket.accept();

            // 파일 전송 Thread 구동
            FileSender sender = new FileSender(socket);
            sender.start();
         }
      } catch (java.io.IOException ex) {
         ex.printStackTrace();
      }
   }

   class FileSender extends Thread {
      private Socket socket;
      private FileInputStream fis;
      private DataInputStream dis;
      private DataOutputStream dos;

      public FileSender(Socket socket) {
         this.socket = socket;

         try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
         } catch (IOException e) {
            e.printStackTrace();
         }
      }

      @Override
      public void run() {
         BufferedInputStream bis = null;
         BufferedOutputStream bos = null;
         try {
            String downDir = "d:/D_Other/";

            File file = new File(downDir + dis.readUTF());

            System.out.println("요청파일 존재여부 체크중...");

            if (!file.exists()) {
               System.out.println("오청 파일 (" + file.getName() + ") 존재하지 않습니다.");
               dos.writeUTF("오청 파일 (" + file.getName() + ") 존재하지 않습니다.");
            } else {
               // 요청파일 존재함
               dos.writeUTF("OK");

               System.out.println("요청파일(" + file.getName() + ") 전송시작...");

               fis = new FileInputStream(file);

               bis = new BufferedInputStream(fis);
               bos = new BufferedOutputStream(socket.getOutputStream());

               int data = 0;
               while ((data = bis.read()) != -1) {
                  bos.write(data);
               }
               System.out.println("요청파일(" + file.getName() + ") 전송 롼료...");
            }
         } catch (IOException ex) {
            ex.printStackTrace();
         } finally {
            try {
               bis.close();
               bos.close();
               dis.close();
               dos.close();
               socket.close();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }

      }
   }
}