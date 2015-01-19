/**
 * Created by thebitmonk on 13/07/14.
 */
object P16 {

  def dropRecursive[A](n: Int, list: List[A]):List[A] = {
    def dropR(c: Int, curList: List[A]):List[A] = (c, curList) match {
      case (_, Nil) => Nil
      case (1, _ :: tail) => dropR(n, tail)
      case (_, h :: tail) => h :: dropR(c - 1, tail)
    }
    dropR(n, list)
  }

  def dropFunctional[A](n: Int, list: List[A]):List[A] = {
    list.zipWithIndex filter { v => (v._2 + 1) % n != 0 } map ( _._1 )
  }

  def main (args: Array[String]) {
    val x = List(1,2,3,4,5,6,7,8,9,10)
    println(dropRecursive(3, x))
    println(dropFunctional(3, x))
  }
}
