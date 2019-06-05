import java.util.ArrayList;

public class cylinder {

    public static final int tracksAmount = 3;
    public static final int bytesAmount = tracksAmount * track.bytesAmount;

    public ArrayList<track> tracks;

    cylinder()
    {
        tracks = new ArrayList<>();
        tracks.ensureCapacity(tracksAmount);
        for (int i = 0; i < tracksAmount; i++)
        {
            tracks.add(new track());
        }
    }
}
