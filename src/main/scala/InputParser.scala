object InputParser {
  def eventFrom(input: String): Event = {
    BinaryStringToEvent.binaryToEvent(HexParser.to32bitBinaryString(input))
  }
}
