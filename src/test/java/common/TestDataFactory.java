package common;

import se.fk.github.quarkustemplate.integration.dto.ImmutableIntegrationOmbudRequest;
import se.fk.github.quarkustemplate.integration.dto.IntegrationOmbudRequest;
import se.fk.gradle.examples.jaxrsspec.controllers.generatedsource.model.OmbudRequest;

public final class TestDataFactory
{
   public static final String PNR1 = "201701032391";
   public static final String PATH_ALTERNATIVES = "/api/template/alternatives";
   public static final String PATH_OMBUD = "/api/template/ombud";

   public static IntegrationOmbudRequest createIntegrationOmbudRequest()
   {
      return ImmutableIntegrationOmbudRequest.builder()
            .personnummer(TestDataFactory.PNR1)
            .build();
   }

   public static OmbudRequest createOmbudRequest(String pnr)
   {
      return new OmbudRequest()
            .personnummer(pnr);
   }
}
