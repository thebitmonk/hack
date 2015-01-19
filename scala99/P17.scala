package main

/**
 * Created by thebitmonk on 20/07/14.
 */
object P17 {

  def recursiveSplit[A](n: Int, list: List[A]):(List[A], List[A]) = {
      def recursiveS(n: Int, currentList: List[A]):(List[A], List[A]) = (n, currentList) match {
        case (_, Nil) => (Nil, Nil)
        case (0, list) => (Nil, list)
        case (n, h :: tail) => {
          val (start, end) = recursiveS(n-1, tail)
          (h :: start, end)
        }
      }
      recursiveS(n, list)
  }

  def functionalSplit[A](n: Int, list: List[A]):(List[A], List[A]) = {
    (list.take(n), list.drop(n))
  }

  def partialSplit[A](start: Int, end: Int, list: List[A]):List[A] = (start, end, list) match{
    case (_, _, Nil ) => Nil
    case (_, e, _) if(e <= 0) => Nil
    case (x, y, h :: tail) if(x > 0) => partialSplit(x-1, y-1, tail)
    case (0, y, h :: tail) if(y > 0) => h :: partialSplit(0, y-1, tail)
  }

  def rotateList[A](n: Int, list: List[A]):List[A] = (n, list) match {
    case (_, Nil) => Nil
    case (0, list) => list
    case (n, h :: tail) => rotateList(n-1, tail) ::: List(h)
  }

  def main (args: Array[String]) {
    val l = List(1,2,3,4,5,6,7,8,9,10)
    println(recursiveSplit(3, l))
    println(functionalSplit(3, l))
    println(partialSplit(3, 7, l))
    println(rotateList(3, l))
  }
}
