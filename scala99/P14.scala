/**
 * Created by thebitmonk on 13/07/14.
 */
import scala.collection.mutable.ListBuffer

object P14 {

  def functionalDuplicate[A](list: List[A]):List[A] =
    list flatMap(y => List(y, y))

  def main(args: Array[String]) {
    val x = List('a', 'b', 'c', 'd')
    println(functionalDuplicate(x))
  }
}
