import java.io.CharArrayReader;
import java.util.ArrayList;

public class Read {
    static ArrayList<Character> readFile(int count, int mem_area, IOSys testSys) {

        int countGet = 0;
        ArrayList<Character> infoFromFile = new ArrayList<>();
        ArrayList<Integer> blocksNumRemain = new ArrayList<>();
        for (int i = 0, j = 0; i < File.blocksNum.size(); i++) {
            if (mem_area == File.blocksNum.get(i)) j++;
            if (j != 0) {
                blocksNumRemain.add(File.blocksNum.get(i));
            }
        }

        if (blocksNumRemain.size() != 0) {
            for (int i : blocksNumRemain) {
                ArrayList<Character> infoFromBlock = testSys.readBlock(i);
                for (int j = File.pos; j < infoFromBlock.size(); j++, countGet++) {
                    if (countGet == count) {
                        break;}
                    if (infoFromBlock.get(j) == ' ') break;
                    infoFromFile.add(infoFromBlock.get(j));
                }
                if (countGet == count) {
                    break;}
            }

            if (count != countGet) System.out.println("File read to the end");
            return infoFromFile;

        } else {
            System.out.println("There  is no block with file in mem_area");
            return null;
        }
    }
}
