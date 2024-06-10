import java.util.*;
import java.sql.Timestamp;

/**
 * Represents a file system with directories and files.
 */
public class FileSystem {
    private Directory root;
    private Directory currentDirectory;

    /**
     * Constructs a new file system with a root directory.
     */
    public FileSystem() {
        root = new Directory("root", new Timestamp(System.currentTimeMillis()), null);
        currentDirectory = root;
    }

    /**
     * Retrieves the root directory of the file system.
     *
     * @return The root directory.
     */
    public Directory getRoot() {
        return root;
    }

    /**
     * Retrieves the current working directory of the file system.
     *
     * @return The current directory.
     */
    public Directory getCurrentDirectory() {
        return currentDirectory;
    }

    /**
     * Sets the current working directory of the file system.
     *
     * @param directory The directory to set as the current working directory.
     */
    public void setCurrentDirectory(Directory directory) {
        currentDirectory = directory;
    }
    
    /**
     * Creates a new directory in the current working directory.
     *
     * @param name The name of the new directory.
     */
    public void createDirectory(String name) {
        Directory newDir = new Directory(name, new Timestamp(System.currentTimeMillis()), currentDirectory);
        currentDirectory.addChild(newDir);
    }

    /**
     * Creates a new file in the current working directory.
     *
     * @param name The name of the new file.
     */
    public void createFile(String name) {
        File newFile = new File(name, new Timestamp(System.currentTimeMillis()), currentDirectory);
        currentDirectory.addChild(newFile);
    }

    /**
     * Deletes a file or directory with the given name in the specified directory.
     *
     * @param name      The name of the file or directory to delete.
     * @param directory The directory in which to search for the file or directory to delete.
     * @return True if the file or directory was successfully deleted, false otherwise.
     */
    public boolean delete(String name, Directory directory){
        // If target is file delete it, if target is directory then recursively empty every directory and delete every file in it and at last delete the directory itself
        for(FileSystemElement element : directory.getChildren()){
            if(element.getName().equals(name) && element instanceof File){
                directory.removeChild(element);
                return true;
            }
            else if(element.getName().equals(name) && element instanceof Directory) {
                Directory dir = (Directory) element;
                // Empty target directory
                while(!dir.getChildren().isEmpty()){
                    deleteRecursively((Directory) element);
                }
                // Delete the empty target directory
                Directory dirParent = (Directory) dir.getParent();
                dirParent.removeChild(dir);
                return true;
            }
        }
        return false;
    }

    /**
     * Recursively deletes a directory and its contents.
     *
     * @param directory The directory to delete.
     */
    private void deleteRecursively(Directory directory) {
        Iterator<FileSystemElement> iterator = directory.getChildren().iterator();
        while (iterator.hasNext()) {
            FileSystemElement element = iterator.next();
            if (element instanceof File) {
                Directory dir = (Directory) element.getParent();
                dir.removeChild(element); // Remove the element from the list
            } else if (element instanceof Directory) {
                Directory dir = (Directory) element;
                if (!dir.getChildren().isEmpty()) {
                    // Recursive call to empty a directory
                    deleteRecursively(dir);
                } if(dir.getChildren().isEmpty()){         
                    // Delete the empty directory 
                    Directory dirParent = (Directory) dir.getParent();
                    dirParent.removeChild(dir);
                }
            }
        }
    }
    
    /**
     * Gets the current path of the file system.
     *
     * @return The current path.
     */
    public String getCurrentPath() {
        StringBuilder path = new StringBuilder();
        FileSystemElement current = getCurrentDirectory();
        while (current != null) {
            path.insert(0, current.getName() + "/");
            current = current.getParent();
        }
        return path.toString();
    }

    /**
     * Searches for a file or directory with the given name in the specified directory.
     *
     * @param name     The name of the file or directory to search for.
     * @param directory The directory in which to search for the file or directory.
     * @param path     The current path.
     * @return The path to the found file or directory, or a message indicating it was not found.
     */
    public String search(String name, Directory directory, String path) {
        // Search for target inside current directory
        for (FileSystemElement element : directory.getChildren()) {
            if (element.getName().equals(name)) {
                return path + "/" + element.getName();
            } else if (element instanceof Directory) {
                // If element is a directory, recursively search the target inside it
                String newPath = path + "/" + element.getName();
                String foundPath = search(name, (Directory) element, newPath);
                if (!foundPath.equals("There is no file or directory named " + name + ".")) {
                    return foundPath; // Return the found path if it's not the default message
                }
            }
        }
        return "There is no file or directory named " + name + ".";
    }
    
