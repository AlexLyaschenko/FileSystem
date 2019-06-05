import java.util.ArrayList;

public class lDisk {

    public static final int cylindersAmount = 4;
    public static final int bytesAmount = cylindersAmount * cylinder.bytesAmount;

    public ArrayList<cylinder> cylinders;

    lDisk()
    {
        cylinders= new ArrayList<>();
        cylinders.ensureCapacity(cylindersAmount);
        for (int i = 0; i < cylindersAmount; i++)
        {
            cylinders.add(new cylinder());
        }
    }
}
