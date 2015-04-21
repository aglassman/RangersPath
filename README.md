RangersPath
===========

A text-based adventure game. I'm writing this mostly as a project to explore Java 8 features.


To run in Windows:
==

Have Maven 3 installed
Have Java 8 installed (Create JAVA8_HOME environment variable, may need to restart computer).

Pull the mavenized code bases:
https://github.com/aglassman/JMotion
https://github.com/aglassman/MapGen
https://github.com/aglassman/RangersPath

Install dependencies locally:

../JMotion mvn install
../MapGen mvn install

Package RangersPath:

../RangersPath mvn package

../RangersPath/target/
	+ assets   (contains game assets, this will be migrated into the rangers-path.jar at some point)
	+ rangers-path.jar           (The RangersPath game with all necessary dependencies).
	+ rangers_path_text-only.bat (Runs the text only game)
	+ rangers_path_tiled.bat     (Runs the tiled game)
