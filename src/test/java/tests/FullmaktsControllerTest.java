package tests;

import common.IntegrationTestBase;
import common.TestDataFactory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import se.fk.gradle.examples.jaxrsspec.controllers.generatedsource.model.OmbudRequest;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class FullmaktsControllerTest extends IntegrationTestBase
{
   @Test
   void testAlternatives()
   {
      String actualResponse = getAlternatives();

      assertThat(actualResponse).isEqualToIgnoringWhitespace("""
            {
                "alternatives": [
                    {
                        "id": "1",
                        "name": "Alt 1"
                    },
                    {
                        "id": "2",
                        "name": "Alt 2"
                    }
                ]
            }
            """);
   }

   @Test
   void testCheckOmbud()
   {
      OmbudRequest ombudRequest = TestDataFactory.createOmbudRequest(TestDataFactory.PNR1);

      String actualResponse = checkOmbud(ombudRequest);

      assertThat(actualResponse).isEqualToIgnoringWhitespace("""
            {
                "name": "Test Testsson"
            }
            """);
   }
}
