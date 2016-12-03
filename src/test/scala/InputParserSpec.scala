import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.prop.TableFor2
import org.scalatest.{FlatSpec, Matchers}

class InputParserSpec extends FlatSpec with Matchers {

  val data: TableFor2[String, Event] = Table[String, Event](
    ("Input Hex", "Event"),
    ("0x781002", Event(pointsScored = 2, scoringTeam = 1, team2Total = 0, team1Total = 2, elapsedTimeInSeconds = 15)),
    ("0xf0101f", Event(pointsScored = 3, scoringTeam = 2, team2Total = 3, team1Total = 2, elapsedTimeInSeconds = 30)),
    ("0x1310c8a1", Event(pointsScored = 1, scoringTeam = 1, team2Total = 20, team1Total = 25, elapsedTimeInSeconds = (10 * 60) + 10)),
    ("0x29f981a2", Event(pointsScored = 2, scoringTeam = 1, team2Total = 52, team1Total = 48, elapsedTimeInSeconds = (22 * 60) + 23)),
    ("0x48332327", Event(pointsScored = 3, scoringTeam = 2, team2Total = 100, team1Total = 100, elapsedTimeInSeconds = (38 * 60) + 30))
  )

  behavior of "The input parser"

  forAll(data) { (input: String, event: Event) =>
    it should s"parse input '$input' into event '$event'" in {
      InputParser.eventFrom(input) should be(event)
    }
  }

}
