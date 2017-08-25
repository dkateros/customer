package gr.dkateros.util.jee7.java8;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * JEE7 included JPA 2.1 does not include support for Java8 date API.
 * Database columns of type DATE are normally converted as {@link Date} instances.
 * This handles the conversion to corresponding entity properties typed as {@link LocalDate}.
 */
@Converter(autoApply=true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate attribute) {
		if(attribute==null) return null;
		return Date.valueOf(attribute);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date dbData) {
		if(dbData==null) return null;
		return dbData.toLocalDate();
	}

}
