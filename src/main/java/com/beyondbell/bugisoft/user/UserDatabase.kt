package com.beyondbell.bugisoft.user

object UserDatabase {
	private val users = HashMap<Long, BugisoftUser>()

	@Synchronized
	public fun getUser(id: Long): BugisoftUser {
		return if (users.containsKey(id)) {
			users[id]!!
		} else {
			users[id] = BugisoftUser(id)
			users[id]!!
		}
	}
}