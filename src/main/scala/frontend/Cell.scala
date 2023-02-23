package frontend

import com.typesafe.scalalogging.Logger

import java.awt.event.{MouseAdapter, MouseEvent}
import java.awt.{Color, Dimension, Graphics, Graphics2D}
import javax.swing.JPanel

class Cell(col : Int, row: Int, width: Int, height: Int) extends JPanel {

  private val logger = Logger("logger")

  private var color = Color.LIGHT_GRAY

  private var chosenPitch : Int = _

  def getCol: Int = {
    col
  }

  def getRow: Int = {
    row
  }

  this.setPreferredSize(new Dimension(width, height))

  var clicked = false

  addMouseListener(new MouseAdapter {
    override def mouseClicked(e: MouseEvent): Unit = {
      logger.info(s"Cell $col, $row got click")
      clicked = true
    }
  })

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)

    val g2d = g.asInstanceOf[Graphics2D]

    g2d.setColor(this.color)
    g2d.drawRect(0, 0, width, height)
  }

  def memorizePitch(pitch: Int): Unit = {
    this.chosenPitch = pitch
  }

  def markAsFailed(pitch: Int): Unit = {
    val g2d = getGraphics.asInstanceOf[Graphics2D]

    this.color = Color.RED
    g2d.setColor(this.color)
    g2d.fillRect(0, 0, width, height)

    memorizePitch(pitch)

  }

  def markAsSuccessful(pitch: Int): Unit = {
    val g2d = getGraphics.asInstanceOf[Graphics2D]

    this.color = Color.GREEN
    g2d.setColor(this.color)
    g2d.fillRect(0, 0, width, height)

    memorizePitch(pitch)
  }

}
