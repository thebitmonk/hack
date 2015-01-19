/**
 * Created by thebitmonk on 13/07/14.
 */
// Solution taken:: Have previously solved via recursive partition.
// had not explored span

object P13 {
  def runLengthEncoding[A](list: List[A]):List[(Int, A)] = {
    if(list.isEmpty)
      Nil
    else {
      val (packed, remaining) = list span {_ == list.head}
      (packed.length, packed.head) :: runLengthEncoding(remaining)
    }
  }


  def main (args: Array[String]) {
    val x = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')
    println(runLengthEncoding(x))
  }
}
