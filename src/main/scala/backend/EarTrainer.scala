package backend

import javax.sound.midi.MidiSystem
import scala.util.Random


class EarTrainer(config: Configuration) {

  private var current_pitch = config.lowest_pitch

  def generateNextPitch(): Int = {

    val rand = new Random()

    val current_pitch = rand.between(config.lowest_pitch, config.highest_pitch + 1)

    this.current_pitch = current_pitch

    current_pitch
  }

  def checkAnswer(pitch: Int): Boolean = pitch == this.current_pitch

  def playNote(pitch: Int): Unit = {
    val synth = MidiSystem.getSynthesizer
    synth.open()

    val insts = synth.getDefaultSoundbank.getInstruments

    val channels = synth.getChannels

    synth.loadInstrument(insts(0))

    channels(0).noteOn(pitch, 100)
  }


}
