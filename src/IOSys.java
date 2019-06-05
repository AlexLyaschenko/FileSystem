import java.util.ArrayList;

public class IOSys {

    private
    lDisk ldisk;

	private final int blockLength = 64;
    final int blocksAmount = lDisk.bytesAmount / blockLength;
    private final int blocksAmountInCylinder = cylinder.bytesAmount / blockLength;
    private final int blocksAmountInTrack = track.bytesAmount / blockLength;
    private final int blocksAmountInSector = sector.bytesAmount / blockLength;

    public
    IOSys(lDisk ldisk)
    {
        this.ldisk = ldisk;
    }

    private ArrayList<Integer> getBlockLocation(int blockNumber) {
        //Firstly searching cylinder number
        int cylinderNumber = -1; //undefined firstly
        for (int i = 1; i <= lDisk.cylindersAmount; i++)
        {
            if (blockNumber < i * blocksAmountInCylinder)
            {
                cylinderNumber = i - 1;
                break;
            }
        }

        if (cylinderNumber == -1) //Хз как в джаве делать это
        {
            //std::runtime_error("Cannot find cylinder number for block number" + blockNumber);
        }

        int blockNumberInCylinder = blockNumber % blocksAmountInCylinder;
        //Searching track number
        int trackNumber = -1;
        for (int i = 1; i <= cylinder.tracksAmount; i++)
        {
            if (blockNumberInCylinder < i * blocksAmountInTrack)
            {
                trackNumber = i - 1;
                break;
            }
        }

        if (trackNumber == -1)
        {
            //std::runtime_error("Cannot find track number for block number" + blockNumber);
        }

        int blockNumberInTrack = blockNumberInCylinder % blocksAmountInTrack;

        //Searching actually block(sector) number
        int sectorNumber = -1;

        for (int i = 1; i <= track.sectorsAmount; i++)
        {
            if (blockNumberInTrack < i * blocksAmountInSector)
            {
                sectorNumber = i - 1;
                break;
            }
        }

        if (sectorNumber == -1)
        {
            //std::runtime_error("Cannot find sector number for block number" + blockNumber);
        }

        ArrayList<Integer> result = new ArrayList<>();
        result.ensureCapacity(3);
        result.add(cylinderNumber);
        result.add(trackNumber);
        result.add(sectorNumber);

        return result;
    }


    public

    ArrayList<Character> readBlock(int blockNumber)
    {
        ArrayList<Character> destination = new ArrayList<>();

        if (blockNumber < 0 || blockNumber >= blocksAmount)
        {
            //std::runtime_error("Block number " + std::to_string(blockNumber) + " should be >= 0 and < " + std::to_string(blocksAmount));
        }

        ArrayList<Integer> blockLocation = getBlockLocation(blockNumber);

        for (int i = 0; i < blockLength; i++)
        {
            cylinder c = ldisk.cylinders.get(blockLocation.get(0));
            track t = c.tracks.get(blockLocation.get(1));
            sector s = t.sectors.get(blockLocation.get(2));
            Character ch = s.bytes.get(i);

            destination.add(ch);

            //destination.set(i, ldisk.cylinders.get(blockLocation.get(0)).tracks.get(blockLocation.get(1)).sectors.get(blockLocation.get(2)).bytes.get(i));
        }
        return destination;
    }


    void writeBlock(int blockNumber, ArrayList<Character> bytes)
    {
        if (blockNumber < 0 || blockNumber >= blocksAmount)
        {
            //std::runtime_error("Block number " + std::to_string(blockNumber) + " should be >= 0 and < " + std::to_string(blocksAmount));
        }

        ArrayList<Integer> blockLocation = getBlockLocation(blockNumber);

        for (int i = 0; i < blockLength; i++)
        {
            cylinder c = ldisk.cylinders.get(blockLocation.get(0));
            track t = c.tracks.get(blockLocation.get(1));
            sector s = t.sectors.get(blockLocation.get(2));
            s.bytes.set(i, bytes.get(i));

            //ldisk.cylinders[blockLocation[0]].tracks[blockLocation[1]].sectors[blockLocation[2]].bytes[i] = bytes[i];
            ldisk.cylinders.get(blockLocation.get(0)).tracks.get(blockLocation.get(1)).sectors.get(blockLocation.get(2)).bytes.set(i, bytes.get(i));
        }
    }

    //getters for file system
    int getBlockLength() {
        return blockLength;
    }
    int getBlockCount() {
        return blocksAmount;
    }

}
