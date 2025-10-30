package se.fk.github.quarkustemplate.logic.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface LogicAlternative
{
   String id();

   String name();
}
