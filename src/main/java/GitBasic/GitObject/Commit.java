package GitBasic.GitObject;

import GitBasic.FileStructure.FileStructure;
import GitBasic.GitBasicException;
import GitBasic.Utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Commit implements  GitObject{
    /* commit id, sha-1 hashed */
    private String _commitId;
    /* the comment for the commit */
    private String _message;
    /* the parent commit ids, marge can cause multiple parent ids */
    private ArrayList<String> _parentCommitIds;
    /* the time stamp when the commit object is created */
    private String _timeStamp;
    /* key: file path, value: blob id */
    private HashMap<String, String> _fileMap;

    public Commit() {
        _parentCommitIds = new ArrayList<>();
        _fileMap = new HashMap<>();
    }

    public Commit(String message) {
        this();
        _message = message;
    }

    @Override
    public void serialize() {
        generateCommitId();
        if (_commitId == null) {
            throw new GitBasicException("commit id is null. not generated correctly");
        }
        File commitFile = new File(FileStructure.COMMIT_DIR, _commitId);
        if (commitFile.exists()) {
            throw new GitBasicException("commit file already exists");
        }
        Utils.writeObject(commitFile, this);
    }

    @Override
    public void deserialize(File file) {
        Utils.checkFileExists(file);
        Commit commit = Utils.readObject(file, Commit.class);
        if (commit == null) {
            throw new GitBasicException("failed to deserialize commit");
        }
        _message = commit._message;
        _commitId = commit._commitId;
        _parentCommitIds = commit._parentCommitIds;
        _timeStamp = commit._timeStamp;
        _fileMap = commit._fileMap;
    }

    public void deserialize(String _commitId) {
        deserialize(new File(FileStructure.COMMIT_DIR, _commitId));
    }

    public void addFile(String filePath) {
        File file = new File(filePath);
        addFile(file);
    }

    public void addFile(File file) {
        Utils.checkFileExists(file);
        Blob blob = new Blob(file);
        blob.serialize();
        _fileMap.put(file.getPath(), blob.getBlobId());
    }

    public void setMessage(String message) {
        _message = message;
    }

    public String getMessage() {
        return _message;
    }

    public String getCommitId() {
        generateCommitId();
        return _commitId;
    }

    public ArrayList<String> getParentCommitIds() {
        return _parentCommitIds;
    }

    public String getTimeStamp() {
        return _timeStamp;
    }

    public HashSet<String> getBlobIds() {
        return new HashSet<>(_fileMap.values());
    }

    /**
     * _commitId is NOT part of calculation of the sha1-hash,
     * or it will cause an incorrect commit id
     */
    private void generateCommitId() {
        if (_message == null) {
            throw new GitBasicException("commit need a message");
        }
        if (_fileMap.isEmpty()) {
            throw new GitBasicException("nothing to commit");
        }
        if (_timeStamp == null) {
            generateTimeStamp();
        }
        _commitId = Utils.sha1(_message, _timeStamp, _parentCommitIds.toString(), _fileMap.toString());
    }

    /**
     * @return the time stamp in the specified format
     * e.g. "Mon Jan 1 00:00:00 2000 -0800"
     */
    private void generateTimeStamp() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z");
        _timeStamp = dateFormat.format(date);
    }
}
