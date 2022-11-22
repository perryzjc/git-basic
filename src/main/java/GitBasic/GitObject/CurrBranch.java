package GitBasic.GitObject;

import GitBasic.FileStructure.FileStructure;
import GitBasic.GitBasicException;
import GitBasic.Utils;

import java.io.File;

/**
 * current branch object stores the name of the current branch
 */
public class CurrBranch implements GitObject {
    public static final String DEFAULT_BRANCH_NAME = "main";
    private String _branchName;

    public CurrBranch() {
        _branchName = DEFAULT_BRANCH_NAME;
    }

    public CurrBranch(String branchName) {
        _branchName = branchName;
    }

    public static CurrBranch load() {
        File currBranchFile = FileStructure.CURRENT_BRANCH_FILE;
        Utils.checkFileExists(currBranchFile);
        CurrBranch currBranch = new CurrBranch();
        currBranch.deserialize(currBranchFile);
        return currBranch;
    }

    @Override
    public void serialize() {
        if (_branchName == null) {
            throw new GitBasicException("branch name is not initialized. Unable to serialize.");
        }
        File currBranchFile = Utils.join(FileStructure.CURRENT_BRANCH_FILE);
        Utils.writeObject(currBranchFile, this);
    }

    @Override
    public void deserialize(File file) {
        CurrBranch currBranch = Utils.readObject(file, CurrBranch.class);
        _branchName = currBranch._branchName;
    }

    public String getBranchName() {
        return _branchName;
    }

    public void setBranchName(String branchName) {
        _branchName = branchName;
    }
}