    /**
     * Prints the directory tree starting from the specified directory.
     *
     * @param directory The directory from which to print the tree.
     * @param prefix    The prefix for each level of the tree.
     */
    public void printDirectoryTree(Directory directory, String prefix) {
        System.out.println(prefix + directory.getName() + "/");
        for (FileSystemElement element : directory.getChildren()) {
            if (element instanceof Directory) {
                printDirectoryTree((Directory) element, prefix + "\t");
            } else {
                System.out.println(prefix + "\t" + element.getName());
            }
        }
    }

    /**
     * Lists the contents of the current directory.
     */
    public void listContents() {
        System.out.println("\n\nContents of " + getCurrentPath() + ":\n");
        for (FileSystemElement element : currentDirectory.getChildren()) {
            if (element instanceof Directory) {
                System.out.println("Directory: " + element.getName());
            } else {
                System.out.println("File: " + element.getName());
            }
        }
    }

    /**
     * Displays the current path.
     */
    public void displayCurrentPath() {
        System.out.println("\nCurrent Path: " + getCurrentPath());
    }

    /**
     * Moves a file or directory to the specified path.
     *
     * @param elementName The name of the file or directory to move.
     * @param path        The path to move the file or directory to.
     */
    public void move(String elementName, String path) {
        Directory newParent = findDirectoryByPath(path);
        if (newParent == null) {
            System.out.println("Destination directory not found.");
            return;
        }

        FileSystemElement foundElement = null;
        // Find the element to move
        for (FileSystemElement element : currentDirectory.getChildren()) {
            if (element.getName().equals(elementName)) {
                foundElement = element;
                break;
            }
        }

        if (foundElement == null) {
            System.out.println("Element '" + elementName + "' not found in current directory.");
            return;
        }
        // Move the found element to the new parent
        if (foundElement instanceof Directory) {
            Directory directory = (Directory) foundElement;
            directory.move(newParent); // Call directory move function
        } else if (foundElement instanceof File) {
            File file = (File) foundElement;
            file.move(newParent); // Call file move function
        }
        System.out.println("Successfully moved " + elementName + " into " + path+ ".");
    }

    /**
     * Sorts the contents of the specified directory by date created.
     *
     * @param current The directory whose contents are to be sorted.
     */
    public void sortContent(Directory current) {
        List<FileSystemElement> sortedContents = new ArrayList<>(current.getChildren());
    
        // Sort the list based on the timestamp of each element
        Collections.sort(sortedContents, new Comparator<FileSystemElement>() {
            @Override
            public int compare(FileSystemElement e1, FileSystemElement e2) {
                return e1.getDateCreated().compareTo(e2.getDateCreated());
            }
        });
    
        // Print the sorted contents
        System.out.println("\nSorted contents of " + current.getName() + " by date created:");
        for (FileSystemElement element : sortedContents) {
            if (element instanceof Directory) System.out.println("* " + element.getName() + "/ (" + element.getDateCreated() + ")");
            else if (element instanceof File) System.out.println(element.getName() + " (" + element.getDateCreated() + ")");
        }
    }

    /**
     * Finds a directory by its path.
     *
     * @param fullPath The full path of the directory to find.
     * @return The directory found, or null if not found.
     */
    private Directory findDirectoryByPath(String fullPath) {
        // Split the full path by '/'
        String[] pathComponents = fullPath.split("/");

        // Start from the root directory
        Directory currentDirectory = getRoot();

        // Iterate through the path components to find the directory
        for (String componentName : pathComponents) {
            // Ignore empty components
            if (!componentName.isEmpty() && !componentName.equals("root")) { // Allow writing or not writing 'root' at the beginning
                boolean found = false;
                // Search for the current component in the children of the current directory
                for (FileSystemElement element : currentDirectory.getChildren()) {
                    if (element.getName().equals(componentName) && element instanceof Directory) {
                        currentDirectory = (Directory) element;
                        found = true;
                        break;
                    }
                }
                // If the current component is not found, return null
                if (!found) {
                    return null;
                }
            }
        }
        // Return the directory found
        return currentDirectory;
    }
}
