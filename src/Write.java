import java.util.ArrayList;

public class Write {

    static void writeFile(ArrayList<Character> writeInfo, IOSys testSys) {
        int blockId = Bitmap.findFreeBlock(testSys);

        if (blockId != -1) {
            ArrayList<Character> writeToBlock = new ArrayList<>();
            for (int i = 0; i < writeInfo.size(); i++) {
                writeToBlock.add(writeInfo.get(i));
                if (i == writeInfo.size() -1)
                    while (writeToBlock.size() < 64) {
                        writeToBlock.add(' ');
                    }
                if ((i > 0 && (i + 1) % 64 == 0) || writeInfo.size() == i + 1) {
                    //System.out.println(writeInfo.size());

                    //System.out.println(writeInfo.size());
                    //System.out.println(blockId);
                    testSys.writeBlock(blockId, writeToBlock);
                    Bitmap.writeToBitmap(testSys, blockId);
                    File.blocksNum.add(blockId);
                    blockId = Bitmap.findFreeBlock(testSys);


                    writeToBlock = new ArrayList<>();
                }
            }
            Directory.changeDescriptor(testSys);
        } else {
            System.out.println("There is no free space");
        }
    }
}
