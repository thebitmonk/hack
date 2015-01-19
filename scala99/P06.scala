/**
 * Created by thebitmonk on 12/07/14.
 */
object P06 {

  def builtinPalindrome[T](list: List[T]):Boolean =
    list == list.reverse

  def loopPalindrome[T](list: List[T]): Boolean = {
    val length = list.length - 1
    val halfLength = (length/2)
    var index = 0
    while(index <= halfLength){
      if(list(index) != list(length - index))
        return false
      index = index + 1
    }
    true
  }

  def main (args: Array[String]) {
    val x = List(1, 2, 1, 2, 1)
    val y = List(1, 2, 3, 4, 5)
    println(builtinPalindrome(x))
    println(builtinPalindrome(y))
    println(loopPalindrome(y))

  }
}
