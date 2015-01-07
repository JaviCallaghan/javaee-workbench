javaee-workbench
================

Java EE 7 features experimentation repository for pure feature or technology testing.

* **helloworld**: Project with all necessary artifacts for a basic JSF application with default navigation.
  * `helloworld-web`: war Maven artifact for the web application with minimum dependencies.

* **helloworld_full**: Project with all necessary artifacts for a basic JSF application with injection and specific navigation.
  * `helloworld-lib`: jar Maven artifact for the web application business logic.
  * `helloworld-web`: war Maven artifact for the web application with minimum dependencies.
  * `helloworld-app`: ear Maven artifact for the application assembly.
  * `helloworld-pom`: pom parent for all the previous artifacts to build the whole project.
