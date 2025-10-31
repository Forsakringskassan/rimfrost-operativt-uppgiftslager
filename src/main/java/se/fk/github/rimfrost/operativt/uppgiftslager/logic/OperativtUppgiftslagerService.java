package se.fk.github.rimfrost.operativt.uppgiftslager.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OperativtUppgiftslagerService
{
   @Inject
   LogicMapper logicMapper;

   public void addOperativeTask()
   {
      // TODO: Logik för att lägga till i DB osv
   }

   public void updateOperativeTask(Object dataFörTaskUppdatering)
   {
      // TODO: Logik för att uppdatera tasken, om den är klar kör ett anrop vidare
      // if (dataFörTaskUppdatering.status.equals("Avslutad")) {
      //    notifyTaskCompleted(nyaDatanSomBehövs);
      // }
   }

   public void notifyTaskCompleted()
   {
      // TODO: metodanrop på producern för att skicka meddelande om att tasken är klar via interface sättet
   }
}
