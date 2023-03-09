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
        pr.correct()
      } else {
        logger.info("Answer is incorrect")
        pr.failed(trainer.getExpectedPitch())
      }
      trainer.stopNote()
      trainer.playNote(trainer.generateNextPitch())
    }
  }

  def main(args: Array[String]): Unit = {

    val f = new JFrame("Ear trainer")
    System.setProperty("sun.java2d.opengl", "true")
    val config = Configuration.getDefaultConfiguration

    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    f.setSize(config.width, config.height)

    val pr = new PianoRoll(f.getWidth, f.getHeight, config.lowestPitch, config.highestPitch)

    val sp = new JScrollPane(pr)
    sp.getViewport.addChangeListener(_ => pr.repaint())
    sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS)
    sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS)
    f.getContentPane.add(sp)

    sp.setLocation(120, 0)

    f.setVisible(true)

    val trainer = new EarTrainer(Configuration.getDefaultConfiguration)
    trainer.playNote(trainer.generateNextPitch())

    val executor = new ScheduledThreadPoolExecutor(1)
    val task = new Runnable {
      override def run(): Unit = updateState(pr, trainer)
    }

    val _ = executor.scheduleWithFixedDelay(task, 0, 10, TimeUnit.MILLISECONDS)
  }
}