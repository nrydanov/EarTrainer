import backend.{Configuration, EarTrainer}
import frontend.PianoRoll

import javax.swing.{JFrame, JScrollPane, ScrollPaneConstants, WindowConstants}

object Main {



  def main(args: Array[String]): Unit = {

    val f = new JFrame("Ear trainer")

    System.setProperty("sun.java2d.opengl", "true")

    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

    val trainer = new EarTrainer(Configuration.getDefaultConfiguration())

    trainer.playNote(trainer.generateNextPitch())

    f.setSize(1280, 720)

    val pr = new PianoRoll(0, f.getY, f.getWidth, 21, 127)

    val sp = new JScrollPane(pr)

    sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS)
    sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS)

    f.getContentPane.add(sp)

    f.setVisible(true)
  }
}