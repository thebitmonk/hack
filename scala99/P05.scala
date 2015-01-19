/**
 * Created by thebitmonk on 12/07/14.
 */
object P05 {

  def builtinReverse[T](list: List[T]): List[T] =
     list.reverse

  def recursiveReverse[T](list: List[T]): List[T] = list match {
    case Nil => List()
    case List(x) => List(x)
    case head :: tail => recursiveReverse(tail) ::: List(head)
  }

  def tailRecursiveReverse[T](list: List[T]): List[T] = {

    def recursiveReverse(acc: List[T], newList: List[T]): List[T] = newList match {
      case Nil => acc
      case head :: tail => recursiveReverse(head :: acc, tail)
    }
    recursiveReverse(List(), list)
  }


  def main (args: Array[String]) {
    val list = List(1, 2, 3, 4, 5)
    println(builtinReverse(list))
    println(recursiveReverse(list))
    println(tailRecursiveReverse(list))
  }
}
