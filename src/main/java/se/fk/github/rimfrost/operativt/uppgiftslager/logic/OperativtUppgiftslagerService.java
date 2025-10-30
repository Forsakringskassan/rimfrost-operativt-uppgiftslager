package se.fk.github.quarkustemplate.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.github.quarkustemplate.integration.IntegrationService;
import se.fk.github.quarkustemplate.logic.dto.LogicAlternatives;
import se.fk.github.quarkustemplate.logic.dto.LogicOmbudRequest;
import se.fk.github.quarkustemplate.logic.dto.LogicOmbudResponse;

@ApplicationScoped
public class OperativtUppgiftslagerService
{

   @Inject
   IntegrationService fullmaktsIntegration;

   @Inject
   LogicMapper logicMapper;

   public LogicAlternatives getAlternatives()
   {
      var integrationAlternatives = fullmaktsIntegration.getAlternatives();
      return logicMapper.toLogic(integrationAlternatives);
   }

   public LogicOmbudResponse checkOmbud(LogicOmbudRequest request)
   {
      var integrationRequest = logicMapper.toIntegration(request);
      var integrationResponse = fullmaktsIntegration.checkOmbud(integrationRequest);
      return logicMapper.toLogic(integrationResponse);
   }
}
