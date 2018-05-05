package com.beyondbell.bugisoft.Standalone.AdminCommands;

import java.io.IOException;

public class Update {
	public Update() {
		try {
			Runtime.getRuntime().exec("reboot");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
