import scala.io.Source

/**
 * Solution to https://www.codeeval.com/open_challenges/208/
 */
object FindTheHighestScore extends App {

  Source
    .fromFile(args(0))
    .getLines()
    .foreach { line =>
      val scores = line.split(" \\| ").map(toInts)
      val size = scores(0).length
      val maxScores = Array.fill[Int](size)(Int.MinValue)
      for (i <- 0 until size) {
        for (j <- 0 until scores.length) {
          if (maxScores(i) < scores(j)(i)) {
            maxScores(i) = scores(j)(i)
          }
        }
      }
      println(maxScores.mkString(" "))
    }

  def toInts(s: String) = s.split(" ").map(_.toInt)
}