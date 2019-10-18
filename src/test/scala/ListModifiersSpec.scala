import org.scalatest.FlatSpec

class ListModifiersSpec extends FlatSpec {

  val fibonacciList: List[Int] = 1 :: 1 :: 2 :: 3 :: 5 :: 8 :: Nil
  val palindromeList: List[Int] = List(1, 2, 3, 2, 1)
  val unflatList: List[Any] = List(List(1, 1), 2, List(3, List(5, 8)))
  val symbolsList: List[Symbol] = List(Symbol("a"), Symbol("a"), Symbol("a"), Symbol("a"), Symbol("b"), Symbol("c"), Symbol("c"), Symbol("a"), Symbol("a"), Symbol("d"), Symbol("e"), Symbol("e"), Symbol("e"), Symbol("e"))
  val encodedList = List((4, Symbol("a")), (1, Symbol("b")), (2, Symbol("c")), (2, Symbol("a")), (1, Symbol("d")), (4, Symbol("e")))
  val abridgedAlphabetList = List(Symbol("a"), Symbol("b"), Symbol("c"), Symbol("d"), Symbol("e"), Symbol("f"), Symbol("g"), Symbol("h"), Symbol("i"), Symbol("j"), Symbol("k"))

  "List Modifier method last" should "return the last element in a given list" in {
    assert(ListModifiers.last(fibonacciList) == 8)
  }

  "List Modifier method penultimare" should "return the penultimate element in a given list" in {
    assert(ListModifiers.penultimate(fibonacciList) == 5)
  }

  "List Modifier method kth" should "return the kth element in a given list" in {
    assert(ListModifiers.kth(2, fibonacciList) == 2)
    assert(ListModifiers.kth(4, fibonacciList) == 5)
  }

  it should "return IllegalArgumentException with invalid kth parameter" in {
    assertThrows[IllegalArgumentException] {
      ListModifiers.kth(-1, fibonacciList)
    }
  }

  "List Modifier method length" should "return the kth element in a given list" in {
    assert(ListModifiers.length(fibonacciList) == 6)
  }

  it should "return count of 0 for an empty list" in {
    assert(ListModifiers.length(Nil) == 0)
  }

  "List Modifier method reverse" should "return the given list in reverse" in {
    assert(ListModifiers.reverse(fibonacciList) == List(8, 5, 3, 2, 1, 1))
  }

  it should "return count of 0 for an empty list" in {
    assertThrows[IllegalArgumentException] {
      ListModifiers.reverse(Nil)
    }
  }

  "List Modifier method isPalindrome" should "return true for a palindrom" in {
    assert(ListModifiers.isPalindrome(palindromeList))
  }

  it should "return false for a non-palindrome" in {
    assert(!ListModifiers.isPalindrome(fibonacciList))
  }

  it should "return count of 0 for an empty list" in {
    assertThrows[IllegalArgumentException] {
      ListModifiers.isPalindrome(Nil)
    }
  }

  "List Modifier method flatten" should "return flat list" in {
    assert(ListModifiers.flatten(unflatList) == fibonacciList)
  }

  "List Modifier method compress" should "return list with duplicates removed" in {
    assert(ListModifiers.compress(symbolsList) == List(Symbol("a"), Symbol("b"), Symbol("c"), Symbol("a"), Symbol("d"), Symbol("e")))
  }

  it should "return an empty list for a input of empty list" in {
    assert(ListModifiers.compress(List()) == List())
  }

  "List Modifier method pack" should "return list with duplicate consecutive values in sublists" in {
    assert(ListModifiers.pack(symbolsList) == List(List(Symbol("a"), Symbol("a"), Symbol("a"), Symbol("a")), List(Symbol("b")), List(Symbol("c"), Symbol("c")), List(Symbol("a"), Symbol("a")), List(Symbol("d")), List(Symbol("e"), Symbol("e"), Symbol("e"), Symbol("e"))))
  }

  "List Modifier method encode" should "return list with run-length encoding data compression" in {
    assert(ListModifiers.encode(symbolsList) == List((4, Symbol("a")), (1, Symbol("b")), (2, Symbol("c")), (2, Symbol("a")), (1, Symbol("d")), (4, Symbol("e"))))
    assert(ListModifiers.encode(List()) == List())
  }

  "List Modifier method encodeModified" should "return list with run-length encoding data compression" in {
    assert(ListModifiers.encodeModified(symbolsList) == List((4, Symbol("a")), Symbol("b"), (2, Symbol("c")), (2, Symbol("a")), Symbol("d"), (4, Symbol("e"))))
    assert(ListModifiers.encodeModified(List()) == List())
  }

  "List Modifier method decode" should "return list with decoded/expanded list" in {
    assert(ListModifiers.decode(encodedList) == symbolsList)
    assert(ListModifiers.decode(List()) == List())
  }

  "List Modifier method encodeDirect" should "return list with run-length encoding data compression" in {
    assert(ListModifiers.encodeDirect(symbolsList) == List((4, Symbol("a")), (1, Symbol("b")), (2, Symbol("c")), (2, Symbol("a")), (1, Symbol("d")), (4, Symbol("e"))))
    assert(ListModifiers.encodeDirect(List()) == List())
  }

  "List Modifier method duplicate" should "return list with duplicates" in {
    assert(ListModifiers.duplicate(abridgedAlphabetList) == List(Symbol("a"), Symbol("a"), Symbol("b"), Symbol("b"), Symbol("c"), Symbol("c"), Symbol("d"), Symbol("d"), Symbol("e"), Symbol("e"), Symbol("f"), Symbol("f"), Symbol("g"), Symbol("g"), Symbol("h"), Symbol("h"), Symbol("i"), Symbol("i"), Symbol("j"), Symbol("j"), Symbol("k"), Symbol("k")))
    assert(ListModifiers.duplicate(List()) == List())
  }

  "List Modifier method duplicateN" should "return list with N duplicates" in {
    assert(ListModifiers.duplicateN(3, abridgedAlphabetList) == List(Symbol("a"), Symbol("a"), Symbol("a"), Symbol("b"), Symbol("b"), Symbol("b"), Symbol("c"), Symbol("c"), Symbol("c"), Symbol("d"), Symbol("d"), Symbol("d"), Symbol("e"), Symbol("e"), Symbol("e"), Symbol("f"), Symbol("f"), Symbol("f"), Symbol("g"), Symbol("g"), Symbol("g"), Symbol("h"), Symbol("h"), Symbol("h"), Symbol("i"), Symbol("i"), Symbol("i"), Symbol("j"), Symbol("j"), Symbol("j"), Symbol("k"), Symbol("k"), Symbol("k")))
    assert(ListModifiers.duplicateN(2, List()) == List())
  }

  "List Modifier method drop" should "return list with Nth elements removed" in {
    assert(ListModifiers.drop(3, abridgedAlphabetList) == List(Symbol("a"), Symbol("b"), Symbol("d"), Symbol("e"), Symbol("g"), Symbol("h"), Symbol("j"), Symbol("k")))
    assert(ListModifiers.drop(2, abridgedAlphabetList) == List(Symbol("a"), Symbol("c"), Symbol("e"), Symbol("g"), Symbol("i"), Symbol("k")))
  }

  "List Modifier method lotto" should "return list of given number of Ints" in {
    assert(ListModifiers.lotto(9, 20).size == 9)
    assert(ListModifiers.lotto(5, 30).map(e => e.isInstanceOf[Int]) == List.fill(5)(true))
  }
}
