import java.io.*;

// https://liveexample.pearsoncmg.com/liang/intro11e/html/TestObjectOutputStream.html
// textbook appendix G Table G.1 referenced

class BitOutputStream extends Driver{
    private FileOutputStream output;
    private int currByte;


    private int numBitsInCurr;
    
    public BitOutputStream(File file) throws FileNotFoundException {
        
        output = new FileOutputStream(file);
        currByte = 0;
        numBitsInCurr = 0;
    }

/**
 * The writeBit(char bit) method stores the bit in a byte variable. 
 * When you create a BitOutputStream, the byte is empty. 
 * After invoking writeBit('1') the byte becomes becomes 00000001. 
 * After invoking writeBit("0101"), the byte becomes 00010101. 
 * The first three bits are not filled yet. When a byte is full, it is sent to the output stream.
 * 
 *  Writes a bit '0' or '1' to the output stream
 */
    public void writeBit(char bit) throws IOException{
        // if (bit != '0' | bit != '1') {
        //     throw new IllegalArgumentException("1 or 0");
        // }


    /**
     * 
     * The operator shifts bits in the first 
    operand left by the number of bits 
    specified in the second operand, 
    filling with 0s on the right

    Ex: 10101110 << 2 yields
        10111000
     */


        currByte = (currByte << 1) | (bit - '0'); //shift bits to the left by 1


        numBitsInCurr++;

        if (numBitsInCurr ==8) { // if true we have a full byte
            output.write(currByte);
            currByte =0;
            numBitsInCurr =0;
        }
        
    } 

    // Writes a string of bits to the output stream
    public void writeBits(String bitString) throws IOException{
        for (int i =0; i < bitString.length(); i++) {
            writeBit(bitString.charAt(i));
        }
    }

    // This method must be invoked to close the stream
    public void close() throws IOException {
        if (numBitsInCurr > 0) {
            currByte <<= (8-numBitsInCurr);
            output.write(currByte);
        }

        output.close();

}
}

