JC = javac
JFLAGS = -classpath .
JD = javadoc
JDFLAGS = -protected -splitindex -use -author -version -d ./javadoc
RM = rm
JR = java

CLASSES = \
		Inventory.java \
		Device.java \
		InventorySystem.java \
		Smartphone.java \
		Laptop.java \
		Headphone.java \
		SmartWatch.java \
		TV.java

all : InventorySystem.class

run : 
	$(JR) InventorySystem

classes : $(CLASSES:.java=.class)

%.class : %.java
	$(JC) $(JFLAGS) $<

doc:
	$(JD) $(JDFLAGS) *.java 

clean:
	$(RM) *.class report.txt

cleandoc:
	$(RM) -r ./javadoc
