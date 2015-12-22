package uk.co.skyem.experiments.language_attempt_2.ast

class ASTWord(name: String) extends ASTEntry {
	override def getData: String = name
}
