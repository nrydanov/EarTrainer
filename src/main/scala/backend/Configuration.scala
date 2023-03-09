package backend

trait ConfigurationTrait:
  def getDefaultConfiguration: Configuration

case class Configuration(lowestPitch: Int, highestPitch: Int, width: Int, height: Int)

object Configuration extends ConfigurationTrait {
  def getDefaultConfiguration: Configuration = {
    new Configuration(
      48,
      60,
      1280,
      720)
  }
}
