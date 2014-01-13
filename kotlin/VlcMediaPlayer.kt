/**
 * Created by ivan on 1/11/14.
 */
import uk.co.caprica.vlcj.player.MediaPlayerFactory
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer
import uk.co.caprica.vlcj.runtime.RuntimeUtil
import uk.co.caprica.vlcj.binding.LibVlc
import com.sun.jna.NativeLibrary
import com.sun.jna.Native
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter
import uk.co.caprica.vlcj.player.MediaPlayer

object VlcMediaPlayer{
    val mediaListPlayer: EmbeddedMediaPlayer;
    val factory: MediaPlayerFactory;
    {

        val osName = System.getProperty("os.name")?.toLowerCase()
        println(osName)
        val libPath = when(osName) {
            "linux" -> "/usr/lib/vlc"
            "mac os x" -> "/Applications/VLC.app/Contents/MacOS/lib"

            else -> {
                throw RuntimeException("Platform not supported $osName")
            }
        }

        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), libPath)


        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), javaClass<LibVlc>())

        factory = MediaPlayerFactory()
        mediaListPlayer = factory?.newEmbeddedMediaPlayer()!!
        mediaListPlayer.addMediaPlayerEventListener(EventsListener())
    }




    class EventsListener : MediaPlayerEventAdapter(){
        override fun positionChanged(mediaPlayer: MediaPlayer?, newPosition: Float) {
            Core.onSeek(newPosition)
        }
        override fun seekableChanged(mediaPlayer: MediaPlayer?, newSeekable: Int) {
            println("seekableChanged")
        }
    }

    fun play(path: String) {
        println("Try to play $path")
        if (!path.endsWith(".mp3")) {
            //return;
        }

        mediaListPlayer.playMedia(path)

        println("length = ${mediaListPlayer.getLength()}")
        println("isSeekable = ${mediaListPlayer.isSeekable()}")
    }

    fun setVolume(value: Int) {
        mediaListPlayer.setVolume(value)
    }

    fun play() {
        mediaListPlayer.play()
    }
    fun seek(value: Float) {
        println("seek $value")
        mediaListPlayer.setPosition(value)
    }

    fun stop() {
        mediaListPlayer.stop()
    }

    fun pause() {
        mediaListPlayer.pause()
    }
    fun release() {
        mediaListPlayer.release()
        factory.release()
    }
}