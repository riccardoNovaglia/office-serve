package inputParsing

import scala.collection.immutable.Range.Inclusive

abstract class StringParser {
  protected val range: Range.Inclusive

  def getValueFrom(binaryString: String): Int = {
    val array = toList(binaryString)
    val slice = getSlice(array)
    Integer.parseInt(slice.mkString, 2)
  }

  private def getSlice(array: List[Char]) = {
    array.slice(32 - range.end, 32 - range.start)
  }

  private def toList(binaryString: String) = {
    binaryString.toCharArray.toList
  }
}

object PointsScored extends StringParser {
  override protected val range: Inclusive = 0 to 2
}

object ScoringTeam extends StringParser {
  override protected val range: Inclusive = 2 to 3
}

object Team2Total extends StringParser {
  override protected val range: Inclusive = 3 to 11
}

object Team1Total extends StringParser {
  override protected val range: Inclusive = 11 to 19
}

object ElapsedTimeInSeconds extends StringParser {
  override protected val range: Inclusive = 19 to 31
}
