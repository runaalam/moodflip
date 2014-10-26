package au.moodflip.moodtrack.validator;

import au.moodflip.moodtrack.model.ReportCmd;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

@Component
public class ReportValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ReportCmd.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ReportCmd reportCmd = (ReportCmd) o;

        if (reportCmd.getStartDate() == null) {
            errors.rejectValue("startDate", "start.date.required");
            return;
        }

        Date endDate = reportCmd.getEndDate() != null ? reportCmd.getEndDate() : new Date();

        if (endDate.before(reportCmd.getStartDate())) {
            errors.reject("start.date.after.end");
        }

        int reportRange = Days
                .daysBetween(new DateTime(reportCmd.getStartDate()), new DateTime(endDate))
                .getDays();

        if (reportRange < 7) {
            errors.reject("report.min.date.range");
        }

        if (reportRange > 31) {
            errors.reject("report.max.date.range");
        }

    }
}
