# McShares
McSharesProject

The project has being implemented with Spring boot version 2.6.9 and Java version 17.

Please refer to the following documentation.
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.9/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.9/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.9/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.9/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.9/reference/htmlsingle/#using.devtools)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.6.9/reference/htmlsingle/#actuator)
* [Validation](https://docs.spring.io/spring-boot/docs/2.6.9/reference/htmlsingle/#io.validation)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.6.9/reference/htmlsingle/#web.security)
* [Kubernetes](https://kubernetes.io/docs/reference/)

## Build and deploy application
1) The project is build using spring-boot image where by creating a container image.
2) The image can then be pushed to remote repository.
3) A deployment script has been provided so as a the initial release version, 1.0.0.RELEASE.
4) Security measure has been added to the pod so as it will run as root user.
