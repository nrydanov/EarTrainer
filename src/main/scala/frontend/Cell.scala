package frontend

import java.awt.{Color, Dimension, Graphics, Graphics2D}
import javax.swing.JPanel

class Cell(x : Int, y: Int, width: Int, height: Int) extends JPanel {

  this.setPreferredSize(new Dimension(width, height))

  override def paintComponent(g: Graphics): Unit = {

    val g2d = g.asInstanceOf[Graphics2D]

    g2d.setColor(Color.LIGHT_GRAY)
    g2d.drawRect(0, 0, width, height)
  }
}
