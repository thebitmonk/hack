/**
 * Created by thebitmonk on 13/07/14.
 */
object P10 {
  def recursiveDuplicate[A](list: List[A]):List[List[A]] = list match {
    case Nil => Nil
    case data => {
      var pair = data.partition(x => x == data.head)
      pair._1 :: recursiveDuplicate(pair._2)
    }
  }

  def runLengthEncoding[A](list: List[List[A]]): List[(Int, A)] = {
    list.map {element => (element.length, element.head)}
  }

  def main (args: Array[String]) {
    val x = List('a', 'a', 'a', 'b', 'b', 'c', 'c', 'c', 'c', 'c', 'd')
    var d = recursiveDuplicate(x)
    println(d)
    println(runLengthEncoding(d))

  }
}
