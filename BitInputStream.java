
// https://liveexample.pearsoncmg.com/liang/intro11e/html/TestObjectInputStream.html

/**
 * reads a stream of bits from a file
 * @author maher hasan
 */


class BitInputStream extends Driver {
    private FileInputStream input;
    private int curr;
    private int storage;

    public BitInputStream(File file) {
        this.input = new FileInputStream(file);
        this.curr = 0;
        this.storage =0;
    }

    /**
     javas .available already returns how many bytes can be read from an input stream.
     since we want to know how many bits are available we can find how many bytes are .available
     and multiply it by 8 and then + storage.

      Returns the number of bits available in the file
    */
    public int available( ) {
        return (this.input * 8) + storage;
    }

    // Reads the next bit from the input stream.
    public char readBit() {

    }

    //Reads the remainder of the file and returns the bit string.
    public String readBits() {

    }

    //This method must be invoked to close the stream
    public void close() {
        input.close();
    }
}