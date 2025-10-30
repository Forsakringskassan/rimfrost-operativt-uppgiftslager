package se.fk.github.quarkustemplate.integration.dto;

import java.util.List;
import org.immutables.value.Value;

@Value.Immutable
public interface IntegrationAlternativesResponse
{
   List<IntegrationAlternative> alternatives();
}
