/**
 * Created by thebitmonk on 12/07/14.
 */

object P01{

  def lastBuiltIn[T](list: List[T]):T = list.last

  def recursiveLast[T](list: List[T]):T = list match {
    case List() => throw new Error("List is empty. No last element")
    case List(x) => x
    case x :: tail => recursiveLast(tail)
  }

  def main (args: Array[String]) {
    //println(recursiveLast(List()))

    println(lastBuiltIn(List(1,2,3)))
    println(recursiveLast(List(1,2,3)))

    println(lastBuiltIn(List("Humpty","Sharma","dulhaniya")))
    println(recursiveLast(List("Humpty","Sharma","dulhaniya")))


  }
}
