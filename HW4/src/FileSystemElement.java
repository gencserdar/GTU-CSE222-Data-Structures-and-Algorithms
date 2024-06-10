import java.sql.Timestamp;

/**
 * Represents a generic element in a file system.
 */
public abstract class FileSystemElement {
    protected String name;
    protected Timestamp dateCreated;
    protected FileSystemElement parent;

    /**
     * Constructs a new file system element with the specified name, creation date, and parent element.
     *
     * @param name        The name of the file system element.
     * @param dateCreated The creation date of the file system element.
     * @param parent      The parent element of the file system element.
     */
    public FileSystemElement(String name, Timestamp dateCreated, FileSystemElement parent) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.parent = parent;
    }

    /**
     * Retrieves the name of the file system element.
     *
     * @return The name of the file system element.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the creation date of the file system element.
     *
     * @return The creation date of the file system element.
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    /**
     * Retrieves the parent element of the file system element.
     *
     * @return The parent element of the file system element.
     */
    public FileSystemElement getParent() {
        return parent;
    }

    /**
     * Sets the parent element of the file system element.
     *
     * @param parent The new parent element.
     */
    public void setParent(FileSystemElement parent) {
        this.parent = parent;
    }

    /**
     * Moves the file system element to a new parent element.
     *
     * @param newParent The new parent element.
     */
    public abstract void move(FileSystemElement newParent);
}
