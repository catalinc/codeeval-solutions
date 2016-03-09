import scala.io.Source

/**
 * Solution to https://www.codeeval.com/open_challenges/211/
 */
object ChardonnayOrCabernet extends App {

  val source = Source.fromFile(args(0))
  val lines = source.getLines().filter(_.length > 0)
  for (l <- lines) {
    val arr = l.split(" \\| ")
    val ns = arr(0)
    val ls = arr(1)
    val ms = wineNames(ns, ls)
    if (ms.nonEmpty) {
      println(ms.mkString(" "))
    } else {
      println("False")
    }
  }

  def wineNames(ns: String, ls: String): Array[String] =
    ns.split(" ").filter(containsLetters(_, ls))


  def containsLetters(s1: String, s2: String): Boolean =
    s2.forall(c => s2.count(_ == c) <= s1.count(_ == c))

}
