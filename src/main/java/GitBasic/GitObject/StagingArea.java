package GitBasic.GitObject;

import GitBasic.FileStructure.FileStructure;
import GitBasic.Utils;

import java.io.File;
import java.util.HashMap;

/**
 * staging area store the filename and blob id of the files added and files removed
 * it got cleared after each commit
 */
public class StagingArea implements GitObject {
    /* key: file path, value: blob id */
    private HashMap<String, String> _fileAdded;
    /*
     * key: file path, value: blob id
     * the file got removed from the most recent commit
     */
    private HashMap<String, String> _fileRemoved;
    /*
     * Staging area need to check the content of the file at current commit,
     * thus it needs to know the commit id through _currBranch
     */
    private CurrBranch _currBranch;

    public StagingArea(CurrBranch currBranch) {
        _fileAdded = new HashMap<>();
        _fileRemoved = new HashMap<>();
        _currBranch = currBranch;
    }

    public static StagingArea load() {
        StagingArea stagingArea = Utils.readObject(FileStructure.STAGING_AREA_FILE, StagingArea.class);
        return stagingArea;
    }

    @Override
    public void serialize() {
        Utils.writeObject(FileStructure.STAGING_AREA_FILE, this);
    }

    @Override
    public void deserialize(File file) {
        StagingArea stagingArea = Utils.readObject(file, StagingArea.class);
        _fileAdded = stagingArea._fileAdded;
        _fileRemoved = stagingArea._fileRemoved;
    }

    public void stageForAddition(File file) {
        if (isFileIdenticalAtCurrCommit(file) || isFileAlreadyHere(file)) {
            return;
        }
        Blob blob = new Blob(file);
        blob.serialize();
        _fileAdded.put(file.getPath(), blob.getBlobId());
    }

    public void stageForRemoval(File file) {
        return;
    }

    /**
     * @return true, if the file's blobId appears in the current commit
     */
    private boolean isFileIdenticalAtCurrCommit(File file) {
        Blob blob = new Blob(file);
        String hashedFileContent =  blob.getBlobId();
        Commit currCommit = Utils.getCurrCommit(_currBranch);
        return currCommit.getBlobIds().contains(hashedFileContent);
    }

    /**
     * this can happen when a file is changed, added, and then changed back to it’s original version
     */
    private boolean isFileAlreadyHere(File file) {
        Blob blob = new Blob(file);
        String hashedFileContent =  blob.getBlobId();
        return _fileAdded.containsValue(hashedFileContent);
    }

    public HashMap<String, String> getFileAdded() {
        return new HashMap(_fileAdded);
    }

    public HashMap<String, String> getFileRemoved() {
        return new HashMap(_fileRemoved);
    }
}
