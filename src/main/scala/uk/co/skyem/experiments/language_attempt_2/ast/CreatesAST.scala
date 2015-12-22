package uk.co.skyem.experiments.language_attempt_2.ast

trait CreatesAST {
	def makeAST(): Option[(ASTEntry, Int)]
	def makeASTList(): Option[(ASTList, Int)]
	def makeASTString(): Option[(ASTString, Int)]
	def makeASTWord(): Option[(ASTWord, Int)]
}
