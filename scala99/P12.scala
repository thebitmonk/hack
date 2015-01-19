/**
 * Created by thebitmonk on 13/07/14.
 */
object P12 {

  def decodeRunLengthEncoding[A](list: List[(Int, A)]):List[A] = {
    list flatMap(element => {
      var x = 0
      for (x<- 1 to element._1)
         yield element._2
      }
    )
  }

  def main (args: Array[String]) {
    val x = List((4, 'a'), (5, 'b'), (2, 'c'), (1, 'd'))
    println(decodeRunLengthEncoding(x))
  }
}
