### Demo application for [XRMI framework](https://github.com/lcf/xrmi)

"Online store" web service that allows adding items to shopping cart. This application demonstrates how a certain part of an application can be deployed and scaled independently with XRMI without any changes to application itself.
 
The web facade entry point is implemented with Java Spark framework. Key business components are:
`ShoppingCartService`, `CustomersService` and `WarehouseService`.

You can launch the JAR file in three modes:

1. java -jar xrmi-demo-1.0.0-SNAPSHOT.jar

will start the application as if XRMI wasn't used, as a single application.

2. java -jar xrmi-demo-1.0.0-SNAPHOST.jar server 12345

will start XRMI server on port 12345

3. java -jar xrmi-demo-1.0.0-SNAPHOST.jar client example.com 12345

will launch the application with XRMI client support. All calls to `WarehouseService` will be directed to remote server located on example.com:12345
