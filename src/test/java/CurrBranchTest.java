import GitBasic.FileStructure.FileStructure;
import GitBasic.GitObject.CurrBranch;
import GitBasic.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test CurrBranch class
 * Test its location
 * Test its default content
 */
public class CurrBranchTest {
    @BeforeEach
    public void createTempCurrBranchFile() {
        FileStructure.initGitBasicDirectory();
        File currBranchFile = Utils.join(FileStructure.CURRENT_BRANCH_FILE);
        Utils.writeObject(currBranchFile, new CurrBranch());
    }

    @AfterEach
    public void deleteTempDir() {
        assertTrue(Utils.deleteDir(FileStructure.GIT_BASIC_DIR), "Failed to delete temp dir");
    }

    @Test
    public void testCurrBranchFileLocation() {
        assertEquals(".git-basic/refs/currentBranch", FileStructure.CURRENT_BRANCH_FILE.getPath());
    }

    @Test
    public void testCurrBranchDefaultContent() {
        CurrBranch currBranch = new CurrBranch();
        currBranch.deserialize(FileStructure.CURRENT_BRANCH_FILE);
        assertEquals("main", currBranch.getBranchName());
    }
}
