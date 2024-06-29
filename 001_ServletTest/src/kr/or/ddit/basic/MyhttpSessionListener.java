package kr.or.ddit.basic;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyhttpSessionListener implements HttpSessionListener, HttpSessionAttributeListener{

	@Override
	public void attributeAdded(HttpSessionBindingEvent hsbe) {
		System.out.println("[MyHttpSessionListener] attributeAdded 호출됨 : " 
						   + hsbe.getName() + "=" + hsbe.getValue() + "추가됨.");
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent hsbe) {
		System.out.println("[MyHttpSessionListener] attributeRemoved 호출됨 : " 
						   + hsbe.getName() + "=" + hsbe.getValue() + "삭제됨.");
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent hsbe) {
		System.out.println("[MyHttpSessionListener] attributeReplaced 호출됨 : " + hsbe.getName() + "=" 
						   + hsbe.getValue() + "변경됨.");
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("[MyHttpSessionListener] sessionCreated 호출됨 : ");
		
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("[MyHttpSessionListener] sessionDestroyed 호출됨 : ");
		
	}
	
	

}
