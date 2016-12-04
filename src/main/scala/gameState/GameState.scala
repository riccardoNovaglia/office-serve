package gameState

case class GameState() {
  private var statesHistory: List[Event] = List(GameJustStartedEvent)

  def currentState: Event = statesHistory.last
  def getLastNEvents(n: Int): List[Event] = statesHistory.takeRight(n)
  def getAllEvents: List[Event] = statesHistory

  def updateStateFrom(event: Event): Unit = {
    ensureEventConsistency(event)
    statesHistory = statesHistory :+ event
  }

  private def ensureEventConsistency(event: Event) = {
    ensureTimeConsistencyFor(currentState, event)
    ensurePointsAreScoredFor(currentState, event)
    ensurePointsTotalConsistencyFor(currentState, event)
  }

  private def ensureTimeConsistencyFor(currentState: Event, event: Event) = {
    if (event.elapsedTimeInSeconds <= currentState.elapsedTimeInSeconds) {
      throw new EventNotInCorrectTimeSequenceException(currentState, event)
    }
  }

  private def ensurePointsAreScoredFor(currentState: Event, event: Event) = {
    if (event.pointsScored == 0) {
      throw new NoPointsScoredException(currentState, event)
    }
  }

  private def ensurePointsTotalConsistencyFor(currentState: Event, event: Event) = {
    val pointsToCheck = scoringTeamTotalFor(event.scoringTeam, event)
    val expectedTeamTotal = scoringTeamTotalFor(event.scoringTeam, currentState) + event.pointsScored
    if (pointsToCheck != expectedTeamTotal) {
      throw new TotalScoresDoNotAddUpException(currentState, event, expectedTeamTotal, pointsToCheck)
    }
  }

  private def scoringTeamTotalFor(scoringTeam: Int, currentState: Event) = {
    if (scoringTeam == 1) currentState.team1Total else currentState.team2Total
  }
}
