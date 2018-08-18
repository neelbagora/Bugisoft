package com.beyondbell.bugisoft.utilities

fun getParameters(input: String): Array<String> {
	val parameters = ArrayList<String>()

	// Adds the Prefix
	parameters.add(input[0].toString())

	// Builds the Parameters
	var currentParameter = StringBuilder()
	for (i in 1 until input.length) {
		if (input[i] != ' ') {
			currentParameter.append(input[i])
		} else {
			parameters.add(currentParameter.toString())
			currentParameter = StringBuilder()
		}
	}
	parameters.add(currentParameter.toString())

	// Removes Whitespace at the End
	if (parameters.size > 1) {
		while (parameters[parameters.size - 1] == "") {
			parameters.removeAt(parameters.size - 1)
		}
	}

	return parameters.toTypedArray()
}