package GitBasic.FileStructure;

import GitBasic.Utils;
import java.io.File;

/**
 * constants for file structure at .git-basic directory
 */
public class FileStructure {
    public static final File GIT_BASIC_DIR = new File(".git-basic");
    public static final File OBJECT_DIR = Utils.join(GIT_BASIC_DIR, "objects");
    public static final File COMMIT_DIR = Utils.join(OBJECT_DIR, "commits");
    public static final File COMMIT_OBJECT = Utils.join(COMMIT_DIR, "commit");
    public static final File BLOB_DIR = Utils.join(OBJECT_DIR, "blobs");
    public static final File BLOB_OBJECT = Utils.join(BLOB_DIR, "blob");
    public static final File STAGING_AREA_FILE = Utils.join(OBJECT_DIR, "stagingArea");
    public static final File REF_DIR = Utils.join(GIT_BASIC_DIR, "refs");
    public static final File HEAD_DIR = Utils.join(REF_DIR, "heads");
    public static final File HEAD_OBJECT = Utils.join(HEAD_DIR, "head");
    public static final File CURRENT_BRANCH_FILE = Utils.join(REF_DIR, "currentBranch");

    public static void initGitBasicDirectory() {
        GIT_BASIC_DIR.mkdir();
        OBJECT_DIR.mkdir();
        COMMIT_DIR.mkdir();
        BLOB_DIR.mkdir();
        REF_DIR.mkdir();
        HEAD_DIR.mkdir();
    }
}
