import scala.io.Source

/**
 * Solution to https://www.codeeval.com/open_challenges/190/
 */
object NumberOperations extends App {
  val plus = (x: Int, y: Int) => x + y
  val minus = (x: Int, y: Int) => x - y
  val times = (x: Int, y: Int) => x * y
  val ops = Array(plus, minus, times)

  def toInts(s: String) = s.split(" ").map(_.toInt)

  Source
    .fromFile(args(0))
    .getLines()
    .map(toInts)
    .foreach {
    vs =>
      val m = if (canReach(vs, 42)) "YES" else "NO"
      println(m)
  }

  def canReach(vs: Array[Int], n: Int): Boolean = {
    vs.permutations.foreach(p =>
      for (op1 <- ops)
        for (op2 <- ops)
          for (op3 <- ops)
            for (op4 <- ops) {
              val r = op4.apply(op3.apply(op2.apply(op1.apply(p(0), p(1)), p(2)), p(3)), p(4))
              if (r == n) return true
            }
    )
    false
  }

}
