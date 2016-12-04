case class GameTracker() {

  private val state: GameState = GameState()

  def currentState: Event = state.currentState
  def getLastNEvents(n: Int): List[Event] = state.getLastNEvents(n)
  def getAllEvents: List[Event] = state.getAllEvents

  def updateStateFrom(input: String): Unit = {
    try {
      state.updateStateFrom(InputParser.eventFrom(input))
    } catch {
      case e: Throwable => println(s"Failed to parse event $input. Failed with exception $e")
    }
  }
}
