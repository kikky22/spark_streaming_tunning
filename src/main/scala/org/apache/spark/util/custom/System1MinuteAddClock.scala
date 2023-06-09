package org.apache.spark.util.custom

import org.apache.spark.util.Clock

/**
 * A clock backed by the actual time from the OS as reported by the `System` API.
 *
 * spark.streaming.clock = org.apache.spark.util.custom.System1MinuteAddClock Setting
 */
private[spark] class System1MinuteAddClock extends Clock {

  val minPollTime = 25L

  /**
   * @return the same time (milliseconds since the epoch)
   *         as is reported by `System.currentTimeMillis()`
   */
  override def getTimeMillis(): Long = System.currentTimeMillis() - 60000

  /**
   * @param targetTime block until the current time is at least this value
   * @return current system time when wait has completed
   */
  override def waitTillTime(targetTime: Long): Long = {
    var currentTime = System.currentTimeMillis() - 60000

    var waitTime = targetTime - currentTime
    if (waitTime <= 0) {
      return currentTime
    }

    val pollTime = math.max(waitTime / 10.0, minPollTime).toLong

    while (true) {
      currentTime = System.currentTimeMillis() - 60000
      waitTime = targetTime - currentTime
      if (waitTime <= 0) {
        return currentTime
      }
      val sleepTime = math.min(waitTime, pollTime)
      Thread.sleep(sleepTime)
    }
    -1
  }
}
