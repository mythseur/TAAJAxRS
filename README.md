TAA Labs
===================

The goal of this lab is to add support of restful webservices for an application. 

In order to initialize your project, we provide a tiny sample project to show how to run an JAX-RS application, embedded in Undertow app server, fired up in a `main()` method. 

Undertow is a flexible performant web server written in java, providing both blocking and non-blocking API’s based on NIO.

Undertow has a composition based architecture that allows you to build a web server by combining small single purpose handlers. The gives you the flexibility to choose between a full Java EE servlet 3.1 container, or a low level non-blocking handler, to anything in between.

Undertow is designed to be fully embeddable, with easy to use fluent builder APIs. Undertow’s lifecycle is completely controlled by the embedding application.


### Getting started. 

Fork this project. 

Just build the project:

    mvn clean install
    
and launch the server:

    java -jar target/server-test-1.0-SNAPSHOT.jar
   
You can test the service using 
to get a person
> curl -H "Content-Type: application/json" -X GET http://localhost:8080/status/person

to insert a Person
> curl -H "Content-Type: application/json" -X POST -d '{"name":"test","firstName":"t"}' http://localhost:8080/status/person

You can import this maven project in your favorite IDE. 

# Task 1

Using this example, select an example of RestServices you could build.

You can have three choices. 

1. You can re-implement [swapi](https://swapi.co/)

2. You can provide a rest access within the [Borg calendar](https://github.com/mikeberger/borg_calendar). In this case, you need to install the [lombok tool](https://projectlombok.org/) for your IDE .

3. you can provide you own Rest API for your tweeter account [http://twitter4j.org/en/index.html](http://twitter4j.org/en/index.html). 

### Tweeter

For tweeter, you must go to [tweeter app](https://apps.twitter.com/) and create an application. When your application is created, you can see a new **tab key and access tokens**

you can find some sample [here](http://twitter4j.org/en/code-examples.html)

in particular 

```java
  public static void main(String args[]) throws Exception{
    // The factory instance is re-useable and thread safe.
    TwitterFactory factory = new TwitterFactory();
    AccessToken accessToken = loadAccessToken(Integer.parseInt(args[0]));
    Twitter twitter = factory.getInstance();
    twitter.setOAuthConsumerKey("[consumer key]", "[consumer secret]");
    twitter.setOAuthAccessToken(accessToken);
    Status status = twitter.updateStatus(args[1]);
    System.out.println("Successfully updated the status to [" + status.getText() + "].");
    System.exit(0);
  }
  private static AccessToken loadAccessToken(int useId){
    String token = // load from a persistent store
    String tokenSecret = // load from a persistent store
    return new AccessToken(token, tokenSecret);
  }

```


The goal is to design a clean CRUD API fo a business layer that allows to interact with your application using retful services.


Maxime provides a complete example with an integration with JPA. https://github.com/maxleiko/taa-jpa if require. 

This Fork implements the Twitter API
You need the create and set up the twitter4j.properties file like describe int this http://twitter4j.org/en/configuration.html.


# Task 2. 
In following this guide. Integrate swagger with your API

https://github.com/swagger-api/swagger-core/wiki/Swagger-Core-RESTEasy-2.X-Project-Setup-1.5

Look at the three files to understand how you can integrate swagger to your application. Of course, when you go in production, the delivery of swagger-ui static content (JS, html, images)must not be provided by undertow but it must be provided by your nginx or your apache webserver. 

* https://github.com/maxleiko/taa-jpa/blob/master/src/main/java/web/rest/SwaggerResource.java
* https://github.com/maxleiko/taa-jpa/blob/master/pom.xml#L59
* https://github.com/maxleiko/taa-jpa/blob/master/src/main/java/app/RestApplication.java#L20

