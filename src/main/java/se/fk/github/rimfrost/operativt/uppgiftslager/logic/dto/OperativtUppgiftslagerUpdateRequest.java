package se.fk.github.rimfrost.operativt.uppgiftslager.logic.dto;

import java.util.UUID;

import org.immutables.value.Value;

import se.fk.github.rimfrost.operativt.uppgiftslager.logic.enums.UppgiftStatus;

@Value.Immutable
public interface OperativtUppgiftslagerUpdateRequest
{
   UUID uppgiftId();

   String processId();

   String personnummer();

   String resultat();

   UppgiftStatus status();
}
