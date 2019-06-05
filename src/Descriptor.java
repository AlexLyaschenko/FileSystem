import java.util.ArrayList;

public class Descriptor {


    static void findInfoAboutFile(int descriptorId, IOSys testSys) {
        ArrayList<Character> infoAboutFile = testSys.readBlock(descriptorId);
        String filenameArr = "";
        String fileLengthArr = "";
        String fileIdArr = "";
        String blocksNumArr = "";

        for (int i = 0; i < 15; i++) {
            if (infoAboutFile.get(i) == ' ') break;
            filenameArr+=(infoAboutFile.get(i));
        }
        for (int i = 15; i < 25; i++) {
            if (infoAboutFile.get(i) == ' ') break;
            fileLengthArr+=(infoAboutFile.get(i));
        }
        for (int i = 25; i < 30; i++) {
            if (infoAboutFile.get(i) == ' ') break;
            fileIdArr+=(infoAboutFile.get(i));
        }
        for (int i = 30; i < 64; i++) {
            if (infoAboutFile.get(i) == ' ') break;
            blocksNumArr+=(infoAboutFile.get(i));
        }
        if (blocksNumArr.length() == 0) {
            System.out.println("File is empty");
        }
        String filename = filenameArr.toString();
        int fileLength = -1;
        if (fileLengthArr.length() != 0) fileLength = Integer.valueOf(fileLengthArr.toString());
        int fileId = -1;
        if (fileIdArr.length() != 0) fileId = Integer.valueOf(fileIdArr.toString());
        String[] blocksNumStr = blocksNumArr.toString().split(",");
        ArrayList<Integer> blocksNum = new ArrayList<>();
        if (blocksNumStr[0] != "")
            for (String s:blocksNumStr) blocksNum.add(Integer.valueOf(s));

        File.filename = filename;
        //File.id = fileId;
        File.descriptorId = descriptorId;
        File.fileLength = fileLength;
        File.blocksNum = blocksNum;
        if (blocksNumArr.length() == 0) {
            File.firstBlock = null;
            File.currentNum = -1;
            return;
        }
        File.firstBlock = testSys.readBlock(blocksNum.get(0));
        File.currentNum = blocksNum.get(0);
        //File.directoryId = directoryId;
    }
}
