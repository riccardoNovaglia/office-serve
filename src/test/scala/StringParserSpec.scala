import org.scalatest.{FlatSpec, Matchers}

// 0 -1    | Points scored       | Number of points scored (either 1, 2 or 3)
// 2       | Who scored          | 0 indicates team 1 scored, 1 indicates team 2
// 3 - 10  | Team 2 points total | The total number of points team 2 has scored in the match.
// 11 - 18 | Team 1 points total | The total number of points team 1 has scored in the match.
// 19 - 30 | Elapsed match time  | Number of seconds since the start of the match. This represents the match clock, rather than “wall clock” time.
class StringParserSpec extends FlatSpec with Matchers with BinaryStringUtils {

  "Points" should "extract the correct value from a binary string" in {
    PointsScored.getValueFrom(allZeros.mkString) should be(0)
    PointsScored.getValueFrom(reversedZerosStringWithOneAt(0)) should be(1)
    PointsScored.getValueFrom(reversedZerosStringWithOneAt(1)) should be(2)
    PointsScored.getValueFrom(reversedZerosStringWithOnesFrom(0 to 1)) should be(3)
    PointsScored.getValueFrom(specExampleTeam1Scores) should be(2)
    PointsScored.getValueFrom(specExampleTeam2ScoresBack) should be(3)
  }

  "ScoringTeam" should "extract the correct value from a binary string" in {
    ScoringTeam.getValueFrom(allZeros.mkString) should be(0)
    ScoringTeam.getValueFrom(reversedZerosStringWithOneAt(2)) should be(1)
    ScoringTeam.getValueFrom(specExampleTeam1Scores) should be(0)
    ScoringTeam.getValueFrom(specExampleTeam2ScoresBack) should be(1)
  }

  "Team2Total" should "extract the correct value from a binary string" in {
    Team2Total.getValueFrom(allZeros.mkString) should be(0)
    Team2Total.getValueFrom(reversedZerosStringWithOneAt(3)) should be(1)
    Team2Total.getValueFrom(reversedZerosStringWithOnesFrom(3 to 10)) should be(255)
    Team2Total.getValueFrom(specExampleTeam1Scores) should be(0)
    Team2Total.getValueFrom(specExampleTeam2ScoresBack) should be(3)
  }

  "Team1Total" should "extract the correct value from a binary string" in {
    Team1Total.getValueFrom(allZeros.mkString) should be(0)
    Team1Total.getValueFrom(reversedZerosStringWithOneAt(11)) should be(1)
    Team1Total.getValueFrom(reversedZerosStringWithOnesFrom(11 to 18)) should be(255)
    Team1Total.getValueFrom(specExampleTeam1Scores) should be(2)
    Team1Total.getValueFrom(specExampleTeam2ScoresBack) should be(2)
  }

  "elapsedTimeInSeconds" should "extract the correct value from a binary string" in {
    ElapsedTimeInSeconds.getValueFrom(allZeros.mkString) should be(0)
    ElapsedTimeInSeconds.getValueFrom(reversedZerosStringWithOneAt(19)) should be(1)
    ElapsedTimeInSeconds.getValueFrom(reversedZerosStringWithOnesFrom(19 to 30)) should be(4095)
    ElapsedTimeInSeconds.getValueFrom(specExampleTeam1Scores) should be(15)
    ElapsedTimeInSeconds.getValueFrom(specExampleTeam2ScoresBack) should be(30)
  }
}
