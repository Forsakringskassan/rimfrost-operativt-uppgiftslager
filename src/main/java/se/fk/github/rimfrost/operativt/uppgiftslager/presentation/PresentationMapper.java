package se.fk.github.quarkustemplate.presentation;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.quarkustemplate.logic.dto.*;
import se.fk.github.quarkustemplate.presentation.dto.*;
import se.fk.gradle.examples.jaxrsspec.controllers.generatedsource.model.Alternative;
import se.fk.gradle.examples.jaxrsspec.controllers.generatedsource.model.AlternativesResponse;
import se.fk.gradle.examples.jaxrsspec.controllers.generatedsource.model.OmbudRequest;
import se.fk.gradle.examples.jaxrsspec.controllers.generatedsource.model.OmbudResponse;

@ApplicationScoped
public class PresentationMapper
{

   public PresentationAlternativesResponse toPresentation(LogicAlternatives logic)
   {
      if (logic == null || logic.alternatives() == null)
      {
         return ImmutablePresentationAlternativesResponse.builder()
               .alternatives(java.util.Collections.emptyList())
               .build();
      }

      return ImmutablePresentationAlternativesResponse.builder()
            .alternatives(
                  logic.alternatives().stream()
                        .map(this::toPresentation)
                        .toList())
            .build();
   }

   public PresentationAlternative toPresentation(LogicAlternative logic)
   {
      return ImmutablePresentationAlternative.builder()
            .id(logic.id())
            .name(logic.name())
            .build();
   }

   public LogicOmbudRequest toLogic(OperativtUppgiftslagerRequest presentation)
   {
      return ImmutableLogicOmbudRequest.builder()
            .personnummer(presentation.personnummer())
            .build();
   }

   public OperativtUppgiftslagerResponse toPresentation(LogicOmbudResponse logic)
   {
      return ImmutablePresentationOmbudResponse.builder()
            .name(logic.name())
            .build();
   }

   private String extractIdFromHref(String href)
   {
      if (href != null && href.startsWith("/alternative/"))
      {
         return href.substring("/alternative/".length());
      }
      return "unknown";
   }

   public AlternativesResponse toExternalApi(PresentationAlternativesResponse presentationDto)
   {
      AlternativesResponse apiResponse = new AlternativesResponse();
      if (presentationDto.alternatives() != null)
      {
         apiResponse.setAlternatives(
               presentationDto.alternatives().stream()
                     .map(alt -> {
                        var apiAlt = new Alternative();
                        apiAlt.setId(alt.id());
                        apiAlt.setName(alt.name());
                        return apiAlt;
                     })
                     .toList());
      }
      return apiResponse;
   }

   public OperativtUppgiftslagerRequest fromExternalApi(OmbudRequest ombudRequest)
   {
      return ImmutablePresentationOmbudRequest.builder()
            .personnummer(ombudRequest.getPersonnummer())
            .build();
   }

   public OmbudResponse toExternalApi(OperativtUppgiftslagerResponse presentationResponse)
   {
      OmbudResponse apiResponse = new OmbudResponse();
      apiResponse.setName(presentationResponse.name());
      return apiResponse;
   }
}
