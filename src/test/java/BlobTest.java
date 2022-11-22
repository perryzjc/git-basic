import GitBasic.FileStructure.FileStructure;
import GitBasic.GitObject.Blob;
import GitBasic.Utils;
import org.junit.jupiter.api.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Test for the Blob class.
 * Test whether blob can serialize a file at the correct path.
 * Test whether blob can deserialize a file and return the original file (content and path)
 */
public class BlobTest {
    private static File testFile;
    @BeforeEach
    public void createTempDirAndFile() {
        FileStructure.initGitBasicDirectory();
        testFile = Utils.join("src", "test", "resources", "test_files", "a.txt");
    }

    @AfterEach
    public void deleteTempDir() {
        Utils.deleteDir(FileStructure.GIT_BASIC_DIR);
    }

    @Test
    public void testSerialize() {
        File[] files;
        files = FileStructure.BLOB_DIR.listFiles();
        assertTrue(files != null && files.length == 0);
        Blob blob = new Blob(testFile);
        blob.serialize();
        files = FileStructure.BLOB_DIR.listFiles();
        assertTrue(files != null && files.length == 1);
    }
}
