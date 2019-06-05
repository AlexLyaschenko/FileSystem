import java.util.ArrayList;

public class track {

    public static final int sectorsAmount = 8;
    public static final int bytesAmount = sectorsAmount * sector.bytesAmount;

    public ArrayList<sector> sectors;

    track()
    {
        sectors = new ArrayList<>();
        sectors.ensureCapacity(sectorsAmount);
        for (int i = 0; i < sectorsAmount; i++)
        {
            sectors.add(new sector());
        }
    }
}
