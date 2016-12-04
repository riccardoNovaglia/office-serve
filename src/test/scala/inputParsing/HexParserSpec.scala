package inputParsing

import org.scalatest.{FlatSpec, Matchers}
import utils.TestDataUtils

class HexParserSpec extends FlatSpec with Matchers with TestDataUtils {

  "The hex parser" should "parse a hexadecimal string into 32 binary string" in  {
    val hexInput = "0x000000"

    val binaryString = HexParser.to32bitBinaryString(hexInput)

    binaryString should be("00000000000000000000000000000000")
  }

  it should "parse other numbers into correct 32 bits binary strings" in {
    HexParser.to32bitBinaryString(specExampleTeam1ScoresHex) should be(specExampleTeam1ScoresBinary)
    HexParser.to32bitBinaryString(specExampleTeam2ScoresBackHex) should be(specExampleTeam2ScoresBackBinary)
  }

  it should "throw an exception if the input in not in hex format" in {
    val exception = intercept[InputNotHexException] {
      HexParser.to32bitBinaryString("12345")
    }
    exception.getMessage should be("Input '12345' was not in hexadecimal notation" )
  }
}
