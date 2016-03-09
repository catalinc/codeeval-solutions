import scala.io.Source

/**
 * Solution https://www.codeeval.com/open_challenges/205/
 */
object CleanupTheWords extends App {

  Source
    .fromFile(args(0))
    .getLines()
    .foreach { l =>
      println(l.map(c => if (c.isLetter) c.toLower else ' ').trim.replaceAll("\\s+", " "))
    }

}
