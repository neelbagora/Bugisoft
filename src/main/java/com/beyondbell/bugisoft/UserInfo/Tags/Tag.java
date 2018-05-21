package com.beyondbell.bugisoft.UserInfo.Tags;

public abstract class Tag {
	private final String reason;

	Tag() {
		this.reason = null;
	}

	Tag(final String reason) {
		this.reason = reason;
	}

	String getReason() {
		return reason;
	}
}
