package frontend

import java.awt.event.{MouseAdapter, MouseEvent}
import java.awt.{Color, Dimension, Graphics, Graphics2D}
import javax.swing.JPanel

enum CellState:
  case NON_CLICKED, RECLICKED, CLICKED

class Cell(col : Int, row: Int, width: Int, height: Int) extends JPanel {

  private var color = Color.LIGHT_GRAY

  var label : String = _

  def getCol: Int = {
    col
  }

  def getRow: Int = {
    row
  }

  this.setPreferredSize(new Dimension(width, height))
  var state : CellState = CellState.NON_CLICKED

  addMouseListener(new MouseAdapter {
    override def mouseClicked(e: MouseEvent): Unit = {
      if (state == CellState.NON_CLICKED)
        state = CellState.CLICKED
      else if (state == CellState.CLICKED) {
        state = CellState.RECLICKED
      }
    }})

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)

    val g2d = g.asInstanceOf[Graphics2D]

    g2d.setColor(Color.BLACK)

    if (col == 0) {
      g2d.drawString(label, 0, height)
    }

    g2d.setColor(this.color)
    if (state == CellState.CLICKED) {
      g2d.fillRect(0, 0, width, height)
    } else {
      g2d.drawRect(0, 0, width, height)
    }
  }

  def markAsFailed() : Unit = {
    val g2d = getGraphics.asInstanceOf[Graphics2D]

    this.color = Color.RED
    g2d.setColor(this.color)
    g2d.fillRect(0, 0, width, height)
  }

  def markAsSuccessful(): Unit = {
    val g2d = getGraphics.asInstanceOf[Graphics2D]

    this.color = Color.GREEN
    g2d.setColor(this.color)
    g2d.fillRect(0, 0, width, height)
  }

  def markAsExpected(): Unit = {
    val g2d = getGraphics.asInstanceOf[Graphics2D]

    this.color = Color.DARK_GRAY
    g2d.setColor(this.color)
    g2d.fillRect(0, 0, width, height)
  }

}
