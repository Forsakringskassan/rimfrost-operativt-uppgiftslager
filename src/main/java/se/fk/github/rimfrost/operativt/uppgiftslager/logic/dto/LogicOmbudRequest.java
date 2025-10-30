package se.fk.github.quarkustemplate.logic.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface LogicOmbudRequest
{
   String personnummer();
}
