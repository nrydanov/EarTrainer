package frontend

import java.awt.{Color, Graphics, Graphics2D}
import javax.swing.JButton

class Note(x: Int, y: Int) extends JButton {
  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)

    val g2d = g.asInstanceOf[Graphics2D]

    g2d.setColor(Color.BLUE)

    g2d.fillRect(x, y, 200, 20)
  }
}
