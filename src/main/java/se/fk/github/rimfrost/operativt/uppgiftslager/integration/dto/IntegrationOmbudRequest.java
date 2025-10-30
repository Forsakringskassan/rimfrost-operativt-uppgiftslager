package se.fk.github.quarkustemplate.integration.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface IntegrationOmbudRequest
{
   String personnummer();
}
