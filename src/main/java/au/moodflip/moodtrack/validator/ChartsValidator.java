package au.moodflip.moodtrack.validator;

import au.moodflip.moodtrack.model.Charts;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

@Component
public class ChartsValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Charts.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Charts charts = (Charts) o;

        if (charts.getStartDate() == null) {
            errors.rejectValue("startDate", "start.date.required");
            return;
        }

        Date endDate = charts.getEndDate() != null ? charts.getEndDate() : new Date();

        if (endDate.before(charts.getStartDate())) {
            errors.reject("start.date.after.end");
        }

        int chartsRange = Days
                .daysBetween(new DateTime(charts.getStartDate()), new DateTime(endDate))
                .getDays();

        if (chartsRange != 6) {
            errors.reject("chart.date.range");
        }

    }
}
