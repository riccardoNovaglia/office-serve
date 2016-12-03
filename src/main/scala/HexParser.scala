object HexParser {

  def to32bitBinaryString(hexInput: String): String = {
    val validatedInput = validate(hexInput)
    padTo32Bits(Integer.parseInt(validatedInput, 16).toBinaryString)
  }

  private def validate(input: String) = {
    if (!input.startsWith("0x")) {
      throw new InputNotHexException(s"Input '$input' was not in hexadecimal notation")
    } else {
      input.drop(2)
    }
  }

  private def padTo32Bits(binaryString: String) = {
    val leadingZeros = "0" * (32 - binaryString.length)
    leadingZeros + binaryString
  }

}

class InputNotHexException(message: String) extends Exception(message)
