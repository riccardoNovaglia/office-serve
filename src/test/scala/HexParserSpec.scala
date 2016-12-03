import org.scalatest.{FlatSpec, Matchers}

class HexParserSpec extends FlatSpec with Matchers {

  "The hex parser" should "parse a hexadecimal string into 32 binary string" in  {
    val hexInput = "0x000000"

    val binaryString = HexParser.to32bitBinaryString(hexInput)

    binaryString should be("00000000000000000000000000000000")
  }

  it should "parse other numbers into correct 32 bits binary strings" in {
    HexParser.to32bitBinaryString("0x781002") should be("00000000011110000001000000000010")
    HexParser.to32bitBinaryString("0xf0101f") should be("00000000111100000001000000011111")
  }

  it should "throw an exception if the input in not in hex format" in {
    val exception = intercept[InputNotHexException] {
      HexParser.to32bitBinaryString("12345")
    }
    exception.getMessage should be("Input '12345' was not in hexadecimal notation" )
  }
}
