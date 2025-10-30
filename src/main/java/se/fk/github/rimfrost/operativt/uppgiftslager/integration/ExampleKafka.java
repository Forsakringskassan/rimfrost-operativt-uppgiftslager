package se.fk.github.quarkustemplate.integration;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.LoggerFactory;

import se.fk.gradle.examples.asyncapi.ExempelRtfRequestPayload;
import se.fk.gradle.examples.asyncapi.ExempelRtfResponsePayload;

import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;

@ApplicationScoped
public class ExampleKafka
{
   private static final Logger LOGGER = LoggerFactory.getLogger(ExampleKafka.class);

   private final AtomicInteger receivedMessageCount = new AtomicInteger(0);

   @Incoming("exempel-rtf-requests")
   @Outgoing("exempel-rtf-responses")
   public ExempelRtfResponsePayload receiveExempelRtfRequest(ExempelRtfRequestPayload incoming)
   {
      receivedMessageCount.incrementAndGet();
      LOGGER.info("Received ExempelRtfRequestPayload: {}", incoming);
      ExempelRtfResponsePayload response = new ExempelRtfResponsePayload();
      response.setPersonNummer("123");
      return response;
   }

   public int getReceivedMessageCount()
   {
      return receivedMessageCount.get();
   }

   public void resetCounter()
   {
      receivedMessageCount.set(0);
   }
}
