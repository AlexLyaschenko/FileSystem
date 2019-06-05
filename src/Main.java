import java.util.ArrayList;

public class Main {
    static final int bitmapNum = 0;
    static final int directoryStart = 1;
    static final int directoryEnd = 5;
    static final int descriptorStart = 6;
    static final int descriptorEnd = 20;
    static final int fileStart = 21;
    static final int sectorSize = 64;

    public static void main(String[] args) {

        lDisk ld = new lDisk();
        IOSys testSys = new IOSys(ld);
        ArrayList<Character> c = new ArrayList<>();
        c.add('f');
        ArrayList<Character> bitmap = new ArrayList<>();
        bitmap.add('1');
        for (int i = 1; i < 64; i++) {
            bitmap.add('0');
        }
        testSys.writeBlock(0, bitmap);
        print(testSys.readBlock(0));
        Open.openFile("File1", testSys);
        Write.writeFile(bitmap, testSys);
        Lseek.changePos(63);
        print(Read.readFile(123, 21, testSys));

    }

    static void print(ArrayList<Character> c) {
        for (char i : c) {
            System.out.print(i);
        }
        System.out.println();
    }

    static void printFile() {
        System.out.println(File.blocksNum);
        System.out.println(File.descriptorId);
        System.out.println(File.currentNum);
        System.out.println(File.filename);
        System.out.println(File.firstBlock);
        System.out.println(File.fileLength);
    }
}
