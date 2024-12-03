package day3

import java.nio.file.{Files, Paths}
import java.util.regex.Pattern
import scala.jdk.CollectionConverters._

object Day3 {
  def main(args: Array[String]): Unit = {
    // Read all lines from the file
    val lines = Files.readAllLines(Paths.get("src/main/scala/day3/day3.txt")).asScala.toList
    val pattern = Pattern.compile("do\\(\\)|mul\\((\\d+),(\\d+)\\)|don't\\(\\)")

    var sum: Long = 0
    var active = true
    val buffer = new StringBuilder()
    lines.foreach(buffer.append)

    var countMul = 0
    var countDo = 0
    var countDont = 0

    val matcher = pattern.matcher(buffer.toString())

    // Process matches
    while (matcher.find()) {
      val group = matcher.group()
      println(group)

      if (group.startsWith("mul(")) {
        countMul += 1
        if (active) {
          val x = matcher.group(1).toInt
          val y = matcher.group(2).toInt
          println(s"$x x $y")
          sum += x.toLong * y
        }
      } else if (group.startsWith("do()")) {
        active = true
        countDo += 1
      } else if (group.startsWith("don't()")) {
        active = false
        countDont += 1
      }
    }

    println(sum)
    println(s"do: $countDo")
    println(s"don't: $countDont")
    println(s"mul: $countMul")
  }
}
