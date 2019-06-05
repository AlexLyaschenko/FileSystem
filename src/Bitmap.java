import java.util.ArrayList;

public class Bitmap {
    private static ArrayList<Character> occupiedBlocks;

    static int findFreeBlock(IOSys testSys) {
        occupiedBlocks = testSys.readBlock(0);
        for (int i = Main.fileStart; i < 64; i++) {
            if (occupiedBlocks.get(i) == '0') {
                return i;
            }
        }
        return -1;
    }

    static int findFreeDescriptor(IOSys testSys) {
        for (int i = Main.descriptorStart; i <= Main.descriptorEnd; i++) {
            occupiedBlocks = testSys.readBlock(i);
            if (occupiedBlocks.get(0) == ' ') {
                return i;
            }
        }
        return -1;
    }

    static void writeToBitmap(IOSys testSys, int index) {
        ArrayList<Character> rewrite = testSys.readBlock(0);
        rewrite.set(index, '1');
        testSys.writeBlock(0, rewrite);
    }
    static void writeToBitmap(IOSys testSys, int index, boolean b) {
        ArrayList<Character> rewrite = testSys.readBlock(0);
        if (index > -1){
        rewrite.set(index, '0');
        testSys.writeBlock(0, rewrite);
    }}
}
