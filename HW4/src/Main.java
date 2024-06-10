import java.util.Scanner;

/**
 * Main class to display the menu; to manage the file system and user inputs.
 */
public class Main {
    /**
     * Main method to run the file system management program.
     */
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        Scanner scanner = new Scanner(System.in);
        
        // DEFAULT TEST DATA
        /*
        fileSystem.createDirectory("documents");
        fileSystem.createDirectory("pictures");
        fileSystem.createDirectory("notes");
        fileSystem.createFile("picture1.png");
        fileSystem.createFile("picture2.png");
        fileSystem.createFile("picture3.png");
        fileSystem.createFile("text1.txt");
        fileSystem.createFile("text2.txt");
        fileSystem.createFile("text3.txt");
        fileSystem.move("pictures", "documents");
        fileSystem.move("notes", "documents");
        fileSystem.move("picture1.png", "documents/pictures");
        fileSystem.move("text1.txt", "documents/notes");
        */
        
        while (true) {
            System.out.println("\n\n===== File System Management Menu =====");
            System.out.println("1. Change directory");
            System.out.println("2. List directory contents");
            System.out.println("3. Create file/directory");
            System.out.println("4. Delete file/directory");
            System.out.println("5. Move file/directory");
            System.out.println("6. Search file/directory");
            System.out.println("7. Print directory tree");
            System.out.println("8. Sort contents by date created");
            System.out.println("9. Exit");

            System.out.print("Please select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    // Change directory
                    fileSystem.displayCurrentPath();
                    System.out.print("Enter new directory path: ");
                    String newPath = scanner.nextLine();
                    
                    // Check if the newPath is an absolute path or relative path
                    if (newPath.equals("root")) { // Handle root directory explicitly
                        fileSystem.setCurrentDirectory(fileSystem.getRoot());
                        System.out.println("Directory changed to: /" + newPath);
                    }else if (newPath.startsWith("/")) { // Absolute path
                        // Traverse the file system to find the directory
                        Directory currentDirectory = fileSystem.getRoot();
                        String[] pathComponents = newPath.split("/");
                        for (String component : pathComponents) {
                            if (!component.isEmpty() && currentDirectory != null) {
                                boolean found = false;
                                for (FileSystemElement element : currentDirectory.getChildren()) {
                                    if (element.getName().equals(component) && element instanceof Directory) {
                                        currentDirectory = (Directory) element;
                                        found = true;
                                        break;
                                    }
                                }
                                if (!found) {
                                    System.out.println("Directory not found: " + newPath);
                                    break;
                                }
                            }
                        }
                        fileSystem.setCurrentDirectory(currentDirectory);
                        System.out.println("Directory changed to: " + fileSystem.getCurrentPath());
                    } else { // Relative path
                        Directory currentDirectory = fileSystem.getCurrentDirectory();
                        for (FileSystemElement element : currentDirectory.getChildren()) {
                            if (element.getName().equals(newPath) && element instanceof Directory) {
                                fileSystem.setCurrentDirectory((Directory) element);
                                System.out.println("Changed directory to: " + fileSystem.getCurrentPath());
                                break;
                            }
                        }
                        if (!fileSystem.getCurrentDirectory().getName().equals(newPath)) {
                            System.out.println("Directory not found: " + newPath);
                        }
                        break;
                    }
                    break;
                case 2:
                    // List directory contents
                    fileSystem.listContents();
                    break;
                case 3:
                    // Create file/directory
                    fileSystem.displayCurrentPath();
                    System.out.print("Create file or directory (f/d): ");
                    String input = scanner.nextLine();
                    // Check if the input is a single character
                    if (input.length() == 1) {
                        char choice = input.charAt(0);
                        if (choice == 'f' || choice == 'F') {
                            System.out.print("Enter name for new file: ");
                            String name = scanner.nextLine();
                            fileSystem.createFile(name);
                            System.out.println("File created: " + name);
                        } else if (choice == 'd' || choice == 'D') {
                            System.out.print("Enter name for new directory: ");
                            String name = scanner.nextLine();
                            fileSystem.createDirectory(name);
                            System.out.println("Directory created: " + name + "/");
                        } else {
                            System.out.println("Invalid choice. Please enter 'f' for file or 'd' for directory.");
                        }
                    } else {
                        System.out.println("Invalid choice. Please enter 'f' for file or 'd' for directory.");
                    }
                    break;
                case 4:
                    // Delete file/directory
                    fileSystem.displayCurrentPath();
                    System.out.print("Enter name of file/directory to delete: ");
                    String deleteName = scanner.nextLine();
                    boolean res = fileSystem.delete(deleteName,fileSystem.getCurrentDirectory());
                    System.out.println(res ? "Successfully deleted " + deleteName + ".": "There is no file named " + deleteName + ".");
                    break;
                case 5:
                    // Move file/directory
                    fileSystem.displayCurrentPath();
                    System.out.print("Enter name of file/directory to move: ");
                    String moveName = scanner.nextLine();
                    System.out.print("Enter new directory path: ");
                    String destinationName = scanner.nextLine();
                    fileSystem.move(moveName, destinationName);
                    break;
                case 6:
                    // Search file/directory
                    System.out.print("\nEnter name of file/directory to search: ");
                    String searchName = scanner.nextLine();
                    String result = fileSystem.search(searchName, fileSystem.getRoot(), "/root");
                    System.out.println(result);
                    break;
                case 7:
                    // Print directory tree
                    System.out.println("\nDirectory Tree:");
                    fileSystem.printDirectoryTree(fileSystem.getRoot(), "");
                    break;
                case 8:
                    // Sort content of current directory
                    fileSystem.sortContent(fileSystem.getCurrentDirectory());
                    break;
                case 9:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
