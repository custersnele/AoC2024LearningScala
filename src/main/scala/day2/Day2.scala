package day2

import java.nio.file.{Files, Paths}
import scala.jdk.CollectionConverters.*

object Day2 {

  def main(args: Array[String]): Unit = {
    val path = Paths.get("src/main/scala/day2/day2.txt")
    val lines = Files.readAllLines(path).asScala.toList
    val reports = lines.map(_.split("\\s+").map(_.toInt).toList)

    // Count safe reports
    val safeCount = reports.count(isSafe)
    println(safeCount)

    // Count safe-with-remove reports
    val safeWithRemoveCount = reports.count(isSafeWithRemove)
    println(safeWithRemoveCount)
  }

  def isSafeWithRemove(report: List[Int]): Boolean = {
    if (isSafe(report)) {
      return true
    }
    report.indices.exists { i =>
      val modifiedReport = report.take(i) ++ report.drop(i + 1)
      isSafe(modifiedReport)

    }
  }

  def isSafe(report: List[Int]): Boolean = {
    val differences = report.sliding(2).map { case List(a, b) => b - a }.toList

    val hasMixedSigns = differences.exists(_ > 0) && differences.exists(_ < 0)
    val hasBigDifferences = differences.map(math.abs).exists(diff => diff == 0 || diff > 3)

    !hasMixedSigns && !hasBigDifferences
  }
}
