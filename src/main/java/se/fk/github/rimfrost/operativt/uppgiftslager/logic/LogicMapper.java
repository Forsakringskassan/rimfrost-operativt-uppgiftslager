package se.fk.github.quarkustemplate.logic;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.quarkustemplate.integration.dto.*;
import se.fk.github.quarkustemplate.logic.dto.*;

import java.util.Collections;

@ApplicationScoped
public class LogicMapper
{

   public LogicAlternatives toLogic(IntegrationAlternativesResponse external)
   {
      if (external == null || external.alternatives() == null)
      {
         return ImmutableLogicAlternatives.builder()
               .alternatives(Collections.emptyList())
               .build();
      }

      return ImmutableLogicAlternatives.builder()
            .alternatives(
                  external.alternatives().stream()
                        .map(this::toLogic)
                        .toList())
            .build();
   }

   public LogicAlternative toLogic(IntegrationAlternative external)
   {
      return ImmutableLogicAlternative.builder()
            .name(external.name())
            .id(external.id())
            .build();
   }

   public LogicOmbudResponse toLogic(IntegrationOmbudResponse external)
   {
      return ImmutableLogicOmbudResponse.builder()
            .name(external.name())
            .build();
   }

   public IntegrationOmbudRequest toIntegration(LogicOmbudRequest logic)
   {
      return ImmutableIntegrationOmbudRequest.builder()
            .personnummer(logic.personnummer())
            .build();
   }
}
