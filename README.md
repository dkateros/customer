# JEE7, Java 8, JSON-B and BEAN VALIDATION 2

## A glimpse in the future

We write applications that need to transform data between various layers. The same data are represented differently on the database, in the application server JVM, over the network (e.g. with HTTP) when different applications exchange messages. Our applications have needs that cross-cut their functionality and we need a platform that streamlines these common use cases in a compatible manner. 

JEE has taken great steps to facilitate this need, using ideas from the many open source frameworks that emerged in the last 12 years or so. Not surprisingly, the JEE platform moves a bit slower than the state of the art open source libraries. However, the JEE specs embody the experience that has been gained by these open source projects in order to result in an overall better developer experience.

This project shows a glimpse of the next JEE version experience. We are interested in using Java 8 (that among other things has a new date-time API), standardized JSON binding (JSON-B) and the new Bean Validation 2.0.

## Wildfly 10.1.0.Final setup

### JSON-B

JSON-B integration at the moment happens on the application level. Once this is integrated on the application server it will be possible for the server to handle the conversions implicitly based on the declared `MediaType` on the `@Produces` and `@Consumes` annotations. This means that the application will only use the entity representation and will not care about `JsonObject` and such.

Currently, letting the application server handle this will produce errors, as the default JSON provider (Jackson) cannot handle `java.time.LocalDate` properties. As far as I know it is not yet possible to use JSON-B for implicit transformations.

At any rate, we want to have as standard Java <-> JSON conversion as possible without worrying about the implementation details.

### Bean validation 2.0

It is possible to patch wildfly 10.1.0.Final to use the hibernate implementation of this spec. Download the following:
```
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator-modules</artifactId>
    <version>6.0.2.Final</version>
    <classifier>wildfly-10.1.0.Final-patch</classifier>
    <type>zip</type>
</dependency>
```

and patch the server using the jboss-cli

`>$JBOSS_HOME/bin/jboss-cli.sh`

and then give the following to the shell that is created:

```
connect
patch apply hibernate-validator-modules-6.0.2.Final-wildfly-10.1.0.Final-patch.zip
exit
```

## Sample app

REST api over customer resource. Sample curl commands

GET with id:
curl -X GET http://localhost:8080/customer-api/rs/customers/1

Add a new customer:
curl -H "Content-Type: application/json" -X POST -d '{"birthDate":"1900-06-10","email":"email2@gmail.com","firstName":"Another","lastName":"Kateros"}' http://localhost:8080/customer-api/rs/customers