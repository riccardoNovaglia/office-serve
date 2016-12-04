package gameState

case class Event(
                  pointsScored: Int,
                  scoringTeam: Int,
                  team2Total: Int,
                  team1Total: Int,
                  elapsedTimeInSeconds: Int
                ) {
  override def toString: String =
    s"At $timeInMinutes of the game, team $scoringTeam scores $pointsScored points!" +
      s" Total now $team1Total-$team2Total"

  private val timeInMinutes = {
    s"$minutesFromSeconds:$secondsRounded"
  }

  private def secondsRounded = toDoubleDigitString(elapsedTimeInSeconds % 60)
  private def minutesFromSeconds = toDoubleDigitString((elapsedTimeInSeconds % 3600) / 60)
  private def toDoubleDigitString(value: Int) = {
    val string = value.toString
    if (string.length == 2) string else "0" + string
  }
}

object GameJustStartedEvent extends Event(0, 0, 0, 0, 0) {
  override def toString: String =
    s"The game just started and both teams are at 0 points!"
}
