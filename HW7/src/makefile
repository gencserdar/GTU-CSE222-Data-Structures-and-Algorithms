JAVAC = javac
MAIN_CLASS = Main
JDFLAGS = -protected -splitindex -use -author -version -d ./javadoc
SOURCES = Stock.java AVLTree.java StockDataManager.java Main.java GUIVisualization.java InputFileGenerator.java
CLASSES = $(SOURCES:.java=.class)

all: $(CLASSES)

%.class: %.java
	$(JAVAC) $<

run: $(CLASSES)
	java -Xint $(MAIN_CLASS) input.txt

random: $(CLASSES)
	java InputFileGenerator

doc:  
	javadoc $(JDFLAGS) $(SOURCES)
	
clean:
	rm -f *.class
	rm -r ./javadoc


help:
	@echo "Usage:"
	@echo "  make         - Compile all source files"
	@echo "  make run     - Run the main class"
	@echo "  make clean   - Remove all class files and javadoc file"
	@echo "  make random    - Generate input.txt file with random commands"
	@echo "  make help    - Display this help message"

.PHONY: all run clean help
