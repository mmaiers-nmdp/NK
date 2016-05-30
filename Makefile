
all: run

clean:
	rm *.class
NK.class:	NK.java
	javac NK.java

run:	NK.class
	java NK

mail: 	
	/usr/ucb/mail -s NK.java maiers@webconn.com <NK.java
