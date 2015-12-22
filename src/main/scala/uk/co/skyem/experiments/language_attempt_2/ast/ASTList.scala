package uk.co.skyem.experiments.language_attempt_2.ast

class ASTList(contents: Array[ASTEntry]) extends ASTEntry {
	override def toString: String = {
		var string = "("
		if (contents.isEmpty) { string += ")" } 
		else {
			for (item <- contents) {
				string += item.toString + " "
			}
		}
		string = string.substring(0, string.length - 1) + ")"
		return string
	}
	override def getData: Array[ASTEntry] = contents
}
