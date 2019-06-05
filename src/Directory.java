import java.util.ArrayList;

public class Directory {
    private static ArrayList<ArrayList> arr = new ArrayList<>();

    static void addToDirectory(String filename, int descriptorIndex, IOSys testSys) {
        Bitmap.writeToBitmap(testSys, descriptorIndex);
        for (int i = Main.directoryStart; i <= Main.directoryEnd; i++) {
            ArrayList<Character> directoryArr = testSys.readBlock(i);
            for (int j = 0; j < 4; j++) {
                ArrayList<Character> checkDir = new ArrayList<>();
                if (directoryArr.get(j * 16) == ' ') {
                    for (int k = 0; k < 16; k++) {
                        checkDir.add(' ');
                    }
                    char[] filenameChr = filename.toCharArray();
                    char[] descriptorIndexChr = Integer.toString(descriptorIndex).toCharArray();
                    for (int k = j * 16, y = 0; k < j * 16 + filenameChr.length; k++, y++) {
                        directoryArr.set(k, filenameChr[y]);
                    }
                    for (int k = j * 16 + 13, y = 0; k < j * 16 + 13 + descriptorIndexChr.length; k++, y++) {
                        directoryArr.set(k, descriptorIndexChr[y]);
                    }
                    testSys.writeBlock(i, directoryArr);
                    return;
                }
            }

        }
        System.out.println("Error while adding to directory");
    }


    static int showDescriptor(String filename, IOSys testSys) {
        for (int i = Main.directoryStart; i <= Main.directoryEnd; i++) {
            ArrayList<Character> directoryArr = testSys.readBlock(i);
            for (int j = 0; j < 4; j++) {
                ArrayList<Character> checkDir = new ArrayList<>();
                if (directoryArr.get(j * 16) != ' ') {
                    for (int k = 0; k < 16; k++) {
                        checkDir.add(' ');
                    }

                    String s = "";
                    String p = "";
                    ArrayList<Character> filenameArr = new ArrayList<>();
                    ArrayList<Character> descriptorIndexArr = new ArrayList<>();
                    for (int k = j * 16; k < j * 16 + 13; k++) {
                        if (directoryArr.get(k) == ' ') break;
                        filenameArr.add(directoryArr.get(k));
                        s+=directoryArr.get(k);
                    }
                    for (int k = j * 16 + 13; k < j * 16 + 16; k++) {
                        if (directoryArr.get(k) == ' ') break;
                        p+=directoryArr.get(k);
                        descriptorIndexArr.add(directoryArr.get(k));
                    }
                    if (s.equals(filename)) {
                        Bitmap.writeToBitmap(testSys,j + 1);
                        return Integer.valueOf(p);
                    }
                    String filenameInDir = filenameArr.toString();
                    String descriptorIndexStr = descriptorIndexArr.toString();
                    if (filenameInDir.equals(filename)) {
                        return Integer.valueOf(descriptorIndexStr);
                    }
                }
            }
        }
        return -1;
    }

    static void changeDescriptor(IOSys testSys) {
        ArrayList<Character> changeDescriptor = testSys.readBlock(File.descriptorId);
        ArrayList<Integer> newBlocksNumInt = File.blocksNum;
        ArrayList<String> newBlocksNumStr = new ArrayList<>();
        for (Integer i:newBlocksNumInt) {
            newBlocksNumStr.add(i.toString());
        }
        String s = String.join(",", newBlocksNumStr);
        for (int i = 30, j = 0; i < 30 + s.length(); i++, j++) {
            changeDescriptor.set(i, s.charAt(j));
        }
        testSys.writeBlock(File.descriptorId, changeDescriptor);
    }

    static int showDescriptor(String filename, IOSys testSys, boolean b) {
        for (int i = Main.directoryStart; i <= Main.directoryEnd; i++) {
            ArrayList<Character> directoryArr = testSys.readBlock(i);
            for (int j = 0; j < 4; j++) {
                ArrayList<Character> checkDir = new ArrayList<>();
                if (directoryArr.get(j * 16) != ' ') {
                    for (int k = 0; k < 16; k++) {
                        checkDir.add(' ');
                    }

                    String s = "";
                    String p = "";
                    ArrayList<Character> filenameArr = new ArrayList<>();
                    ArrayList<Character> descriptorIndexArr = new ArrayList<>();
                    for (int k = j * 16; k < j * 16 + 13; k++) {
                        if (directoryArr.get(k) == ' ') break;
                        filenameArr.add(directoryArr.get(k));
                        s+=directoryArr.get(k);
                    }
                    for (int k = j * 16 + 13; k < j * 16 + 16; k++) {
                        if (directoryArr.get(k) == ' ') break;
                        p+=directoryArr.get(k);
                        descriptorIndexArr.add(directoryArr.get(k));
                    }
                    if (s.equals(filename)) {
                        for (int k = j * 16; k < j * 16 + 16; k++) {
                            directoryArr.set(k, ' ');
                        }
                        testSys.writeBlock(i, directoryArr);
                        Bitmap.writeToBitmap(testSys,j + 1);
                        return Integer.valueOf(p);
                    }
                    String filenameInDir = filenameArr.toString();
                    String descriptorIndexStr = descriptorIndexArr.toString();
                    if (filenameInDir.equals(filename)) {
                        return Integer.valueOf(descriptorIndexStr);
                    }
                }
            }
        }
        return -1;
    }

}
