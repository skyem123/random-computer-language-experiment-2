package uk.co.skyem.experiments.language_attempt_2.ast

class ASTString(data: String) extends ASTEntry {
	override def toString: String = "\"" + data + "\""
	override def getData: String = data
}
