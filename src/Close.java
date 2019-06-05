public class Close {
    public static void close() {
        File.descriptorId = -1;
        File.blocksNum = null;
        File.currentNum = -1;
        File.firstBlock = null;
        File.fileLength = -1;
        File.filename = null;
        //File.id = -1;
    }
}
