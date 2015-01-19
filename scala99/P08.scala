/**
 * Created by thebitmonk on 13/07/14.
 */
object P08 {

  def recursiveRemoveConsecutiveDuplicate[A](list: List[A]):List[A] = list match {
    case Nil => Nil
    case a :: tail => a :: recursiveRemoveConsecutiveDuplicate(tail.dropWhile(_ == a))
  }

  def tailRecursiveRemoveConsecutiveDuplicate[A](list: List[A]):List[A] = {

    def removeConsecutiveDuplicate[A](currentList: List[A], finalList: List[A]): List[A] = currentList match{
      case Nil => finalList.reverse
        case a :: tail => removeConsecutiveDuplicate(tail.dropWhile(_ == a), a :: finalList)
    }

    removeConsecutiveDuplicate(list, Nil)

  }

  def compressTailRecursive[A](ls: List[A]): List[A] = {
    def compressR(result: List[A], curList: List[A]): List[A] = curList match {
      case h :: tail => compressR(h :: result, tail.dropWhile(_ == h))
      case Nil       => result.reverse
    }
    compressR(Nil, ls)
  }

  def functionalRemoveConsecutiveDuplicate[A](list: List[A]):List[A] = {
    list.foldLeft(List(list.head))((r, c) => if(r.head != c) c :: r else r).reverse
  }

  def main(args: Array[String]) {
    val x = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')
    println(functionalRemoveConsecutiveDuplicate(x))
    //println(recursiveRemoveConsecutiveDuplicate(x))
    println(tailRecursiveRemoveConsecutiveDuplicate(x))
  }
}
