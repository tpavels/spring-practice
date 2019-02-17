package tpavels.spring.civ.converters;

import tpavels.spring.civ.model.Location;

import javax.persistence.AttributeConverter;

public class LocationConverter implements AttributeConverter<Location, String> {

    private static final String SEPARATOR = "~";

    @Override
    public String convertToDatabaseColumn(Location loc) {
        return new StringBuffer()
                .append(String.valueOf(loc.getX()))
                .append(SEPARATOR)
                .append(String.valueOf(loc.getY()))
                .toString();
    }

    @Override
    public Location convertToEntityAttribute(String locStr) {
        String[] locSplit = locStr.split(SEPARATOR);
        return new Location(Long.valueOf(locSplit[0]), Long.valueOf(locSplit[1]));
    }
}
