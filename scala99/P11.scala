/**
 * Created by thebitmonk on 13/07/14.
 */
object P11 {
  def recursiveDuplicate[A](list: List[A]):List[List[A]] = list match {
    case Nil => Nil
    case data => {
      var pair = data.partition(x => x == data.head)
      pair._1 :: recursiveDuplicate(pair._2)
    }
  }

  def modifiedRunLengthEncoding[A](list: List[List[A]]): List[Any] = {
    list.map {element => if(element.length == 1) (element.length, element.head) else element.head}
  }

  def main (args: Array[String]) {
    val x = List('a', 'a', 'a', 'b', 'c', 'c', 'c', 'c', 'c', 'd')
    var d = recursiveDuplicate(x)
    println(d)
    println(modifiedRunLengthEncoding(d))

  }
}
