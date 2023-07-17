# paper-scissors-ROCK-acceptance-tests
Demo three different types of acceptance tests using Paper, Scissors, ROCK!

## Requirements
JDK 17+ (Spring Boot 3 + DGS requirement)

## Container Based Acceptance Test
What is the artefact you are deploying? A binary or package. Or are you deploying to container Kubernetes? 
This acceptance test variant takes the published docker container and mocks all collaborating services.
The container based acceptance tests the same artefact that is going to be deployed to production. This gives any 
business a killer capability to test the same artefact that the customer will be using to and put it under its paces 
to ensure that it is of the highest quality to be given to human customers. If you are using Continuous Delivery or 
Deployment, I hope you are using some form of Container based acceptance tests in a pipeline.

Example BDD feature [src/test/resources/features/async-rock-paper-scissors.feature](src/test/resources/features/async-rock-paper-scissors.feature)

Where steps are defined: [src/test/kotlin/local/mathewdj/rock/acceptance/AsyncGameStepDefinitions.kt](src/test/kotlin/local/mathewdj/rock/acceptance/AsyncGameStepDefinitions.kt)

Test container boot strapping: [src/test/kotlin/local/mathewdj/rock/acceptance/Containers.kt](src/test/kotlin/local/mathewdj/rock/acceptance/Containers.kt).

