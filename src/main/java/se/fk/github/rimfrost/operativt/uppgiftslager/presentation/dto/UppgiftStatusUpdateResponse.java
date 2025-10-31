package se.fk.github.rimfrost.operativt.uppgiftslager.presentation.dto;

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonProperty;

@Value.Immutable
public interface UppgiftStatusUpdateResponse 
{
    @JsonProperty("uppgift")
    Uppgift uppgift();
}
