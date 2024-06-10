# Makefile for Social Network Analysis program

# Variables
JAVAC = javac
JAVA = java
JDFLAGS = -protected -splitindex -use -author -version -d ./javadoc
SRC = SocialNetworkGraph.java Person.java Main.java
CLASSES = SocialNetworkGraph.class Person.class Main.class
MAIN = Main

# Default target to compile the program
all: compile

# Target to compile the Java files
compile:
	$(JAVAC) $(SRC)

# Target to run the program
run: compile
	$(JAVA) $(MAIN)

doc:  
	javadoc $(JDFLAGS) $(SRC)
	
# Target to clean up compiled files and javadoc
clean:
	rm -f $(CLASSES)
	rm -r ./javadoc

# Phony targets to prevent conflicts with files named 'all', 'compile', 'run', or 'clean'
.PHONY: all compile run clean
