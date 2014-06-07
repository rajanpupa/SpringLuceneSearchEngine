SpringLuceneSearchEngine
========================

Spring security with database credentials, Apache Lucene API for indexing and searching. 

The steps for the creation of this project are very simple
* First, you need to have java(1.7), mysql, maven installed in your system.
* There is a .sql file in the src/main/resources folder. It should be imported to the required table in the mysql database, and the datasource bean should be updated with corresponding credentials. (for spring security)
* Create a maven webapp project.
* In the pom.xml file, add dependencies for the spring, spring webmvc, spring content, spring security web, spring security config etc
* Add the spring security related filters for all url pattern in the web.xml file, along with the dispatcher servlet configuration
* In the dispatcher servlet configuration file(dispatcher-servlet.xml), set the component scan tag to search for the annotation driven controllers, also set the viewResolver
* Add the spring-security.xml file for spring security to consume, set http autoconfig to true along with the pattern of url to intercept, and the ROLE required
* Add the authentication manager as well, (for this particular project, the authentication manager is hard coded)
* Add the maven plugin jetty as well, if you want to run your project locally for debugging(pom.xml)
* Run your application with goal (jetty:run)


## NOTE
. The Lucene version 3.0 is used.
. For Spring to load all the files associated with it, you have to list them all in the web.xml file under following tag
```
<!-- Spring context files to be loaded -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>
            /WEB-INF/spring/dispatcher-servlet.xml,
            /WEB-INF/spring/spring-security.xml,
            /WEB-INF/spring/spring-module.xml
        </param-value>
    </context-param>
```
### Most of these things have already been done in this project
