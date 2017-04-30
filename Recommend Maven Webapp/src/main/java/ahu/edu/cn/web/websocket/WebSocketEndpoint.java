package ahu.edu.cn.web.websocket;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketEndpoint extends TextWebSocketHandler {
	// 保存在线用户登陆信息
		ConcurrentMap<Integer, WebSocketSession> onlinelist = new ConcurrentHashMap<Integer, WebSocketSession>();
		AtomicInteger onlinenum = new AtomicInteger(0);
	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message) throws Exception {
	
		System.out.println(message.getPayload());
		// 解析json字符串
		try {
			JSONObject jsonObj = new JSONObject(message.getPayload());
			
			String clientMessage = jsonObj.getString("message");
			String kind=jsonObj.getString("kind");
			int from = jsonObj.getInt("from");
			int to = jsonObj.getInt("to");
			
			if(clientMessage!=null){
				JSONObject jsonfrom = new JSONObject();
				jsonfrom.put("message",clientMessage);
				jsonfrom.put("from", from);
				jsonfrom.put("kind", kind);
				
				JSONObject jsonto = new JSONObject();
				jsonto.put("message",clientMessage);
				jsonto.put("from", from);
				jsonto.put("kind", kind);
				
				WebSocketSession oppositeSession =  onlinelist.get(to);
				if(oppositeSession != null){
					oppositeSession.sendMessage(new TextMessage(jsonto.toString()));
				}
//				System.out.println(jsonBack.toString());
//				
				session.sendMessage(new TextMessage(jsonfrom.toString()));
			}
		} catch (JSONException e) {

		}

	}

	

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		Object id= session.getAttributes().get("userId");

		onlinelist.put(Integer.parseInt(String.valueOf(id)), session);
		super.afterConnectionEstablished(session);
	}

}
