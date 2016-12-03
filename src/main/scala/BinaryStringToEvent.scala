object BinaryStringToEvent {
  def binaryToEvent(input: String): Event = {
    Event(
      pointsScored = PointsScored.getValueFrom(input),
      scoringTeam = ScoringTeam.getValueFrom(input) + 1,
      team2Total = Team2Total.getValueFrom(input),
      team1Total = Team1Total.getValueFrom(input),
      elapsedTimeInSeconds = ElapsedTimeInSeconds.getValueFrom(input)
    )
  }
}
