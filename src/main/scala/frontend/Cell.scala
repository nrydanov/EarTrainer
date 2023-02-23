package frontend

import java.awt.{Color, Graphics, Graphics2D}
import javax.swing.JPanel

class Cell(x : Int, y: Int, width: Int, height: Int) extends JPanel {
  override def paintComponent(g: Graphics): Unit = {
//    super.paintComponent(g)

    val g2d = g.asInstanceOf[Graphics2D]

    g2d.setColor(Color.LIGHT_GRAY)
    g2d.fillRect(x, y, width, height)
  }
}
