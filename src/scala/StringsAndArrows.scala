import scala.io.Source

/**
 * Solution to https://www.codeeval.com/open_challenges/203/
 */
object StringsAndArrows extends App {
  val LEFT_ARROW = "<--<<"
  val RIGHT_ARROW = ">>-->"
  
  def countArrows(input: String, arrow: String): Int = {
    var c = 0
    var i = input.indexOf(arrow)
    while (i >= 0) {
      c += 1
      i = input.indexOf(arrow, i + arrow.length - 1)
    }
    c
  }

  val source = Source.fromFile(args(0))
  val lines = source.getLines().filter(_.length > 0)
  for (l <- lines) {
    println(countArrows(l, LEFT_ARROW) + countArrows(l, RIGHT_ARROW))
  }
}
