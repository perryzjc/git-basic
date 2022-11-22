package GitBasic.GitObject;

import GitBasic.FileStructure.FileStructure;
import GitBasic.GitBasicException;
import GitBasic.Utils;

import java.io.File;

/**
 * Head is a pointer to the specific branch
 * It points to the most recent commit of the branch
 */
public class Head implements GitObject {
    private String _branchName;
    private String _commitId;

    public Head(String branchName, String commitId) {
        _branchName = branchName;
        _commitId = commitId;
    }

    @Override
    public void serialize() {
        if (_branchName == null || _commitId == null) {
            throw new GitBasicException("Head is not initialized. Unable to serialize.");
        }
        File headFile = new File(FileStructure.HEAD_DIR, _branchName);
        Utils.writeObject(headFile, this);
    }

    @Override
    public void deserialize(File file) {
        Head head = Utils.readObject(file, Head.class);
        _branchName = head._branchName;
        _commitId = head._commitId;
    }

    public String getBranchName() {
        return _branchName;
    }

    public String getCommitId() {
        return _commitId;
    }

    public void setBranchName(String branchName) {
        _branchName = branchName;
    }

    public void setCommitId(String commitId) {
        _commitId = commitId;
    }
}
