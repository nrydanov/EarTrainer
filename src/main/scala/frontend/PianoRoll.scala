package frontend

import java.awt.{Color, Dimension, Graphics, Graphics2D}
import javax.swing.JPanel

class PianoRoll(x_left: Int, y_bottom: Int, width: Int, lowest_pitch: Int, highest_pitch: Int) extends JPanel {

  private val number_of_notes = highest_pitch - lowest_pitch + 1
  private val cellWidth = 60
  private val cellHeight = 20
  private val numRows = number_of_notes
  private val numCols = width / cellWidth

  this.setPreferredSize(new Dimension(width, number_of_notes * cellHeight))

  private def convertIntToNote(value: Int): String = {

    val octave = (value / 12) - 1
    val noteNames = Array("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")
    val noteName = noteNames(value % 12)

    s"$noteName$octave"
  }

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)

    val g2d = g.asInstanceOf[Graphics2D]

    g2d.setColor(Color.WHITE)
    g2d.fillRect(x_left, y_bottom, getWidth, getHeight)

    g2d.setColor(Color.LIGHT_GRAY)
    for (row <- 0 until numRows; col <- 0 until numCols) {
      g2d.drawRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight)
      if (col == 0) {
        val noteString = convertIntToNote(highest_pitch - row)
        g2d.setColor(Color.BLACK)
        g2d.drawString(noteString, col * cellWidth, row * cellHeight)
        g2d.setColor(Color.LIGHT_GRAY)
      }
    }
  }
}
