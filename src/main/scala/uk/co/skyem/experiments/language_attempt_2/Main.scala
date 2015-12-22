package uk.co.skyem.experiments.language_attempt_2

import scala.io.StdIn

object Main extends App {
	var input: String = StdIn.readLine()
	println(input)
	println(StringToAST(input).makeAST().get._1.toString)
}
