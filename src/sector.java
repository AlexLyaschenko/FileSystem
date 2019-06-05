import java.util.ArrayList;

public class sector {

    public static final int bytesAmount = 64;
    public ArrayList<Character> bytes;

    sector(){
        bytes = new ArrayList<>();
        bytes.ensureCapacity(bytesAmount);

        for(int i = 0; i < bytesAmount; i++){
            Character ch = ' ';
            bytes.add(ch);
        }
    }

}
