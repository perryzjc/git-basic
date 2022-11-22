import GitBasic.GitBasicException;
import GitBasic.GitObject.Commit;
import GitBasic.FileStructure.FileStructure;
import java.io.File;

import GitBasic.GitObject.StagingArea;
import GitBasic.Utils;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Test the commit class
 * Check if the commit object file is created at the correct location
 * Check if the commit object can recover the file stored in the blob object
 */
public class CommitTest {
    private File testCommitFile;
    private File testBlobFile;
    private StagingArea testStagingArea;

    @BeforeAll
    public static void deleteExistingDir() {
        Utils.deleteDir(FileStructure.GIT_BASIC_DIR);
    }

    @BeforeEach
    public void createTempDir() {
        FileStructure.initGitBasicDirectory();
    }

    @BeforeEach
    public void createTempBlobFile() {
        testBlobFile = new File(FileStructure.BLOB_DIR, "blob_test");
        Utils.writeContents(testBlobFile, "blob_test");
    }

    @BeforeEach
    public void createTempCommitFile() {
        testCommitFile = new File(FileStructure.COMMIT_DIR, "commit_test");
        Utils.writeContents(testCommitFile, "commit_test");
    }

    @AfterEach
    public void deleteTempDir() {
        assertTrue(Utils.deleteDir(FileStructure.GIT_BASIC_DIR), "Failed to delete temp dir");
    }

    @Test
    public void testSerialize1_nothingToCommitException() {
        Commit commit = new Commit();
        assertThrows(GitBasicException.class, commit::serialize, "nothing to commit");
    }

    @Test
    public void testSerialize2_noMessageException() {
        Commit commit = new Commit();
        commit.addFile(testBlobFile);
        assertThrows(GitBasicException.class, commit::serialize, "commit need a message");
    }

    @Test
    public void testSerialize3_commitFileCreated() {
        File commitFile;
        Commit commit = new Commit();
        commit.setMessage("commie message");
        commit.addFile(testBlobFile);
        commitFile = new File(FileStructure.COMMIT_DIR, commit.getCommitId());
        assertFalse(commitFile.exists());
        commit.addFile(testBlobFile);
        commit.serialize();
        commitFile = new File(FileStructure.COMMIT_DIR, commit.getCommitId());
        assertTrue(commitFile.exists(), "Commit file is not created successfully");
    }

    @Test
    public void testDeserialize1_commitFileNotFound() {
        Commit commit = new Commit();
        assertThrows(GitBasicException.class, () -> commit.deserialize("no_exist_commit"), "commit file not found");
    }

    @Test
    public void testDeserialize2_commitTimeStamp() {
        Commit commit = new Commit();
        commit.setMessage("test message");
        commit.addFile(testBlobFile);
        commit.serialize();
        Commit commit2 = new Commit();
        commit2.deserialize(commit.getCommitId());
        assertEquals(commit.getTimeStamp(), commit2.getTimeStamp());
    }

    @Test
    public void testDeserialize3_commitMessage() {
        Commit commit = new Commit();
        commit.setMessage("test message");
        commit.addFile(testBlobFile);
        commit.serialize();
        Commit commit2 = new Commit();
        commit2.deserialize(commit.getCommitId());
        assertEquals("test message", commit2.getMessage());
    }

    @Test
    public void testDeserialize4_checkBlobExists() {
        Commit commit = new Commit();
        commit.setMessage("test message");
        commit.addFile(testBlobFile);
        commit.serialize();
        Commit commit2 = new Commit();
        commit2.deserialize(commit.getCommitId());
        File blobFile = new File(FileStructure.BLOB_DIR, commit2.getBlobIds().iterator().next());
        assertTrue(blobFile.exists(), "Blob file does not exist");
    }
}
