package common;

import io.quarkus.test.common.QuarkusTestResource;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;
import io.smallrye.reactive.messaging.memory.InMemorySink;
import io.smallrye.reactive.messaging.memory.InMemorySource;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import se.fk.github.quarkustemplate.integration.ExampleKafka;
import se.fk.gradle.examples.asyncapi.ExempelRtfRequestPayload;
import se.fk.gradle.examples.asyncapi.ExempelRtfResponsePayload;

@QuarkusTestResource(InMemoryKafkaResource.class)
public class KafkaTestBase
{
   @Inject
   @Any
   public InMemoryConnector connector;

   @Inject
   public ExampleKafka exampleKafka;

   public InMemorySource<ExempelRtfRequestPayload> inMemorySource;
   public InMemorySink<ExempelRtfResponsePayload> inMemorySink;

   @BeforeEach
   void setUp()
   {
      inMemorySource = connector.source("exempel-rtf-requests");
      inMemorySink = connector.sink("exempel-rtf-responses");
      inMemorySink.clear();
      exampleKafka.resetCounter();
   }

   @AfterEach
   void tearDown()
   {
      InMemoryConnector.clear();
   }
}
