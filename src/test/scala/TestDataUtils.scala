trait TestDataUtils {
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

  val specExampleTeam1ScoresHex = "0x781002"
  val specExampleTeam1ScoresBinary = "00000000011110000001000000000010"
  val specExampleTeam1ScoresEvent = Event(
    pointsScored = 2,
    scoringTeam = 1,
    team2Total = 0,
    team1Total = 2,
    elapsedTimeInSeconds = 15
  )

  val specExampleTeam2ScoresBackHex = "0xf0101f"
  val specExampleTeam2ScoresBackBinary = "00000000111100000001000000011111"
  val specExampleTeam2ScoresBackEvent = Event(
    pointsScored = 3,
    scoringTeam = 2,
    team2Total = 3,
    team1Total = 2,
    elapsedTimeInSeconds = 30
  )
}
