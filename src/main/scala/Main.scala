import backend.{Configuration, EarTrainer}
import com.typesafe.scalalogging.Logger
import frontend.PianoRoll

import java.util.concurrent.{ScheduledThreadPoolExecutor, TimeUnit}
import javax.swing.{JFrame, JScrollPane, ScrollPaneConstants, WindowConstants}

object Main {

  private val logger: Logger = Logger("logger")

  private def updateState(pr: PianoRoll, trainer: EarTrainer): Unit = {
    val code = pr.getAnswer
    if (code.nonEmpty) {
      logger.info(s"Front-end returned answer: ${code.get}")
      if (trainer.checkAnswer(code.get)) {
        logger.info("Answer is correct")
        pr.correct(code.get)
      } else {
        logger.info("Answer is incorrect")
        pr.failed(code.get)
      }
      trainer.stopNote()
      trainer.playNote(trainer.generateNextPitch())
    }
  }

  def main(args: Array[String]): Unit = {

    val f = new JFrame("Ear trainer")
    System.setProperty("sun.java2d.opengl", "true")
    val config = Configuration.getDefaultConfiguration()

    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    f.setSize(1280, 720)

    val pr = new PianoRoll(0, f.getY, f.getWidth, config.lowest_pitch, config.highest_pitch)

    val sp = new JScrollPane(pr)
    sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS)
    sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS)
    f.getContentPane.add(sp)

    f.setVisible(true)



    val trainer = new EarTrainer(Configuration.getDefaultConfiguration())

    trainer.playNote(trainer.generateNextPitch())

    val executor = new ScheduledThreadPoolExecutor(1)

    val task = new Runnable {
      override def run(): Unit = updateState(pr, trainer)
    }

    val _ = executor.scheduleAtFixedRate(task, 0, 50, TimeUnit.MILLISECONDS)
  }
}