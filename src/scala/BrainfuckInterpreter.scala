import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
 * Solution to https://www.codeeval.com/browse/210/
 */
object BrainfuckInterpreter extends App {
  val mem = ArrayBuffer(0)
  val MIN = -128
  val MAX = 127

  def run(program: String): Unit = {
    var dp = 0
    var ip = 0
    var c = 0
    val out = new StringBuilder()

    for (i <- mem.indices) {
      mem(i) = 0
    }

    while (ip < program.length) {
      program(ip) match {
        case '>' =>
          dp += 1
          if (dp == mem.length) mem append 0
        case '<' =>
          dp -= 1
          if (dp < 0) {
            mem prepend 0
            dp = 0
          }
        case '+' =>
          mem(dp) += 1
          if (mem(dp) >  MAX) mem(dp) = MIN
        case '-' =>
          mem(dp) -= 1
          if (mem(dp) < MIN) mem(dp) = MAX
        case '.' => out += mem(dp).toChar
        case ',' => mem(dp) = Console.readChar()
        case '[' =>
          if (mem(dp) == 0) {
            c = 1
            while (c > 0) {
              ip += 1
              program(ip) match {
                case '[' => c += 1
                case ']' => c -= 1
                case _   => "default"
              }
            }
          }
        case ']' =>
          if (mem(dp) != 0) {
            c = 1
            while (c > 0) {
              ip -= 1
              program(ip) match {
                case '[' => c -= 1
                case ']' => c += 1
                case _   => "default"
              }
            }
          }
      }
      ip += 1
    }

    if (out.nonEmpty) println(out)
  }

  val source = Source.fromFile(args(0))
  val lines = source.getLines().filter(_.length > 0)
  for (program <- lines) {
    run(program)
  }
}