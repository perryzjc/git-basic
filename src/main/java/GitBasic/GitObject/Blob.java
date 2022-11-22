package GitBasic.GitObject;

import GitBasic.FileStructure.FileStructure;
import GitBasic.GitBasicException;
import GitBasic.Utils;

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

    public Blob() {

    }

    public Blob(File file) {
        loadFile(file);
    }

    @Override
    public void serialize() {
        if (_filePath == null || _data == null) {
            throw new GitBasicException("Blob is not initialized. Unable to serialize.");
        }
        String blobFileName = generateBlobId();
        File blobFile = Utils.join(FileStructure.BLOB_DIR, blobFileName);
        Utils.writeObject(blobFile, this);
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

    private void loadFile(File file) {
        if (!file.exists()) {
            throw new GitBasicException("File does not exist. Failed to create blob.");
        }
        _data = Utils.readContents(file);
        _filePath = file.getPath();
    }

    private String generateBlobId() {
        return Utils.sha1(_filePath, _data);
    }
}
