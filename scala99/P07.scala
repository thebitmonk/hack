/**
 * Created by thebitmonk on 13/07/14.
 */
object P07 {

  def flatten(list: List[Any]):List[Any] = list flatMap {
    case m: List[Any] => flatten(m)
    case e => List(e)
  }

  def main(args: Array[String]) {
    println(flatten(List(List(1, 1), 2, List(3, List(5, 8)))))

  }
}
