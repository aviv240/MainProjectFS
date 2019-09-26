//package com.aviv.MainProjectFS.thread;
//
//import java.util.Calendar;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.aviv.MainProjectFS.web.Session;
//
//@Component
////@scope("singleton")
//public class SessionThread implements Runnable{
//
//	private static boolean quit = false;
//	
//	@Autowired
//	private Session session;
//	
//	@Autowired
//	private Map<String, Session> tokensMap;
//	
//	public SessionThread() {
//	
//	
//		
//	}
//
//	@Override
//	public void run() {
//		
//		long timeStamp = 15 * 60 * 1000;
//		
//		while(!quit) {
//			
//			Calendar cal = Calendar.getInstance();
//			
//			for (Session session : tokensMap) {
//				
//			}
//			
//				if(cal.getTimeInMillis() + timeStamp > session.getLastAccess()) {
//					
//					tokensMap.remove(tokensMap.get)
//					
//				}
//				
//			}
//		}
//	
//	}
//	
//	
//
//}
