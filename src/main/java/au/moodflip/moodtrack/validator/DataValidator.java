package au.moodflip.moodtrack.validator;

import au.moodflip.moodtrack.model.Data;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;


@Component
public class DataValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Data.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Data data = (Data) o;

        if (data.getDate() == null) {
            errors.rejectValue("date", "required.key");
            return;
        }

        if (data.getDate().after(new Date())) {
            errors.rejectValue("date", "date.can.not.future");
        }

    }
}