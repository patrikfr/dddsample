package se.citerus.dddsample.domain.model.cargo;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import se.citerus.dddsample.domain.model.ValueObject;
import se.citerus.dddsample.domain.model.location.Location;
import se.citerus.dddsample.domain.shared.AbstractSpecification;

import java.util.Date;

/**
 * Route specification.
 * 
 */
public class RouteSpecification extends AbstractSpecification<Itinerary> implements ValueObject<RouteSpecification> {
  private Location origin;
  private Location destination;
  private Date arrivalDeadline;

  /**
   * Factory for creatig a route specification for a cargo, from cargo
   * origin to cargo destination. Use for initial routing.
   *
   * @param cargo cargo
   * @param arrivalDeadline arrival deadline
   * @return A route specification for this cargo and arrival deadline
   */
  public static RouteSpecification forCargo(Cargo cargo, Date arrivalDeadline) {
    Validate.notNull(cargo);
    Validate.notNull(arrivalDeadline);

    return new RouteSpecification(cargo.origin(), cargo.destination(), arrivalDeadline);
  }

  /**
   * Factory for creating a route specfication from an explicit destination,
   * for rerouting a cargo.
   *
   * @param from
   * @param cargo
   * @param arrivalDeadline
   * @return
   */
  public static RouteSpecification forReroutedCargo(Location from, Cargo cargo, Date arrivalDeadline) {
    Validate.notNull(from);
    Validate.notNull(cargo);
    Validate.notNull(arrivalDeadline);

    return new RouteSpecification(from, cargo.destination(), arrivalDeadline);
  }

  private RouteSpecification(Location origin, Location destination, Date arrivalDeadline) {
    this.origin = origin;
    this.destination = destination;
    this.arrivalDeadline = arrivalDeadline;
  }

  public Location origin() {
    return origin;
  }

  public Location destination() {
    return destination;
  }

  public boolean isSatisfiedBy(Itinerary itinerary) {
    // TODO implement
    return true;
  }

  public boolean sameValueAs(RouteSpecification other) {
    return other != null && new EqualsBuilder().
      append(this.origin, other.origin).
      append(this.destination, other.destination).
      append(this.arrivalDeadline, other.arrivalDeadline).
      isEquals();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RouteSpecification that = (RouteSpecification) o;

    return sameValueAs(that);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().
      append(this.origin).
      append(this.destination).
      append(this.arrivalDeadline).
      toHashCode();
  }

}
