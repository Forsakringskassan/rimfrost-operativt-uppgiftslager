package se.fk.github.rimfrost.operativt.uppgiftslager.logic;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.rimfrost.operativt.uppgiftslager.logic.entity.UppgiftEntity;
import se.fk.github.rimfrost.operativt.uppgiftslager.logic.enums.UppgiftStatus;
import se.fk.rimfrost.*;

@ApplicationScoped
public class LogicMapper
{

   public OperativtUppgiftslagerStatusMessagePayload toOperativtUppgiftslagerStatusMessagePayload(UppgiftEntity uppgift)
   {
      var data = new OperativtUppgiftslagerStatusMessagePayload();
      data.setStatus(mapStatus(uppgift.status()));
      data.setUppgiftId(uppgift.uppgiftId().toString());
      data.setProcessId(uppgift.processId().toString());
      if (uppgift.personnummer() != null)
         data.setPersonnummer(uppgift.personnummer());
      return data;
   }

   public Status mapStatus(UppgiftStatus status)
   {
      switch (status)
      {
         case UppgiftStatus.NY:
            return Status.NY;
         case UppgiftStatus.TILLDELAD:
            return Status.TILLDELAD;
         case UppgiftStatus.AVSLUTAD:
         default:
            return Status.AVSLUTAD;
      }
   }
}
