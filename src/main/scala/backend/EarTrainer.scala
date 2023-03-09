package backend

import javax.sound.midi.MidiSystem
import scala.util.Random

class EarTrainer(config: Configuration) extends Client {

  private var currentPitch = config.lowestPitch
  private val synth = MidiSystem.getSynthesizer

  synth.open()

  private val insts = synth.getDefaultSoundbank.getInstruments
  private val channels = synth.getChannels

  synth.loadInstrument(insts(0))

  def getExpectedPitch: Int = {
    currentPitch
  }

  def generateNextPitch : Int = {

    val rand = new Random()
    currentPitch = rand.between(config.lowestPitch, config.highestPitch + 1)

    currentPitch
  }

  def checkAnswer(pitch: Int): Boolean = pitch == this.currentPitch

  def playNote(pitch: Int): Unit = {
    channels(0).noteOn(pitch, 100)
  }

  def stopNote(): Unit = {
    channels(0).noteOff(currentPitch)
  }

}
