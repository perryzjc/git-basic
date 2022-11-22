import GitBasic.FileStructure.FileStructure;
import GitBasic.GitObject.StagingArea;
import GitBasic.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class StagingAreaTest {
    private File testWorkingFile;
    private StagingArea stagingArea;

    @BeforeAll
    public static void deleteExistingDir() {
        Utils.deleteDir(FileStructure.GIT_BASIC_DIR);
    }

    @BeforeEach
    public void setUp() {
        FileStructure.initGitBasicDirectory();
        FileStructure.createInitFiles();
        testWorkingFile = Utils.join("src", "test", "resources", "test_files", "a.txt");
        stagingArea = StagingArea.load();
    }

    @AfterEach
    public void deleteTempDir() {
        Utils.deleteDir(FileStructure.GIT_BASIC_DIR);
    }

    @Test
    public void testStageForAddition1() {
        assertEquals(0, stagingArea.getFileAdded().size());
        stagingArea.stageForAddition(testWorkingFile);
        assertEquals(1, stagingArea.getFileAdded().size());
    }

    @Test
    public void testStageForAddition2_repeatedFileAtCurrentStagingArea() {
        assertEquals(0, stagingArea.getFileAdded().size());
        stagingArea.stageForAddition(testWorkingFile);
        assertEquals(1, stagingArea.getFileAdded().size());
        stagingArea.stageForAddition(testWorkingFile);
        assertEquals(1, stagingArea.getFileAdded().size());
    }

    @Test
    /**
     * TODO: need to implement after the commit class is done
     */
    public void testStageForAddition3_repeatedFileAtCurrenCommit() {
    }
}
