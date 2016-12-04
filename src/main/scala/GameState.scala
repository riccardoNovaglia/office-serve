case class GameState() {
  private var statesHistory: List[Event] = List(GameJustStartedEvent)

  def currentState: Event = statesHistory.last
  def getLastNEvents(n: Int): List[Event] = statesHistory.takeRight(n)
  def getAllEvents: List[Event] = statesHistory

  def updateStateFrom(event: Event): Unit = {
    println(s"Attempting to update game state with [$event]")
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

class InconsistentEventException(currentState: Event, event: Event, message: String)
  extends Exception(s"Could not update current state [$currentState] with event [$event]: " + message)

class EventNotInCorrectTimeSequenceException(currentState: Event, event: Event)
  extends InconsistentEventException(currentState: Event, event: Event,
    s"current event precedes previous state in time"
  )

class TotalScoresDoNotAddUpException(currentState: Event, event: Event, expectedTotal: Int, actualTotal: Int)
  extends InconsistentEventException(currentState: Event, event: Event,
    s"team [${event.scoringTeam}] scored [${event.pointsScored}] point, " +
      s"its total points should now be [$expectedTotal] but was [$actualTotal]"
  )

class NoPointsScoredException(currentState: Event, event: Event)
  extends InconsistentEventException(
    currentState: Event, event: Event,
    "no points were scored!"
  )
