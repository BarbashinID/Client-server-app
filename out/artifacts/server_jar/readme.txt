Клонировать репозиторий в директорию на диске
В терминале перейти в директорию main/java/ и скомпилировать java-классы (для удобства в директорию out):
 javac -classpath . App.java commands/*.java entities/*.java utils/*.java -d ../../../out

Перейти в директорию out/ и объединить классы в исполняемый jar-файл:
jar cvfe server.jar App *.class */*.class

Проверить работоспособность jar-файла:
java -jar server.jar
