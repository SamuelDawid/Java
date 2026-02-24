import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Movie {
    String title;
    int time;

    public Movie(String title, int time) {
        this.title = title;
        this.time = time;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
