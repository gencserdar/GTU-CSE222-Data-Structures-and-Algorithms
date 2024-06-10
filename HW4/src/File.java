import java.sql.Timestamp;

/**
 * Represents a file in the file system.
 */
public class File extends FileSystemElement {

    /**
     * Constructor for creating a new File object.
     *
     * @param name        The name of the file.
     * @param dateCreated The timestamp indicating the creation date of the file.
     * @param parent      The parent directory of the file.
     */
    public File(String name, Timestamp dateCreated, FileSystemElement parent) {
        super(name, dateCreated, parent);
    }

    /**
     * Moves the file to a new parent directory.
     *
     * @param newParent The new parent directory to which the file will be moved.
     */
    @Override
    public void move(FileSystemElement newParent) {
        Directory dir = (Directory) this.getParent();
        dir.removeChild(this);
        Directory newDir = (Directory) newParent;
        newDir.addChild(this);
        setParent(newParent);
    }
}
