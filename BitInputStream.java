
// https://liveexample.pearsoncmg.com/liang/intro11e/html/TestObjectInputStream.html
// referenced pg 683 from textbook

import java.io.*;
/**
 * reads a stream of bits from a file
 * @author maher hasan
 */


class BitInputStream extends Driver {
    private FileInputStream input;
    private int currentByte;    // current byte being read from file
    private int numBitsRemaining;  // number of bits remaining in current byte

    public BitInputStream(File file) throws FileNotFoundException{
        input = new FileInputStream(file);
        numBitsRemaining = 0;
    }

    /**
     javas .available already returns how many bytes can be read from an input stream.
     since we want to know how many bits are available we can find how many bytes are .available
     and multiply it by 8 and then + storage.

      Returns the number of bits available in the file
    */
    public int available() throws IOException {
        return input.available() * 8 + numBitsRemaining;
    }

    // Reads the next bit from the input stream.
    public char readBit() throws IOException {
        if (numBitsRemaining == 0) {
            currentByte = input.read();
            if (currentByte == -1) {
                throw new EOFException("End of file reached");
            }
            numBitsRemaining = 8;
        }
        
        char bit = (char) ((currentByte >> (numBitsRemaining - 1)) & 1);
        numBitsRemaining--;
        return bit;
    }

    //Reads the remainder of the file and returns the bit string.
    public String readBits() throws IOException {
        StringBuilder result = new StringBuilder();
        int bit;
        while ((bit = readBit()) != -1) {
            result.append(bit);
        }
        return result.toString();
    }

    //This method must be invoked to close the stream
    public void close() throws IOException {
        input.close();
    }
}
