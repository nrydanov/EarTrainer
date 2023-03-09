package backend

trait Client {
  def getExpectedPitch : Int
  def generateNextPitch : Int

  def checkAnswer(pitch: Int) : Boolean

  def playNote(pitch: Int) : Unit

  def stopNote() : Unit
}
