package uk.co.skyem.experiments.language_attempt_2.ast

abstract class ASTEntry {
	override def toString: String = getData.toString
	def getData: Object
}
