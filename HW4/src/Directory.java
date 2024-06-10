import java.sql.Timestamp;
import java.util.LinkedList;

/**
 * Represents a directory in a file system.
 */
public class Directory extends FileSystemElement {
    private LinkedList<FileSystemElement> children;

    /**
     * Constructs a new directory with the specified name, creation date, and parent directory.
     *
     * @param name        The name of the directory.
     * @param dateCreated The creation date of the directory.
     * @param parent      The parent directory of the directory.
     */
    public Directory(String name, Timestamp dateCreated, Directory parent) {
        super(name, dateCreated, parent);
        children = new LinkedList<>();
    }

    /**
     * Retrieves the children elements of the directory.
     *
     * @return The list of children elements.
     */
    public LinkedList<FileSystemElement> getChildren() {
        return children;
    }

    /**
     * Adds a new child element to the directory.
     *
     * @param element The child element to add.
     */
    public void addChild(FileSystemElement element) {
        children.add(element);
    }

    /**
     * Removes a child element from the directory.
     *
     * @param element The child element to remove.
     */
    public void removeChild(FileSystemElement element) {
        children.remove(element);
    }

    /**
     * Moves the directory to a new parent directory.
     *
     * @param newParent The new parent directory.
     */
    @Override
    public void move(FileSystemElement newParent) {
        Directory dir = (Directory) this.parent;
        dir.removeChild(this);
        Directory newDir = (Directory) newParent;
        newDir.addChild(this);
        setParent(newParent);
    }
}
