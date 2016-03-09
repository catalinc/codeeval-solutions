import java.nio.ByteOrder;

public class endian {
    public static void main(String[] args) {
        final ByteOrder byteOrder = ByteOrder.nativeOrder();
        if (byteOrder.equals(ByteOrder.BIG_ENDIAN)) {
            System.out.println("BigEndian");
        } else {
            System.out.println("LittleEndian");
        }
    }
}
