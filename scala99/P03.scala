/**
 * Created by thebitmonk on 12/07/14.
 */
object P03 {

  def kthBuildIn[T](list: List[T], k: Int):T =
      if(k >= 0)
        list(k)
      else
        throw new NoSuchElementException

  def recursiveKth[T](list: List[T], k: Int):T = (k, list) match{
    case (_, Nil) => throw new NoSuchElementException
    case (0, x :: _) => x
    case (n, _ :: tail) => {
      if(n < 0)
        throw new NoSuchElementException
      else
        recursiveKth(tail, n-1)
    }

  }

  def main (args: Array[String]) {
    println(recursiveKth(List(1, 2, 4, 4), 1))
    println(recursiveKth(List(1, 2, 3, 4), -1))
  }

}
