package GitBasic.GitObject;

/**
 * Bulb object stores the content of a file and its relative path at the working directory.
 * The filename of the blob file is the SHA-1 hash of the content of the file.
 */
public class Blob implements GitObject {
    /* the content of the file */
    private byte[] _data;
    /* the relative path of the file at the working directory*/
    private String _filePath;

    @Override
    public void serialize() {

    }
}
