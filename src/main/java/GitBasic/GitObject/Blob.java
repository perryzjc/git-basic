package GitBasic.GitObject;

import GitBasic.GitBasicException;

import java.io.File;

/**
 * Bulb object stores the content of a file and its relative path at the working directory.
 * The filename of the blob file is the SHA-1 hash of the content of the file.
 */
public class Blob implements GitObject {
    /* the content of the file */
    private byte[] _data;
    /* the relative path of the file at the working directory*/
    private String _filePath;

    public Blob(File file) {
        if (!file.exists()) {
            throw new GitBasicException("File does not exist. Failed to create blob.");
        }
    }

    @Override
    public void serialize() {
        return;
    }

    @Override
    public GitObject deserialize(File file) {
        return null;
    }

    public byte[] getData() {
        return _data;
    }

    public String getFilePath() {
        return _filePath;
    }
}
