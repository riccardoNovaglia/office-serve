import gameState.Event
import org.scalatest.{FlatSpec, Matchers, OneInstancePerTest}

class DataValidationSpec extends FlatSpec with Matchers with OneInstancePerTest {

  val gameTracker = GameTracker()
  "The data tracker" should "parse all events provided in the first file with spec without exceptions" in {
    println(gameTracker.currentState)

    val inputs = List(
      "0x801002",
      "0xf81016",
      "0x1d8102f",
      "0x248202a",
      "0x2e0203e",
      "0x348204e",
      "0x3b8384b",
      "0x468385e",
      "0x5304059",
      "0x640406e",
      "0x6d8506a",
      "0x760606a",
      "0x838607e",
      "0x8e8707a",
      "0x930708e",
      "0x9f0709e",
      "0xad070a5",
      "0xb7880a2",
      "0xbf880b6",
      "0xc9080c6",
      "0xd2090c2",
      "0xdd090d6",
      "0xed0a8d3",
      "0xf98a8e6",
      "0x10a8b8e2",
      "0x1178b8ed",
      "0x1228c8ea",
      "0x12b0d8ea"
    )

    inputs.foreach(input => {
      stateShouldBeUpdateWith(input)
    })
  }

  it should "filter out bad events from a stream of inconsistent data" in {
    val expectedFailures = List(3, 8, 12, 14, 17, 22, 26)

    val inputs = List(
      "0x781002",
      "0xe01016",
      "0x1081014", // 0 points scored
      "0x1e0102f",
      "0x258202a",
      "0x308203e",
      "0x388204e",
      "0x388204e", // duplicate from above - points do not add up
      "0x3d0384b",
      "0x478385e",
      "0x618406e",
      "0x5404059", // team1 scores 1 point but total doesn't increase, team 2 decreases, time is wrong
      "0x6b8506a",
      "0x750706c", // 0 points
      "0x7d8507e",
      "0x938608e",
      "0x8b8607a", // wrong time, totals don't match up
      "0xa10609e",
      "0xb8870a2",
      "0xc4870b6",
      "0xcc070c6",
      "0x2ee74753", // time and totals
      "0xd5080c2",
      "0xdf080d6",
      "0xe4098d3",
      "0xec098f6", // totals don't match up
      "0xfc8a8e2",
      "0x10a8a8ed",
      "0x1180b8ea",
      "0x1218c8ea"
    )

    inputs.indices.foreach(i => {
      val input = inputs(i)

      if (expectedFailures.contains(i + 1)) {
        stateShouldNotBeUpdateWith(input)
      } else {
        stateShouldBeUpdateWith(input)
      }
    })
  }

  private def stateShouldBeUpdateWith(input: String) = {
    val (previousState, currentState) = getPreviousAndCurrentStateAfter(input)
    previousState should not be currentState
    println(currentState)
  }

  private def stateShouldNotBeUpdateWith(input: String) = {
    val previousState = gameTracker.currentState
    val exception = intercept[GameStateUpdateFailedException] {
      gameTracker.updateStateFrom(input)
    }
    previousState should be (gameTracker.currentState)
    println(s"Caught exception [$exception] with cause [${exception.getCause.getClass.getName}]")
  }

  private def getPreviousAndCurrentStateAfter(input: String): (Event, Event) = {
    val previousState = gameTracker.currentState
    gameTracker.updateStateFrom(input)

    (previousState, gameTracker.currentState)
  }

}
