package GitBasic.GitObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Commit implements  GitObject{
    /* the comment for the commit */
    private String _message;
    /* commit id, sha-1 hashed */
    private String _commitId;
    /* the parent commit ids, marge can cause multiple parent ids */
    private ArrayList<String> _parentCommitIds;
    /* the time stamp when the commit object is created */
    private String _timeStamp;
    /* key: file path, value: blob id */
    private HashMap<String, String> _fileMap;

    @Override
    public void serialize() {

    }

    @Override
    public void deserialize(File file) {

    }

    public void addFile(File file) {

    }

    public void addMessage(String message) {

    }

    public String getMessage() {
        return _message;
    }

    public String getCommitId() {
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
}
