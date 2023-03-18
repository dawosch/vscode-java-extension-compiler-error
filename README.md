# Repository to reproduce the compiler problem

## Steps to reproduce the error
1. Run `mvn -U clean install` in the terminal of the root folder
2. Check the following file: `app/target/generated-sources/annotations/con/example/demo/TestMappterInterfaceImpl.java`
3. Start the `app` project from the `Spring Boot Dashboard` extension
4. Your code will be recompiled by the extension
5. Check the same file like before

You will notice that the setter on line 20 is gone and that the compiler changed on the generated info part.


## Information
Sometimes the extension doesn't recompile your code.
Then you need to change something in the file `app/src/main/java/con/example/demo/TestMapperInterface.java`
Adding some line breaks should be enough.
