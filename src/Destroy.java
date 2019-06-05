import java.util.ArrayList;

public class Destroy {

    static void delAllFile(String filename, IOSys testSys) {
        if (Directory.showDescriptor(filename, testSys) == -1) {
            System.out.println("File already deleted");
            return;
        }
        int i = Directory.showDescriptor(filename, testSys, false);
        String blocksNumStr = "";
        ArrayList<Character> descriptor = testSys.readBlock(i);
        for (int j = 30; j < descriptor.size(); j++) {
            blocksNumStr += (descriptor.get(j));
        }
        String[] blocksNumStrArr = blocksNumStr.split(",");
        for (int j = 0; j < blocksNumStrArr.length; j++) {
            try {
                int c = Integer.valueOf(blocksNumStrArr[j].replace(" ", ""));
                Bitmap.writeToBitmap(testSys, c, false);

            }
            catch (Exception e) {
                continue;
            }
        }
        ArrayList<Character> newDescriptor = new ArrayList<>();
        for (int j = 0; j < 64; j++)
            newDescriptor.add(' ');
        testSys.writeBlock(i, newDescriptor);
        //Bitmap.writeToBitmap(testSys, File.descriptorId, false);
        //Bitmap.writeToBitmap(testSys, i, false);

        for (int q = 1; q < 6; q++) {
            boolean a = false;
            ArrayList<Character> s = testSys.readBlock(q);
            for (int j = 0; j < 64; j++) {
                if (s.get(j) != ' ')a = true;
            }
            if (!a)
            Bitmap.writeToBitmap(testSys, q, false);
        }
        for (int q = 6; q < 21; q++) {
            boolean a = false;
            ArrayList<Character> s = testSys.readBlock(q);
            for (int j = 0; j < 64; j++) {
                if (s.get(j) != ' ')a = true;
            }
            if (!a)
                Bitmap.writeToBitmap(testSys, q, false);
        }
    }
}
