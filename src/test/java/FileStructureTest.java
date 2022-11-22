import GitBasic.FileStructure.FileStructure;
import GitBasic.Utils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the file structure of the project.
 * Test whether each file/dir are created correctly.
 *
 * Create a.txt temporary directory for testing. And delete it after all tests.
 */
public class FileStructureTest {
    @BeforeAll
    public static void createTempDir() {
        FileStructure.initGitBasicDirectory();
    }

    @AfterAll
    public static void deleteTempDir() {
        assertTrue(Utils.deleteDir(FileStructure.GIT_BASIC_DIR), "Failed to delete temp dir");
    }

    @Test
    public void testGitDirectory() {
        assertEquals(FileStructure.GIT_BASIC_DIR.getPath(), ".git-basic");
        assertTrue(FileStructure.GIT_BASIC_DIR.isDirectory());
    }

    @Test
    public void testObjectDirectory() {
        assertEquals(FileStructure.OBJECT_DIR.getPath(), ".git-basic/objects");
        assertTrue(FileStructure.OBJECT_DIR.isDirectory());
    }

    @Test
    public void testCommitDirectory() {
        assertEquals(FileStructure.COMMIT_DIR.getPath(), ".git-basic/objects/commits");
        assertTrue(FileStructure.COMMIT_DIR.isDirectory());
    }

    @Test
    public void testCommitFile() {
        Utils.writeContents(FileStructure.COMMIT_OBJECT, "commit");
        assertEquals(FileStructure.COMMIT_OBJECT.getPath(), ".git-basic/objects/commits/commit");
        assertTrue(FileStructure.COMMIT_OBJECT.isFile());
    }

    @Test
    public void testBlobDirectory() {
        assertEquals(FileStructure.BLOB_DIR.getPath(), ".git-basic/objects/blobs");
        assertTrue(FileStructure.BLOB_DIR.isDirectory());
    }

    @Test
    public void testBlobFile() {
        Utils.writeContents(FileStructure.BLOB_OBJECT, "blob");
        assertEquals(FileStructure.BLOB_OBJECT.getPath(), ".git-basic/objects/blobs/blob");
        assertTrue(FileStructure.BLOB_OBJECT.isFile());
    }

    @Test
    public void testStagingAreaFile() {
        Utils.writeContents(FileStructure.STAGING_AREA_FILE, "test");
        assertEquals(FileStructure.STAGING_AREA_FILE.getPath(), ".git-basic/objects/stagingArea");
        assertTrue(FileStructure.STAGING_AREA_FILE.isFile());
    }

    @Test
    public void testRefDirectory() {
        assertEquals(FileStructure.REF_DIR.getPath(), ".git-basic/refs");
        assertTrue(FileStructure.REF_DIR.isDirectory());
    }

    @Test
    public void testHeadDirectory() {
        assertEquals(FileStructure.HEAD_DIR.getPath(), ".git-basic/refs/heads");
        assertTrue(FileStructure.HEAD_DIR.isDirectory());
    }

    @Test
    public void testHeadFile() {
        Utils.writeContents(FileStructure.HEAD_OBJECT, "head");
        assertEquals(FileStructure.HEAD_OBJECT.getPath(), ".git-basic/refs/heads/head");
        assertTrue(FileStructure.HEAD_OBJECT.isFile());
    }

    @Test
    public void testCurrentBranchFile() {
        Utils.writeContents(FileStructure.CURRENT_BRANCH_FILE, "test");
        assertEquals(FileStructure.CURRENT_BRANCH_FILE.getPath(), ".git-basic/refs/currentBranch");
        assertTrue(FileStructure.CURRENT_BRANCH_FILE.isFile());
    }
}
