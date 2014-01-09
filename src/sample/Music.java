package sample;

/**
 * Created by ivanivanenko on 1/9/14.
 */
public class Music {
    private String text;
    private String path;

    public Music(String text, String path) {
        this.text = text;
        this.path = path;
    }

    @Override
    public String toString() {
        return text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
