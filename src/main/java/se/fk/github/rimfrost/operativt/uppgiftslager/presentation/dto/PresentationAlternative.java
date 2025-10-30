package se.fk.github.rimfrost.operativt.uppgiftslager.presentation.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface PresentationAlternative
{
   String id();

   String name();
}
