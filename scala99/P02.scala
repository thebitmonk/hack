import java.util.NoSuchElementException

/**
 * Created by thebitmonk on 12/07/14.
 */
object P02 {

  def penultimateBuiltIn[T](list: List[T]):T =
    if(list.isEmpty)
       throw new NoSuchElementException
    else
       list.init.last

  def recursivePenultimate[T](list: List[T]):T = list match {
    case List() => throw new NoSuchElementException
    case _ :: Nil => throw new NoSuchElementException
    case x :: _ :: Nil => x
    case _ :: tail => recursivePenultimate(tail)
  }

  def main (args: Array[String]) {
    println(penultimateBuiltIn(List(1,2,3,4)))
    println(recursivePenultimate(List(1,2,3,4)))


    println(penultimateBuiltIn(List("hello", "world")))
    println(recursivePenultimate(List("hello", "world")))

  }
}
