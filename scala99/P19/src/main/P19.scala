package main

/**
 * Created by thebitmonk on 21/07/14.
 */
object P19 {

  // Creates a list of the first n elements
  def positiveSimpleRotate[A](n: Int, list:List[A]): List[A] = (n, list) match{
    case (0, list) => Nil
    case (n , head :: tail) => head :: positiveSimpleRotate(n - 1, tail)
  }

  // Creates a list from n+1 st element to last
  def negativeSimpleRotate[A](n: Int, list:List[A]): List[A] = (n, list) match{
    case (0, list) => list
    case (n , head :: tail) => negativeSimpleRotate(n - 1, tail)
  }

  // Utilizes positive and negative simple rotate so to create rotated list
  def recursiveRotate[A](n:Int, list:List[A]):List[A] = (n, list) match{
    case (_, Nil) => Nil
    case (n, list) if(n < 0) => recursiveRotate(n + list.length, list)
    case (n, list) => negativeSimpleRotate(n, list) ::: positiveSimpleRotate(n, list)
  }

  def rotate[A](n: Int, ls:List[A]):List[A] = {
    val changedN = if(ls.isEmpty) 0 else n % ls.length
    if(changedN < 0) rotate(changedN + ls.length, ls)
    else ls.drop(changedN) ::: ls.take(changedN)
  }

  def main (args: Array[String]) {
    val ls = List(1, 2, 3, 4, 5, 6, 7, 8)
    println(rotate(-1, ls))
    println(recursiveRotate(3, ls))
  }

}
