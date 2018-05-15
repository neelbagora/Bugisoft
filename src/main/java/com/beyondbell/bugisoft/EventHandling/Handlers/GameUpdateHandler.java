package com.beyondbell.bugisoft.EventHandling.Handlers;

import com.beyondbell.bugisoft.Lobby.MovePeople;
import net.dv8tion.jda.core.events.user.update.UserUpdateGameEvent;

public class GameUpdateHandler  extends EventHandler {
    final UserUpdateGameEvent event;

    public GameUpdateHandler(UserUpdateGameEvent event) {
        super();
        this.event = event;
    }
    @Override
    void handle() {
        new MovePeople(event);
    }
}
