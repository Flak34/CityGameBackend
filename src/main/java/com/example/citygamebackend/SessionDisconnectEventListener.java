package com.example.citygamebackend;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class SessionDisconnectEventListener implements ApplicationListener<SessionDisconnectEvent> {
    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        //здесь нужно добавить логику на случай, если пользователь отвалился
        //1. Если пользователь не участвовал в игре, то просто удаляем его
        //2. Если участвовал, то:
        //   1)удаляем игру, с которй он связан
        //   2)удаляем его из списка активных пользователей
        //   3)уведомляем воторого пользователя
    }
}