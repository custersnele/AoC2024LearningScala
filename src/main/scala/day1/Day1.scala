package day1

import java.nio.file.{Files, Paths}
import scala.jdk.CollectionConverters.*
import scala.math.abs

object Day1 {

  def main(args: Array[String]): Unit = {
    val path = Paths.get("src/main/scala/day1/day1.txt")
    val list = Files.readAllLines(path).asScala.toList
    val (left, right) = list.map { s =>
      val split = s.split("\\s+")
      (split(0).toInt, split(1).toInt)
    }.unzip

    val leftSorted = left.sorted
    val rightSorted = right.sorted

    val totalDistance = leftSorted.zip(rightSorted).map {
      case (l, r) => abs(l - r)
    }.sum

    println(s"Total Distance: $totalDistance")

    val simularity = left.foldLeft(0L) { (score, value) =>
      score + value * right.count(_ == value)
    }

    println(s"Simularity: $simularity")
  }
}
