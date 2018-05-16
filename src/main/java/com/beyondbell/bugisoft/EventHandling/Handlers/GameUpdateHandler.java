package com.beyondbell.bugisoft.EventHandling.Handlers;

import com.beyondbell.bugisoft.Bot;
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
        if(Bot.settings.getProperty("defaultTempChannel") == null) {
            event.getGuild().getDefaultChannel().sendMessage("Lobby not set");
        } else {
            new MovePeople(event);
        }
    }
}
