case class Event(
                  pointsScored: Int,
                  scoringTeam: Int,
                  team2Total: Int,
                  team1Total: Int,
                  elapsedTimeInSeconds: Int
                ) {
  private val timeInMinutes = {
    ((elapsedTimeInSeconds % 3600) / 60).toString + ":" + (elapsedTimeInSeconds % 60).toString
  }

  override def toString: String =
    s"At $timeInMinutes of the game, team $scoringTeam scores $pointsScored points!" +
      s" Total now $team1Total-$team2Total"
}
