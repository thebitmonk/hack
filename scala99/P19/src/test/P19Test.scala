package test
import org.scalatest.FlatSpec
import main.P19
/**
 * Created by thebitmonk on 21/07/14.
 */
class P19Test extends FlatSpec {
  "Rotating empty List" should "return Nil" in {
    assert(P19.recursiveRotate(10, Nil) == Nil)
  }

  it should("handle positive rotation values") in {
    val l = List(1, 2 ,3 ,4 ,5)
    assert(P19.recursiveRotate(1, l) == List(2, 3, 4, 5, 1))
  }

  it should("handle negative rotation values") in {
    val l = List(1, 2, 3, 4, 5)
    assert(P19.rotate(-1, l) == List(5, 1, 2, 3, 4))
  }

  // Repeating tests. Should use a cleaner abstraction
  "Rotating empty List" should "return Nil" in {
    assert(P19.rotate(10, Nil) == Nil)
  }

  it should("handle positive rotation values") in {
    val l = List(1, 2 ,3 ,4 ,5)
    assert(P19.rotate(1, l) == List(2, 3, 4, 5, 1))
  }

  it should("handle negative rotation values") in {
    val l = List(1, 2, 3, 4, 5)
    assert(P19.recursiveRotate(-1, l) == List(5, 1, 2, 3, 4))
  }

}
