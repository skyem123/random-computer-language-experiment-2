package uk.co.skyem.experiments.language_attempt_2

import scala.collection.mutable
import scala.util.control.Breaks._

import uk.co.skyem.experiments.language_attempt_2.ast._

class StringToAST(string: String) extends CreatesAST{
	private def starter(starts: String): Option[String] = {
		if (!string.startsWith(starts)) { return None }
		Some(string.substring(starts.length))
	}
	
	override def makeAST(): Option[(ASTEntry, Int)] = {
		var result: Option[(ASTEntry, Int)] = None
		// First try making a string
		result = makeASTString()
		if (result.nonEmpty) return result
		// Then try making a list
		result = makeASTList()
		if (result.nonEmpty) return result
		
		// The last thing we try is turning it into a word
		result = makeASTWord()
		if (result.nonEmpty) return result
		// If all else fails, return nothing
		None
	}
	
	override def makeASTList(): Option[(ASTList, Int)] = {
		var characters = string
		if (characters.length == 0 || characters.charAt(0) != '(') { return None }
		characters = characters.substring(1)
		
		var list = mutable.Buffer[ASTEntry]()
		var i = 0
		var ended = false
		breakable {
			while (i < characters.length) {
				if (characters.charAt(i) == ')') {
					i += 2
					ended = true
					break
				} else if (characters.charAt(i).isWhitespace) {
					i += 1
				} else {
					val substring = characters.substring(i)
					val result = StringToAST(substring).makeAST()
					//println(substring)
					if (result.isEmpty) {
						throw new Exception(s"Expected something, got nothing. i=$i")
					}
					else {
						val results = result.get
						i += results._2
						list += results._1
					}
				}
			}
		}
		if (!ended) {
			throw new Exception("Unexpected end of input for list.")
		}
		
		Some((new ASTList(list.toArray), i))
	}
	
	override def makeASTString(): Option[(ASTString, Int)] = {
		var characters = string
		if (string.length == 0 || string.charAt(0) != '"') { return None }
		characters = characters.substring(1)
		
		var result = ""
		var position = 1
		var escaped = false
		var finished = false
		breakable {
			for (character <- characters) {
				position += 1
				if (!escaped) {
					character match {
						case '\\' => escaped = true
						case '"' => { finished = true; break }
						case other => result += other
					}
				} else {
					character match {
						case '\\' => '\\'
						case 'n' => result += '\n'
					}
				}
			}
		}
		if (!finished) {
			throw new Exception("Unexpected end of input for string.")
		}
		
		Some((new ASTString(result), position))
	}
	
	override def makeASTWord(): Option[(ASTWord, Int)] = {
		var result = ""
		breakable { for (character <- string) {
			if (character.isWhitespace || character == ')' || character == '(') { break }
			result += character
		}}
		if (result.length == 0) {
			None
		} else {
			Some(new ASTWord(result), result.length) 
		}
	}
}

object StringToAST {
	def apply(string: String): StringToAST = new StringToAST(string)
}
