package se.fk.github.quarkustemplate.logic.dto;

import java.util.List;
import org.immutables.value.Value;

@Value.Immutable
public interface LogicAlternatives
{
   List<LogicAlternative> alternatives();
}
