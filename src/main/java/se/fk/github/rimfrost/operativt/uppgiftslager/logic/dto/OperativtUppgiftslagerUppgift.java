package se.fk.github.rimfrost.operativt.uppgiftslager.logic.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public final class OperativtUppgiftslagerUppgift {
    public final UUID processId;
    public volatile String personnummer;
    public volatile String status;           // "Ny", "Tilldelad", "Pågående", "Avslutad", ...
    public volatile String handlaggarId;     // who reserved it
    public volatile String beskrivning;
    public volatile int version;             // optimistic counter (simple)
    public volatile OffsetDateTime skapad;
    public volatile OffsetDateTime andrad;

  public OperativtUppgiftslagerUppgift(UUID processId, String personnummer, String beskrivning) {
    this.processId = processId;
    this.personnummer = personnummer;
    this.beskrivning = beskrivning;
    this.status = "Ny";
    this.version = 0;
    this.skapad = OffsetDateTime.now();
    this.andrad = this.skapad;
  }
}
