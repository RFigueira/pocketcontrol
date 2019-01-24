package converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@FacesConverter("localDateComDiaFacesConverter")
public class LocalDateComDiaFacesConverter implements Converter {

    private static final Locale BRAZIL = new Locale("pt","BR");
    private static final String PATTERN = "EEE dd/MM/yy";

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

            throw new ConverterException("Um erro ocorreu");
        }

        return localDate;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object localDateValue) {

        if (null == localDateValue) {

            return "";
        }

        return ((LocalDate) localDateValue).format(DateTimeFormatter.ofPattern(PATTERN, BRAZIL));
    }
}