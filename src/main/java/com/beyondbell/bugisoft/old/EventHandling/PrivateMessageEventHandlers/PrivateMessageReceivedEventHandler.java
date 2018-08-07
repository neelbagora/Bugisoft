package com.beyondbell.bugisoft.old.EventHandling.PrivateMessageEventHandlers;

import com.beyondbell.bugisoft.old.EventHandling.EventHandler;
import com.beyondbell.bugisoft.old.Minigames.MinigameInput;
import com.beyondbell.bugisoft.old.UserInfo.UserInfoQuery;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;

public final class PrivateMessageReceivedEventHandler extends EventHandler {
	private final PrivateMessageReceivedEvent event;

	PrivateMessageReceivedEventHandler(final PrivateMessageReceivedEvent event) {
		super();
		this.event = event;
	}

	@Override
	protected void handle() {
		if (UserInfoQuery.getGameModeEnabled(event.getAuthor().getIdLong())) {
			new MinigameInput(event);
		}
	}
}
