import GitBasic.FileStructure.FileStructure;
import GitBasic.GitObject.Blob;
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
    private File testBlobFile;

    @BeforeEach
    public void createTempFiles() {
        FileStructure.initGitBasicDirectory();
        FileStructure.createInitFiles();
        testBlobFile = new File(FileStructure.BLOB_DIR, "blob_test");
        Utils.writeContents(testBlobFile, "blob_test");
    }

    @AfterEach
    public void deleteTempDir() {
        assertTrue(Utils.deleteDir(FileStructure.GIT_BASIC_DIR), "Failed to delete temp dir");
    }

    @Test
    public void testCurrCommitFile() {
        setupHeadFilesAndCommitFile();
        CurrBranch currBranch = CurrBranch.load();
        Commit commit = Utils.getCurrCommit(currBranch);
        assertEquals("test_message", commit.getMessage());
    }

    private void setupHeadFilesAndCommitFile() {
        CurrBranch currBranch = new CurrBranch();
        currBranch.serialize();
        Commit commit = new Commit(null, "test_message");
        commit.addFile(testBlobFile);
        commit.serialize();
        Head head = new Head("main", commit.getCommitId());
        head.serialize();
    }
}
