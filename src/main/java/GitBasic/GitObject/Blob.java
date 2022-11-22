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
    /* the blob id, sha-1 hashed. It's the filename of blob object */
    private String _blobId;

    public Blob() {

    }

    public Blob(File file) {
        loadFile(file);
    }

    public Blob(String filePath) {
        loadFile(new File(filePath));
    }

    @Override
    public void serialize() {
        if (_filePath == null || _data == null) {
            throw new GitBasicException("Blob is not initialized. Unable to serialize.");
        }
        String blobFileName = getBlobId();
        File blobFile = Utils.join(FileStructure.BLOB_DIR, blobFileName);
        Utils.writeObject(blobFile, this);
    }

    @Override
    public void deserialize(File file) {
        Blob blob = Utils.readObject(file, Blob.class);
        _data = blob._data;
        _filePath = blob._filePath;
    }

    public String getBlobId() {
        if (_blobId == null) {
            _blobId = generateBlobId();
        }
        return _blobId;
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

    /**
     * blob id is NOT part of the calculation of the blob id,
     * or it will cause an incorrect sha-1 hash
     */
    private String generateBlobId() {
        return Utils.sha1(_filePath, _data);
    }
}
