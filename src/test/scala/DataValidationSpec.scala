import org.scalatest.FlatSpec

class DataValidationSpec extends FlatSpec {

  "The data tracker" should "parse all events provided in the first file with spec without exceptions" in {
    val gameTracker = GameTracker()
    println(gameTracker.currentState)

    val inputs = List(
      "0x801002",
      "0xf81016",
      "0x1d8102f",
      "0x248202a",
      "0x2e0203e",
      "0x348204e",
      "0x3b8384b",
      "0x468385e",
      "0x5304059",
      "0x640406e",
      "0x6d8506a",
      "0x760606a",
      "0x838607e",
      "0x8e8707a",
      "0x930708e",
      "0x9f0709e",
      "0xad070a5",
      "0xb7880a2",
      "0xbf880b6",
      "0xc9080c6",
      "0xd2090c2",
      "0xdd090d6",
      "0xed0a8d3",
      "0xf98a8e6",
      "0x10a8b8e2",
      "0x1178b8ed",
      "0x1228c8ea",
      "0x12b0d8ea"
    )

    inputs.foreach(input => {
      gameTracker.updateStateFrom(input)
      println(gameTracker.currentState)
    })

  }


}