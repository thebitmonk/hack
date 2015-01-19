/**
 * Created by thebitmonk on 13/07/14.
 */
object P15 {

  def functionalNDuplicate[A](list: List[A], n: Int): List[A] =
    list flatMap(y => {
      var x = 0
      for(x <- 1 to n)
      yield y
    })

  def main (args: Array[String]) {
    val x = List('a', 'b', 'c', 'd')
    println(functionalNDuplicate(x, 5))
  }
}
