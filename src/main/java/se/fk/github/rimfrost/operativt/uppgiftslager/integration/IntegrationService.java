package se.fk.github.quarkustemplate.integration;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import se.fk.github.jaxrsclientfactory.JaxrsClientFactory;
import se.fk.github.jaxrsclientfactory.JaxrsClientOptionsBuilders;
import se.fk.github.quarkustemplate.integration.dto.IntegrationAlternativesResponse;
import se.fk.github.quarkustemplate.integration.dto.IntegrationOmbudRequest;
import se.fk.github.quarkustemplate.integration.dto.IntegrationOmbudResponse;
import se.fk.gradle.examples.jaxrsspec.controllers.generatedsource.FullmaktsControllerApi;

@ApplicationScoped
public class IntegrationService
{

   @ConfigProperty(name = "fullmakts.api.base-url")
   String fullmaktBaseUrl;

   @Inject
   IntegrationMapper integrationMapper;

   private FullmaktsControllerApi fullmaktsClient;

   @PostConstruct
   void init()
   {
      this.fullmaktsClient = new JaxrsClientFactory()
            .create(JaxrsClientOptionsBuilders.createClient(fullmaktBaseUrl, FullmaktsControllerApi.class)
                  .header("static-header-1", "static-header-1-value")
                  .build());
   }

   public IntegrationAlternativesResponse getAlternatives()
   {
      var response = fullmaktsClient.getAlternatives();
      return integrationMapper.fromExternalApi(response);
   }

   public IntegrationOmbudResponse checkOmbud(IntegrationOmbudRequest externalRequest)
   {
      var request = integrationMapper.toExternalApi(externalRequest);
      var response = fullmaktsClient.checkOmbud(request);
      return integrationMapper.fromExternalApi(response);
   }
}
