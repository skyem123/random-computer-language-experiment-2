package uk.co.skyem.experiments.language_attempt_2

import scala.io.StdIn

object Main extends App {
	println(StringToAST(StdIn.readLine()).makeAST().get._1.toString)
}
