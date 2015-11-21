Usage:

- Submit your creative using http api. You will get an id for the submitted creative.
- Check the creative attributes detected by system after some time by sending a http call with your id

Build:
- Create a war. "mvn clean install"
- Create a data base LogiNext in mysql. For dev; The tables would be automatically created when project start up.
- Update its connection infomration in loginext.properties. Put this file in classpath of web application i.e. lib of tomcat
- Deploy the war
- Hit http://localhost:8080/loginext/creativeAttributeScans
