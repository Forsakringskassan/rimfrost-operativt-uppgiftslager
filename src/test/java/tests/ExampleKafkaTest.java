package tests;

import common.JsonHelper;
import common.KafkaTestBase;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import se.fk.gradle.examples.asyncapi.ExempelRtfRequestPayload;
import se.fk.gradle.examples.asyncapi.ExempelRtfResponsePayload;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@QuarkusTest
class ExampleKafkaTest extends KafkaTestBase
{

   @Test
   void testReceiveExempelRtfRequest()
   {
      ExempelRtfRequestPayload payload = new ExempelRtfRequestPayload();
      payload.setProcessId("test-process-123");
      payload.setPersonNummer("19990101-0123");

      inMemorySource.send(payload);

      await()
            .atMost(Duration.ofSeconds(5))
            .untilAsserted(() -> {
               assertThat(exampleKafka.getReceivedMessageCount())
                     .as("One message should have been received")
                     .isEqualTo(1);
            });

      await()
            .atMost(Duration.ofSeconds(5))
            .untilAsserted(() -> {
               assertThat(inMemorySink.received())
                     .as("One message should have been sent")
                     .hasSize(1);

               ExempelRtfResponsePayload receivedPayload = inMemorySink.received().get(0).getPayload();

               assertThat(receivedPayload.getPersonNummer()).isEqualTo("123");
            });
   }

   @Test
   void testReceiveMultipleExempelRtfRequests()
   {
      ExempelRtfRequestPayload payload1 = new ExempelRtfRequestPayload();
      payload1.setProcessId("test-process-456");
      payload1.setPersonNummer("19990101-0123");

      ExempelRtfRequestPayload payload2 = new ExempelRtfRequestPayload();
      payload2.setProcessId("test-process-789");
      payload2.setPersonNummer("19850515-1234");

      inMemorySource.send(payload1);
      inMemorySource.send(payload2);

      await()
            .atMost(Duration.ofSeconds(5))
            .untilAsserted(() -> {
               assertThat(exampleKafka.getReceivedMessageCount())
                     .as("Two messages should have been received")
                     .isEqualTo(2);
            });
   }
}
