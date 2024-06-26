package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiChatServer {
	
	// 대화명 및 클라이언트의 Socket객체를 저장하기 위한 Map객체변수 선언하기
	private Map<String, Socket> clients;
	
	public MultiChatServer() {
		clients = Collections.synchronizedMap(new HashMap<String, Socket>());
	}
	
	public void serverStart() {
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(7777);
			
			System.out.println("멀티챗 서버가 시작되었습니다...");
			
			while(true) {
				// 클라이언트의 접속요청을 기다린다.
				socket = serverSocket.accept();
				
				System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort()
								+ "] 에서 접속하였습니다.");
				
				// 사용자가 보내준 메시지를 처리하기 위한 스레드 생성 및 실행
				ServerReceiver handler = new ServerReceiver(socket);
				handler.start();
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 대화명 즉, Map에 저장된 모든 사용자에게 안내 메시지를 전송하기 위한 메소드
	 * @param msg
	 */
	public void sendMessage(String msg) {
		
		Iterator<String> it = clients.keySet().iterator();
		while(it.hasNext()) {
			
			try {
				String name = it.next();
				
				//대화명에 해당하는 Socket 객체 가져와 메시지 보내기
				Socket socket = clients.get(name);
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				
				dos.writeUTF(msg);
				
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}	
		}	
	}
	
	
	
	
	/**
	 * 대화명 즉 , Map에 저장된 모든 사용자에게 채팅 메시지를 전송하기 위한 메소드
	 * @param msg 채팅 메시지
	 * @param from 채팅 보내는 사람 대화명
	 */
	public void sendMessage(String msg, String from) {
		sendMessage("[" + from + "]" + msg);
	}
	
	
	
	
	
	
	// 서버에서 클라이언트로부터 수신한 메시지를 처리하기 위한 클래스
	// Inner클래스로 정의함. (Inner클래스는 부모(Outer)클래스의 멤버들을 직접 접근할 수 있다.)
	class ServerReceiver extends Thread {
		
		private Socket socket;
		private DataInputStream dis;
		private String name;
		
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			
			try {
				// 서버에는 클라이언트가 보내는 최초의 메시지 즉, 대화명을 수신해야 한다.
				name = dis.readUTF();
				
				// 대화명을 받아서 다른 모든 클라이언트에게 대화방 참여 메시지를 보낸다.
				sendMessage("#" + name + "님이 입장했습니다.");
				
				// 대화명과 소켓객체를 Map에 저장한다.
				clients.put(name, socket);
				
				System.out.println("현재 서버 접속자 수는 " + clients.size() + "명 입니다");
				
				///////////////////////////////////////////////////////////////////
				
				
				// 이 후의 메시지 처리는 채팅 메시지 이다.
				while(dis!= null) {
					sendMessage(dis.readUTF(), name);
				}
				
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				// 이 finally 영역이 실행된다는 것은 클라이언트의 접속이 종료되었다는 의미이다.
				
				sendMessage("#" + name + "님이 퇴장했습니다.");
				
				// Map에서 해당 사용자 정보 제거하기
				clients.remove(name);
				
				System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort()
				+ "] 에서 접속종료 하였습니다.");
				
				System.out.println("현재 서버 접속자 수는 " + clients.size() + "명 입니다.");
			}			
		}	
	}
}
