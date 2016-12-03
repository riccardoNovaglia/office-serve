import org.scalatest.{FlatSpec, Matchers}

class GameTrackerSpec extends FlatSpec with Matchers with TestDataUtils {

  //  Write a utility that:
  //  • can parse incoming data items into a data structure suitable for capturing the state of the match
  //  • maintains a match state as additional data items are received. It should be possible to query
  //    the match state (via a method) for the following:
  //    ◦ the last event (i.e. which team last scored, how many points, at what point through the match
  //  and what the resulting match score was)
  //  ◦ the last n events (where 0 <= n <= Total Items)
  //  ◦ all events in the match so far
  //  • handles cases where an invalid data item is received or where data is inconsistent with previous
  //  data received.

  "The game tracker" should "start from a game that has not started yet" in {
    GameTracker().currentState should be(GameJustStartedEvent)
  }

  it should "receive input and return it when queried" in {
    val gameTracker = GameTracker()

    gameTracker.updateStateFrom(specExampleTeam1ScoresHex)
    gameTracker.currentState should be(specExampleTeam1ScoresEvent)

    gameTracker.updateStateFrom(specExampleTeam2ScoresBackHex)
    gameTracker.currentState should be(specExampleTeam2ScoresBackEvent)
  }

  it should "return the last n events that happened" in {
    val gameTracker = GameTracker()
    gameTracker.updateStateFrom(specExampleTeam1ScoresHex)
    gameTracker.updateStateFrom(specExampleTeam2ScoresBackHex)

    gameTracker.getLastNEvents(2) should be(List(specExampleTeam1ScoresEvent, specExampleTeam2ScoresBackEvent))
  }

  it should "return all events if n requested but less than n occurred" in {
    val gameTracker = GameTracker()
    gameTracker.updateStateFrom(specExampleTeam1ScoresHex)

    gameTracker.getLastNEvents(5) should be(List(GameJustStartedEvent, specExampleTeam1ScoresEvent))
  }

  it should "return all events when queried" in {
    val gameTracker = GameTracker()
    gameTracker.updateStateFrom(specExampleTeam1ScoresHex)
    gameTracker.updateStateFrom(specExampleTeam2ScoresBackHex)

    gameTracker.getAllEvents should be(List(GameJustStartedEvent, specExampleTeam1ScoresEvent, specExampleTeam2ScoresBackEvent))
  }

}
