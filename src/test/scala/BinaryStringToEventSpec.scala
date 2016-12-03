import org.scalatest.{FlatSpec, Matchers}

// 0 -1    | Points scored       | Number of points scored (either 1, 2 or 3)
// 2       | Who scored          | 0 indicates team 1 scored, 1 indicates team 2
// 3 - 10  | Team 2 points total | The total number of points team 2 has scored in the match.
// 11 - 18 | Team 1 points total | The total number of points team 1 has scored in the match.
// 19 - 30 | Elapsed match time  | Number of seconds since the start of the match. This represents the match clock, rather than “wall clock” time.
class BinaryStringToEventSpec extends FlatSpec with Matchers with TestDataUtils {
  "BinaryStringToEventSpec" should "parse 32 0s into an event where no point was scored at time 0 (by team 1 because default)" in {
    val input = "0" * 32

    val event: Event = BinaryStringToEvent.binaryToEvent(input)

    event.pointsScored should be(0)
    event.scoringTeam should be(1)
    event.team2Total should be(0)
    event.team1Total should be(0)
    event.elapsedTimeInSeconds should be(0)
  }

  it should "should parse the string into an event where team 2 scores 1 point at 0 seconds" in {
    val input = reversedZerosStringWithOnesAt(List(0, 2, 3))

    val event = BinaryStringToEvent.binaryToEvent(input)

    event should be(Event(1, 2, 1, 0, 0))
  }

  it should "parse the string into an event where team 1 scores the first 3 points at 15 seconds and team 2 had scored already 12 points" in {
    //                                             3pt   12pt  3pt T1  15 seconds
    val input = reversedZerosStringWithOnesAt(List(0, 1, 5, 6, 11, 12, 19, 20, 21, 22))

    val event = BinaryStringToEvent.binaryToEvent(input)

    event should be(Event(
      pointsScored = 3,
      scoringTeam = 1,
      team2Total = 12,
      team1Total = 3,
      elapsedTimeInSeconds = 15
    ))
  }

  it should "parse the first string given in spec into the correct event" in {
    val event = BinaryStringToEvent.binaryToEvent(specExampleTeam1ScoresBinary)

    event should be(specExampleTeam1ScoresEvent)
  }

  it should "parse the second string given in spec into the correct event" in {
    val event = BinaryStringToEvent.binaryToEvent(specExampleTeam2ScoresBackBinary)

    event should be(specExampleTeam2ScoresBackEvent)
  }
}
