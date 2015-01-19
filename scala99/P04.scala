/**
 * Created by thebitmonk on 12/07/14.
 */
object P04 {

  def builtinNumberOfElements[T](list: List[T]) =
    list.length

  def recursiveNumberOfElements[T](list: List[T]): Int = list match {
    case Nil => 0
    case _ :: tail => 1 + recursiveNumberOfElements(tail)
  }

  def tailRecursiveNumberOfElements[T](list: List[T]): Int = {

    def numberOfElements[T](total: Int, newList: List[T]): Int = newList match {
      case Nil => total
      case _ :: tail => numberOfElements(total + 1, tail)
    }
    numberOfElements(0, list)
  }

  def main (args: Array[String])(){
    val x = List(1, 2, 3, 4, 5, 6)
    println(builtinNumberOfElements(x))
    println(recursiveNumberOfElements(x))
    println(tailRecursiveNumberOfElements(x))
  }
}
