package se.citerus.dddsample.tracking.core.interfaces.handling;

import com.aggregator.HandlingReport;
import org.apache.commons.lang.StringUtils;
import se.citerus.dddsample.tracking.core.domain.model.cargo.TrackingId;
import se.citerus.dddsample.tracking.core.domain.model.handling.OperatorCode;
import se.citerus.dddsample.tracking.core.domain.model.location.UnLocode;
import se.citerus.dddsample.tracking.core.domain.model.shared.HandlingActivityType;
import se.citerus.dddsample.tracking.core.domain.model.voyage.VoyageNumber;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Utility methods for parsing various forms of handling report formats.
 * <p/>
 * Supports the notification pattern for incremental error reporting.
 */
public class HandlingReportParser {

  public static final String ISO_8601_FORMAT = "yyyy-MM-dd HH:mm";

  public static UnLocode parseUnLocode(final String unlocode, final List<String> errors) {
    try {
      return new UnLocode(unlocode);
    } catch (IllegalArgumentException e) {
      errors.add(e.getMessage());
      return null;
    }
  }

  public static TrackingId parseTrackingId(final String trackingId, final List<String> errors) {
    try {
      return new TrackingId(trackingId);
    } catch (IllegalArgumentException e) {
      errors.add(e.getMessage());
      return null;
    }
  }

  public static VoyageNumber parseVoyageNumber(final String voyageNumber, final List<String> errors) {
    if (StringUtils.isNotEmpty(voyageNumber)) {
      try {
        return new VoyageNumber(voyageNumber);
      } catch (IllegalArgumentException e) {
        errors.add(e.getMessage());
        return null;
      }
    } else {
      return null;
    }
  }

  public static Date parseDate(final String completionTime, final List<String> errors) {
    Date date;
    try {
      date = new SimpleDateFormat(ISO_8601_FORMAT).parse(completionTime);
    } catch (ParseException e) {
      errors.add("Invalid date format: " + completionTime + ", must be on ISO 8601 format: " + ISO_8601_FORMAT);
      date = null;
    }
    return date;
  }

  public static HandlingActivityType parseEventType(final String eventType, final List<String> errors) {
    try {
      return HandlingActivityType.valueOf(eventType);
    } catch (IllegalArgumentException e) {
      errors.add(eventType + " is not a valid handling event type. Valid types are: " + Arrays.toString(HandlingActivityType.values()));
      return null;
    }
  }

  public static Date parseCompletionTime(HandlingReport handlingReport, List<String> errors) {
    final XMLGregorianCalendar completionTime = handlingReport.getCompletionTime();
    if (completionTime == null) {
      errors.add("Completion time is required");
      return null;
    }

    return completionTime.toGregorianCalendar().getTime();
  }

  public static OperatorCode parseOperatorCode() {
    // TODO stubbed atm
    return new OperatorCode("ABCDE");
  }
}
