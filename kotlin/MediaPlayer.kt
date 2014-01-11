/**
 * Created by ivan on 1/11/14.
 */
import uk.co.caprica.vlcj.player.MediaPlayerFactory
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer
import uk.co.caprica.vlcj.runtime.RuntimeUtil
import uk.co.caprica.vlcj.binding.LibVlc
import com.sun.jna.NativeLibrary
import com.sun.jna.Native

object MediaPlayer{
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
    }

    fun play(path: String) {
        if (!path.endsWith(".mp3")) {
            println("Format not support")
            return;
        }
        mediaListPlayer.playMedia(path)
    }

    fun setVolume(value:Int){
        mediaListPlayer.setVolume(value)
    }

    fun play() {
        mediaListPlayer.play()
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