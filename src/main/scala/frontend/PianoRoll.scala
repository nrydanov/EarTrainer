package frontend

import com.typesafe.scalalogging.Logger

import java.awt.{Color, Dimension, Graphics, Graphics2D, GridLayout}
import javax.swing.JPanel

class PianoRoll(width: Int, height: Int,
                lowestPitch: Int, highestPitch: Int) extends JPanel {

  private val numberOfNotes = highestPitch - lowestPitch + 1
  private val cellWidth = 60
  private val cellHeight = Integer.max(20, height / (highestPitch - lowestPitch))
  private val numRows = numberOfNotes
  private val numCols = width / cellWidth

  private var currentCol = 1
  private var lastClicked : Cell = _

  private val logger = Logger("logger")

  private def createCells(): Unit = {
    for (row <- 0 until numRows; col <- 0 until numCols) {
      val cell = new Cell(col, row, cellWidth, cellHeight)
      if (col == 0) {
        cell.label = convertIntToNote(row)
      }
      this.add(cell)
    }
  }

  def correct(): Unit = {

    lastClicked.markAsSuccessful()

    currentCol += 1
  }

  def failed(expected: Int): Unit = {

    lastClicked.markAsFailed()
    for (c <- getComponents) {
      c match {
        case cell: Cell =>
          val pitch = highestPitch - cell.getRow
          if (pitch == expected && cell.getCol == currentCol) {
            cell.clicked = true
            cell.markAsExpected()
          }
      }
    }

    currentCol += 1
  }

  def getAnswer: Option[Int] = {
    val components = getComponents

    for (c <- components) {
      c match {
        case cell: Cell =>
          val pitch = highestPitch - cell.getRow
          if (cell.clicked) {
            if (cell.getCol == currentCol) {
              lastClicked = cell
              return Option[Int](pitch)
            }
            else if (cell.getCol > currentCol)
              cell.clicked = false
          }
        case _ =>
      }
    }

    Option.empty
  }

  this.setLayout(new GridLayout(numRows, numCols))
  this.setPreferredSize(new Dimension(width, numberOfNotes * cellHeight))
  createCells()

  private def convertIntToNote(pitch: Int): String = {

    val reversed = highestPitch - pitch
    val octave = (reversed / 12) - 1
    val noteNames = Array("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")
    val noteName = noteNames(reversed % 12)

    s"$noteName$octave"
  }

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)

    val g2d = g.asInstanceOf[Graphics2D]

    g2d.setColor(Color.WHITE)
    g2d.fillRect(0, 0, getWidth, getHeight)
  }
}
