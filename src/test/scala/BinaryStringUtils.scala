trait BinaryStringUtils {
  val specExampleTeam1Scores = "00000000011110000001000000000010"
  val specExampleTeam2ScoresBack = "00000000111100000001000000011111"

  val allZeros: List[String] = List.fill(32)("0")
  def reversedZerosStringWithOneAt(i: Int): String = reverseAndToString(allZeros.patch(i, List("1"), 1))
  def reversedZerosStringWithOnesAt(ones: List[Int]): String = {
    var zerosAndOnes = allZeros
    ones.foreach(oneIndex => {
      zerosAndOnes = zerosAndOnes.patch(oneIndex, List("1"), 1)
    })
    reverseAndToString(zerosAndOnes)
  }
  def reversedZerosStringWithOnesFrom(ones: Range): String = {
    reversedZerosStringWithOnesAt(ones.toList)
  }
  def reverseAndToString(chars: List[String]): String = chars.reverse.mkString
}
