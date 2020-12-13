# Employees Project

Requirements:
<ol>
    <li> java 11+ </li>
    <li> maven (to build project) </li>
</ol>

How To Run: </br>  
            
From terminal :
    
    1 - first build the project to produce the runnable jar 
        mvn clean package
    2 - to run the jar :
        java -jar sirma-1.0-SNAPSHOT.jar -parameters
    3 - the projects accepts -filePath , -man , -withUi where :
        -man gives a short manual; 
        -filePath [path_to_file] is the path to a file that would be opened;
        -withUi [boolean] specifies if a javaFX is to be used for display 
            (default is false);
        note that if withUi is specified -filePath would be ignored
        
        example :
        java -jar sirma-1.0-SNAPSHOT.jar -withUi true  
