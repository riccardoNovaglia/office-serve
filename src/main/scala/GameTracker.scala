case class GameTracker() {

  private var statesHistory: List[Event] = List(GameJustStartedEvent)

  def currentState: Event = statesHistory.last
  def getLastNEvents(n: Int): List[Event] = statesHistory.takeRight(n)
  def getAllEvents: List[Event] = statesHistory

  def updateStateFrom(input: String): Unit = {
    statesHistory = statesHistory :+ InputParser.eventFrom(input)
  }
}
