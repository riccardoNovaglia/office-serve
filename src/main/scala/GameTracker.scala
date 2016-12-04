import gameState.{Event, GameState}
import inputParsing.InputParser

case class GameTracker() {

  private val state: GameState = GameState()

  def currentState: Event = state.currentState
  def getLastNEvents(n: Int): List[Event] = state.getLastNEvents(n)
  def getAllEvents: List[Event] = state.getAllEvents

  def updateStateFrom(input: String): Unit = {
    try {
      state.updateStateFrom(InputParser.eventFrom(input))
    } catch {
      case e: Throwable => throw new GameStateUpdateFailedException("Failed to update game state", e)
    }
  }
}

class GameStateUpdateFailedException(message: String, cause: Throwable) extends Exception(message, cause)
