# eklenti adini belirtin. 
# Ayrica her eklentinin, plugins.info dosyasinda da kendi satirinda belirtilmesi gerekiyor.
EKLENTI_ADI = ornekeklentibileseni

# sacre.jar dosyasinin tam ya da goreceli yolunu belirtin.
SACRE_JAR = ../../../ssgpp.git/dist/lib/sacre.jar

all: deploy

deploy: compile
	jar cvf ../../jars/$(EKLENTI_ADI).jar `find -iname *.class`

compile:
	javac -cp $(SACRE_JAR) `find -iname *.java`

clean:
	rm `find -iname *.class`

dist-clean: clean
	rm ../../jars/$(EKLENTI_ADI).jar
