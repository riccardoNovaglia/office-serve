package gameState

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
