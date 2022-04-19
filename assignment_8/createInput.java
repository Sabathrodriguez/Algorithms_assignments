import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class createInput {
    public static void main(String[] args) {
        createFile();
    }
    public static void createFile() {
        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter( new FileWriter("test.txt"));
            writer.write("10000 50000\n");
            writer.write("5 3\n");
            for (int i = 0; i < 5000; i++) {
                writer.write("0 right 3\n");
                writer.write("1 straight 0\n");
                writer.write("2 right 1\n");
                writer.write("2 left 3\n");
                writer.write("2 left 4\n");
                writer.write("3 right 5\n");
                writer.write("4 straight 3\n");
                writer.write("4 left 5\n");
                writer.write("5 right 1\n");
                writer.write("5 left 2\n");
            }                                                                                                                                    
        
        }
        catch ( IOException e)
        {
        }
        finally
        {
            try
            {
                if ( writer != null)
                writer.close( );
            }
            catch ( IOException e)
            {
            }
        }
    }
}
