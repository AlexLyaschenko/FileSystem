import java.util.ArrayList;

public class Create {
    static private int id = 1;

//    Create(String filename, IOSys testSys) {
//        if (filename.length() > 15) {
//            System.out.println("Too big name");
//        } else if (Directory.showDescriptor(filename, testSys) == -1) {
//            int freeBlockIndex = Bitmap.findFreeBlock(testSys);
//            if (freeBlockIndex != -1) {
//                int freeDescriptorIndex = Bitmap.findFreeDescriptor(testSys);
//                if (freeDescriptorIndex != -1) {
//                    createFile(filename, freeBlockIndex);
//                    writeToDirectory(filename, freeDescriptorIndex, testSys);
//                    //writeToBitmap(testSys, freeBlockIndex);
//                    writeToDescriptor(filename, freeDescriptorIndex, testSys, id);
//                    for (int i = 1; i < 6; i++) {
//                        ArrayList<Character> s = testSys.readBlock(i);
//                        if (s.get(0) != ' ') Bitmap.writeToBitmap(testSys, i);
//                    }
//                } else {
//                    System.out.println("no free directory");
//                }
//            } else {
//                System.out.println("no free space");
//            }
//        } else {
//            System.out.println("file already exists");
//        }
//    }

    static void create(String filename, IOSys testSys) {
        if (filename.length() > 15) {
            System.out.println("Too big name");
        } else if (Directory.showDescriptor(filename, testSys) == -1) {
            int freeBlockIndex = Bitmap.findFreeBlock(testSys);
            if (freeBlockIndex != -1) {
                int freeDescriptorIndex = Bitmap.findFreeDescriptor(testSys);
                if (freeDescriptorIndex != -1) {
                    createFile(filename, freeBlockIndex);
                    writeToDirectory(filename, freeDescriptorIndex, testSys);
                    //writeToBitmap(testSys, freeBlockIndex);
                    writeToDescriptor(filename, freeDescriptorIndex, testSys, id);
                    for (int i = 1; i < 6; i++) {
                        ArrayList<Character> s = testSys.readBlock(i);
                        if (s.get(0) != ' ') Bitmap.writeToBitmap(testSys, i);
                    }
                } else {
                    System.out.println("no free directory");
                }
            } else {
                System.out.println("no free space");
            }
        } else {
            System.out.println("file already exists");
        }
    }
    static void createFile(String filename, int freeBlockIndex) {
        //File.id = id;
        File.filename = filename;
//        Descriptor.filename = filename;
//        Descriptor.blocksNum.add(freeBlockIndex);
//        Descriptor.fileid = id;        Descriptor.filename = filename;
//        Descriptor.blocksNum.add(freeBlockIndex);
//        Descriptor.fileid = id;
    }



    static void writeToDescriptor(String filename, int freeDescriptorIndex, IOSys testSys, int id) {
        ArrayList<Character> rewrite = new ArrayList<>();
        for (int i = 0; i < 64; i++) rewrite.add(' ');
        char[] fileChar = filename.toCharArray();
        for (int i = 0; i < filename.length(); i++) {
            rewrite.add(i, fileChar[i]);
        }
        int j = 0;
        char[] fileIndexChar = Integer.toString(id).toCharArray();
        for (int i = 25; i < fileIndexChar.length + 25; i++) {
            rewrite.add(i, fileIndexChar[j]);
            j++;
        }
        j = 0;
//        char[] freeBlockIndexChar = Integer.toString(freeBlockIndex).toCharArray();
//        for (int i = 30; i < freeBlockIndexChar.length + 30; i++) {
//            rewrite.add(i, freeBlockIndexChar[j]);
//            j++;
//        }
        testSys.writeBlock(freeDescriptorIndex, rewrite);
    }

    static void writeToDirectory(String filename, int descriptorIndex, IOSys testSys) {
        Directory.addToDirectory(filename, descriptorIndex, testSys);
    }
}
