package se.fk.github.quarkustemplate.integration.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface IntegrationAlternative
{
   String id();

   String name();
}
