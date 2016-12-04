package inputParsing

abstract class StringParser {
  protected val lowerBoundIndex: Int
  protected val upperBoundIndex: Int

  def getValueFrom(binaryString: String): Int = {
    val array = toList(binaryString)
    val slice = getSlice(array)
    Integer.parseInt(slice.mkString, 2)
  }

  private def getSlice(array: List[Char]) = {
    array.slice(32 - upperBoundIndex, 32 - lowerBoundIndex)
  }

  private def toList(binaryString: String) = {
    binaryString.toCharArray.toList
  }
}

object PointsScored extends StringParser {
  override protected val lowerBoundIndex: Int = 0
  override protected val upperBoundIndex: Int = 2
}

object ScoringTeam extends StringParser {
  override protected val lowerBoundIndex: Int = 2
  override protected val upperBoundIndex: Int = 3
}

object Team2Total extends StringParser {
  override protected val lowerBoundIndex: Int = 3
  override protected val upperBoundIndex: Int = 11
}

object Team1Total extends StringParser {
  override protected val lowerBoundIndex: Int = 11
  override protected val upperBoundIndex: Int = 19
}

object ElapsedTimeInSeconds extends StringParser {
  override protected val lowerBoundIndex: Int = 19
  override protected val upperBoundIndex: Int = 31
}
