javaee-workbench
================

Java EE 7 features experimentation repository for pure feature or technology testing.

* **helloworld**: Project with all necessary artifacts for a basic JSF application with default navigation.
  * `helloworld-web`: war Maven artifact for the web application.

* **helloworld_full**: helloworld improvement with full basic architecture (ear containing war holding business logic libraries). Includes CDI injection and JSF specific navigation.
  * `helloworld-srv`: jar Maven artifact for the web application business logic interface.
  * `helloworld-srv-impl`: jar Maven artifact for the web application business logic implementation.
  * `helloworld-web`: idem, now having previous jars as minimum dependencies.
  * `helloworld-app`: ear Maven artifact for the application assembly.
  * `helloworld-pom`: pom parent for all the previous artifacts to build the whole project.

* **helloworld_mb**: helloworld_full improvement with MBeans backing to deal with configuration changes at runtime and supporting notifications.
  * `helloworld-srv`: idem.
  * `helloworld-srv-impl`: idem.
  * `helloworld-web`: idem, now delegating runtime configuration and execution issues to MBean attributes and methods, also being responsive to MBean notifications.
  * `helloworld-app`: idem.
  * `helloworld-pom`: idem.