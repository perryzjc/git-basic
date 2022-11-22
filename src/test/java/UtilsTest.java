import GitBasic.FileStructure.FileStructure;
import GitBasic.GitObject.Commit;
import GitBasic.GitObject.CurrBranch;
import GitBasic.GitObject.Head;
import GitBasic.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test partial methods in Utils.java
 */
public class UtilsTest {
    @BeforeEach
    public void createTempDir() {
        FileStructure.initGitBasicDirectory();
        FileStructure.createInitFiles();
    }

    @AfterEach
    public void deleteTempDir() {
        assertTrue(Utils.deleteDir(FileStructure.GIT_BASIC_DIR), "Failed to delete temp dir");
    }

    @Test
    public void testCurrCommitFile() {
        setupHeadFiles();

    }

    private void setupHeadFilesAndCommitFile() {
        File currBranchFile = Utils.join(FileStructure.HEAD_DIR, CurrBranch.DEFAULT_BRANCH_NAME);
        Head head = new Head("main", "test_commitId_123");
        head.serialize();
        Commit commit = new Commit();
        commit.set
    }
}
