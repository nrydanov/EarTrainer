package backend

import javax.sound.midi.MidiSystem
import scala.util.Random


class EarTrainer(config: Configuration) {

  private var current_pitch = config.lowest_pitch

  private val synth = MidiSystem.getSynthesizer

  synth.open()

  private val insts = synth.getDefaultSoundbank.getInstruments

  private val channels = synth.getChannels

  synth.loadInstrument(insts(0))

  def generateNextPitch(): Int = {

    val rand = new Random()

    current_pitch += 1

    current_pitch
  }

  def checkAnswer(pitch: Int): Boolean = pitch == this.current_pitch

  def playNote(pitch: Int): Unit = {
    channels(0).noteOn(pitch, 100)
  }

  def stopNote(): Unit = {
    channels(0).noteOff(current_pitch)
  }


}
