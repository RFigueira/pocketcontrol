package converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@FacesConverter("localDateFacesConverter")
public class LocalDateFacesConverter implements Converter {

    private static final String PATTERN = "dd/MM/yyyy";

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String stringValue) {

        if (null == stringValue || stringValue.isEmpty()) {
            return null;
        }

        LocalDate localDate;

        try {
            localDate = LocalDate.parse(
                    stringValue,
                    DateTimeFormatter.ofPattern(PATTERN));

        } catch (DateTimeParseException e) {

            throw new ConverterException("Digite a data como no exemplo: 13/11/2015.");
        }

        return localDate;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object localDateValue) {

        if (null == localDateValue) {

            return "";
        }
        return ((LocalDate) localDateValue).format(DateTimeFormatter.ofPattern(PATTERN));
    }
}