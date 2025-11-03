package se.fk.github.rimfrost.operativt.uppgiftslager.logic;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

import io.vertx.core.net.impl.pool.Task;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.github.rimfrost.operativt.uppgiftslager.logic.dto.OperativtUppgiftslagerUppgift;

@ApplicationScoped
public class OperativtUppgiftslagerService
{
   @Inject
   LogicMapper logicMapper;

   private final ConcurrentHashMap<UUID, OperativtUppgiftslagerUppgift> tasks = new ConcurrentHashMap<>();   
   private final LinkedBlockingDeque<UUID> availableQueue = new LinkedBlockingDeque<>();

   public void addOperativeTask(OperativtUppgiftslagerUppgift uppgift)
   {
      tasks.put(uppgift.processId, uppgift);
      availableQueue.offerLast(uppgift.processId);
   }

   public OperativtUppgiftslagerUppgift getNextTask(String handlaggarId) {
      UUID id = availableQueue.pollFirst();
      if (id == null) return null;

      OperativtUppgiftslagerUppgift t = tasks.get(id);
      if (t == null) return null;

      if (!"Ny".equals(t.status)) {
         return null;
      }
      t.status = "Tilldelad";
      t.handlaggarId = handlaggarId;
      t.version++;
      t.andrad = OffsetDateTime.now();

      // return logicMapper.toDto(t);
      return t;
   }

   public OperativtUppgiftslagerUppgift updateOperativeTask(UUID taskId, String newStatus, String kommentar)
   {
      OperativtUppgiftslagerUppgift t = tasks.get(taskId);
      if (t == null) return null;
      t.status = newStatus;
      t.version++;
      t.andrad = OffsetDateTime.now();
      // return logicMapper.toDto(t);
      return t;
   }

   public void notifyTaskCompleted()
   {
      // TODO: metodanrop på producern för att skicka meddelande om att tasken är klar via interface sättet
   }
}
