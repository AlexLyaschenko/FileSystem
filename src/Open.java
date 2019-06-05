import java.util.ArrayList;

public class Open {
    static String filename;
    static int index;



    static void openFile(String filename, IOSys testSys) {
        File.pos = 0;
        int descriptorId = Directory.showDescriptor(filename, testSys);
        if (descriptorId != -1) {
            Descriptor.findInfoAboutFile(descriptorId, testSys);
        }
        else {
            System.out.println("this file cannot be opened");
        }
    }
}
