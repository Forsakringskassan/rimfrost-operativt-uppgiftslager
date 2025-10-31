package se.fk.github.rimfrost.operativt.uppgiftslager.presentation.dto;

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonProperty;

@Value.Immutable
public interface UppgfitNextResponseRestDTO 
{
    @JsonProperty("uppgift_id")
    String uppgiftId();

    @JsonProperty("uppgift_beskrivning")
    String beskrivning();

    @JsonProperty("personnummer")
    String personnummer();
}
