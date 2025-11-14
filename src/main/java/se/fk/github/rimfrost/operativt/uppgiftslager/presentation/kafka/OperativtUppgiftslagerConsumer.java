package se.fk.github.rimfrost.operativt.uppgiftslager.presentation.kafka;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.github.rimfrost.operativt.uppgiftslager.logic.OperativtUppgiftslagerService;
import se.fk.github.rimfrost.operativt.uppgiftslager.presentation.kafka.util.PresentationKafkaMapper;
import se.fk.rimfrost.OperativtUppgiftslagerRequestMessagePayload;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.common.annotation.Blocking;
import se.fk.rimfrost.OperativtUppgiftslagerResponseMessagePayload;
import se.fk.rimfrost.OperativtUppgiftslagerStatusMessagePayload;

@ApplicationScoped
public class OperativtUppgiftslagerConsumer
{
   private static final Logger log = LoggerFactory.getLogger(OperativtUppgiftslagerConsumer.class);

   @Inject
   OperativtUppgiftslagerService operativtUppgiftslagerService;

   @Inject
   PresentationKafkaMapper mapper;

   @Incoming("operativt-uppgiftslager-requests")
   @Blocking
   public OperativtUppgiftslagerResponseMessagePayload onOperativtUppgiftsLagerRequest(
         OperativtUppgiftslagerRequestMessagePayload operativtUppgiftslagerRequest)
   {
      log.info("Received task for operativt uppgiftslager: {}", operativtUppgiftslagerRequest);

      var oulAddRequest = mapper.mapToLogicOulAddRequest(operativtUppgiftslagerRequest.getData());
      var oulAddRequestMetadata = mapper.mapToLogicOulAddRequestMetadata(operativtUppgiftslagerRequest);

      var id = operativtUppgiftslagerService.addOperativeTask(oulAddRequest, oulAddRequestMetadata);
      log.info("Processed task for operativt uppgiftslager: {}", operativtUppgiftslagerRequest);

      var response = new OperativtUppgiftslagerResponseMessagePayload();
      response.getData().setUppgiftId(id);
      return response;
   }

   @Incoming("operativt-uppgiftslager-status-control")
   @Blocking
   public void onOperativeUppgiftsLagerStatusUpdate(
         OperativtUppgiftslagerStatusMessagePayload operativtUppgiftslagerStatusMessage)
   {
      log.info("Received task for StatusUpdate: {}", operativtUppgiftslagerStatusMessage);

      var oulUpdateRequest = mapper.mapToLogicOulStatusMessage(operativtUppgiftslagerStatusMessage);
      operativtUppgiftslagerService.onTaskStatusUpdated(oulUpdateRequest);

      log.info("Processed task for StatusUpdate: {}", oulUpdateRequest);
   }
}
