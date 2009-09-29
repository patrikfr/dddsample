package se.citerus.dddsample.tracking.core.domain.model.shared;

import org.apache.commons.lang.Validate;
import se.citerus.dddsample.tracking.core.domain.model.handling.HandlingEvent;
import se.citerus.dddsample.tracking.core.domain.model.location.Location;
import se.citerus.dddsample.tracking.core.domain.model.voyage.Voyage;
import se.citerus.dddsample.tracking.core.domain.shared.experimental.ValueObjectSupport;

/**
 * A handling activity represents how and where a cargo can be handled,
 * and can be used to express predictions about what is expected to
 * happen to a cargo in the future.
 */
public class HandlingActivity extends ValueObjectSupport<HandlingActivity> {

  // TODO introduce something like this (?):
  // HandlingActivity.loadOnto(voyage).in(location)
  // HandlingActivity.claimIn(location)

  private final HandlingEvent.Type type;
  private final Location location;
  private final Voyage voyage;

  public HandlingActivity(final HandlingEvent.Type type, final Location location) {
    Validate.notNull(type, "Handling event type is required");
    Validate.notNull(location, "Location is required");

    this.type = type;
    this.location = location;
    this.voyage = null;
  }

  public HandlingActivity(final HandlingEvent.Type type, final Location location, final Voyage voyage) {
    Validate.notNull(type, "Handling event type is required");
    Validate.notNull(location, "Location is required");
    Validate.notNull(voyage, "Voyage is required");

    this.type = type;
    this.location = location;
    this.voyage = voyage;
  }

  public HandlingEvent.Type type() {
    return type;
  }

  public Location location() {
    return location;
  }

  public Voyage voyage() {
    return voyage;
  }

  @Override
  public String toString() {
    return type + " in " + location + (voyage != null ? ", " + voyage : "");
  }

  HandlingActivity() {
    // Needed by Hibernate
    type = null;
    location = null;
    voyage = null;
  }

}
