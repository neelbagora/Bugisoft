package com.beyondbell.bugisoft.Standalone.AdminCommands;

import com.beyondbell.bugisoft.Bot;
import net.dv8tion.jda.core.JDA;

import java.io.IOException;

public final class Update {
	public Update(final JDA jda) {
		try {
			jda.shutdown();
			Runtime.getRuntime().exec("reboot");
		} catch (final IOException e) {
			Bot.LOGGER.fatal("Cannot Find the Restart/Update Script! Terminating!");
			Runtime.getRuntime().exit(1);
		}
	}
}
