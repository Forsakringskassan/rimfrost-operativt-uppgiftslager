package se.fk.github.rimfrost.operativt.uppgiftslager.presentation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import se.fk.github.rimfrost.operativt.uppgiftslager.logic.OperativtUppgiftslagerService;
import se.fk.github.rimfrost.operativt.uppgiftslager.presentation.dto.OperativtUppgiftsLagerUppdatering;
import se.fk.github.rimfrost.operativt.uppgiftslager.presentation.dto.OperativtUppgiftslagerRequest;
import se.fk.github.rimfrost.operativt.uppgiftslager.presentation.dto.OperativtUppgiftslagerResponse;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.LoggerFactory;

import io.smallrye.common.annotation.Blocking;

@ApplicationScoped
@Path("/api/template")
public class OperativtUppgiftslagerController
{

   @Inject
   OperativtUppgiftslagerService fullmaktsService;

   @Inject
   PresentationMapper presentationMapper;

   @Channel("operativt-uppgiftslager-responses")
   Emitter<OperativtUppgiftslagerResponse> emitter;

   @Channel("operativt-uppgiftslager-status-uppdateringar")
   Emitter<OperativtUppgiftsLagerUppdatering> updateEmitter;

   @Incoming("operativt-uppgiftslager-")
   @Blocking
   public void handleIncomingTask(OperativtUppgiftslagerRequest operativtUppgiftslagerRequest) {
      // Logik för att lägga till en uppgift till uppgiftslagret
      // Skicka även ett slags meddelande när den är ny
   }

   public void publishTaskResponse(OperativtUppgiftslagerResponse response) {
      // Logik för att skicka tillbaka resultatet från en uppgift från uppgiftslagret
      // Även för att uppdatera om statusen på en uppgift ändras, för loggning
      emitter.send(response);
   }

   public void publishTaskUpdate(OperativtUppgiftsLagerUppdatering update) {
      // Logik för att skicka en uppdatering för response
      updateEmitter.send(update);
   }

}
