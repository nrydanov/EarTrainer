package backend

case class Configuration(lowest_pitch: Int, highest_pitch: Int)

object Configuration {
  def getDefaultConfiguration(): Configuration = {
    new Configuration(21, 100)
  }
}
