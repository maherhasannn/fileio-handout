import java.io.*;

// https://liveexample.pearsoncmg.com/liang/intro11e/html/TestObjectOutputStream.html
// textbook appendix G Table G.1 referenced

class BitOutputStream extends Driver{
    private FileOutputStream output;
    private int currByte;


    private int numBitsInCurr;
    
    public BitOutputStream(File file) throws FileNotFoundException {
        try {
            this.output = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found " + file);
            throw e; 
        }
        this.currByte = 0;
        this.numBitsInCurr = 0;
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
        if (bit != '0' | bit != '1') {
            throw new IllegalArgumentException("1 or 0");
        }


    /**
     * 
     * The operator shifts bits in the first 
    operand left by the number of bits 
    specified in the second operand, 
    filling with 0s on the right

    Ex: 10101110 << 2 yields
        10111000
     */


        currByte = (currByte << 1);
        currByte = currByte | (bit - '0');

        numBitsInCurr++;

        if (numBitsInCurr == 8) {
            try {
                output.write(currByte);
            } catch (IOException e) {
                System.err.println("Error writing to output stream");
                throw e; // Re-throw the exception to be handled by the caller
            }
            numBitsInCurr = 0;
            currByte = 0;
        }
        
    } 

    // Writes a string of bits to the output stream
    public void writeBits(String bitString) throws IOException{
        for (char bit : bitString.toCharArray()) {
            writeBit(bit);
        }
    }

    // This method must be invoked to close the stream
    public void close() throws IOException {
        if (numBitsInCurr > 0) {
            currByte <<= (8 - numBitsInCurr);
            try {
                output.write(currByte);
            } catch (IOException e) {
                System.err.println("Error writing to output stream");
                throw e; 
            }
        }
        try {
            output.close();
        } catch (IOException e) {
            System.err.println("Error closing output stream");
            throw e; 
        }
    }



}