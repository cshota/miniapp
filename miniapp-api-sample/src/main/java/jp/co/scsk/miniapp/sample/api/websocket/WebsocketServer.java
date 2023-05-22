package jp.co.scsk.miniapp.sample.api.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket")
@Component
@Slf4j
public class WebsocketServer {

    private static final Map<String, Session> clients = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        log.info("connect open: {}", session.getId());
        clients.put(session.getId(), session);
    }

    @OnClose
    public void onClose(Session session) {
        log.info("connect close:{}", session.getId());
        clients.remove(session.getId());
    }

    @OnError
    public void onError(Throwable throwable) {
        log.error("websocket error", throwable);
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("get message: {}", message);
        this.sendAll(message);
    }

    private void sendAll(String message) {
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            sessionEntry.getValue().getAsyncRemote().sendText("server get message:" + message);
        }
    }
}