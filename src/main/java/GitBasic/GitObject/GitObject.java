package GitBasic.GitObject;

import java.io.Serializable;

/**
 * At git-basic, every file stored in the .git-basic repository is a git object
 * git object is serializable
 */
public interface GitObject extends Serializable {
    public void serialize();
}
