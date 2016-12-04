package inputParsing

import gameState.Event
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.prop.TableFor2
import org.scalatest.prop.Tables.Table
import org.scalatest.{FlatSpec, Matchers}
import utils.TestDataUtils

class InputParserSpec extends FlatSpec with Matchers with TestDataUtils {

  val data: TableFor2[String, Event] = Table[String, Event](
    ("Input Hex", "Event"),
    (specExampleTeam1ScoresHex, specExampleTeam1ScoresEvent),
    (specExampleTeam2ScoresBackHex, specExampleTeam2ScoresBackEvent),
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
