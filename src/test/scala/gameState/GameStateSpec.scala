package gameState

import org.scalatest.{FlatSpec, Matchers, OneInstancePerTest}
import utils.TestDataUtils

class GameStateSpec extends FlatSpec with Matchers with OneInstancePerTest with TestDataUtils {

  val gameState = GameState()

  "The game state" should "update events if they are consistent" in {
    gameState.updateStateFrom(Event(1, 1, 0, 1, 5))
    val finalState = Event(1, 1, 0, 2, 10)
    gameState.updateStateFrom(finalState)
    gameState.currentState should be(finalState)
  }

  it should "not update the state of the match if the events is not consistent with the previous state because of the time" in {
    gameState.updateStateFrom(Event(1, 1, 0, 1, 10))

    val exception = intercept[InconsistentEventException] {
      gameState.updateStateFrom(Event(1, 1, 0, 1, 5))
    }
    exception.getMessage should include("current event precedes previous state in time")
  }

  it should "not update the state of the match if the event is not consistent with the previous state because of the points totals" in {
    gameState.updateStateFrom(Event(1, 1, 0, 1, 10))

    val exception = intercept[InconsistentEventException] {
      gameState.updateStateFrom(Event(1, 1, 0, 1, 15))
    }
    exception.getMessage should include("team [1] scored [1] point, its total points should now be [2] but was [1]")
  }

  it should "not update the state of the match if the event says that team 1 scored but team 2 total scores go up instead" in {
    val exception = intercept[InconsistentEventException] {
      gameState.updateStateFrom(Event(1, 1, 1, 0, 10))
    }
    exception.getMessage should include("team [1] scored [1] point, its total points should now be [1] but was [0]")
  }

  it should "not update the state of the match if the event is not consistent with the previous state because of points scored to total" in {
    val exception = intercept[InconsistentEventException] {
      gameState.updateStateFrom(Event(1, 1, 0, 3, 10))
    }
    exception.getMessage should include("team [1] scored [1] point, its total points should now be [1] but was [3]")
  }

  // From spec: "A new supplier of basketball data is providing scoring data into our system."
  // Seems to be only related to scoring data, so an event when no team has scored is invalid.
  it should "not update the state of the match if the event says that no points were scored" in {
    val exception = intercept[InconsistentEventException] {
      gameState.updateStateFrom(Event(0, 1, 0, 0, 10))
    }
    exception.getMessage should include("no points were scored!")
  }

}
