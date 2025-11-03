package tests;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import se.fk.github.logging.callerinfo.model.MDCKeys;
import se.fk.github.quarkustemplate.integration.IntegrationService;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class MdcPropagationTest
{
   @Inject
   IntegrationService integrationService;

   @BeforeEach
   void setup()
   {
      MDC.clear();
   }

   @Test
   void testMdcHeadersSentToDownstreamService()
   {
      String breadcrumbId = "breadcrumb-from-request";
      String processId = "processid-from-request";

      MDC.put(MDCKeys.BREADCRUMBID.name(), breadcrumbId);
      MDC.put(MDCKeys.PROCESSID.name(), processId);

      var result = integrationService.getAlternatives();

      assertThat(MDC.get(MDCKeys.BREADCRUMBID.name())).isEqualTo("breadcrumb-from-response");
      assertThat(MDC.get(MDCKeys.PROCESSID.name())).isEqualTo("processid-from-response");
   }

}
