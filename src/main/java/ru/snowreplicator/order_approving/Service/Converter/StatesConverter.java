package ru.snowreplicator.order_approving.Service.Converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.snowreplicator.order_approving.Service.Common.States;

@Converter(autoApply = true)
public class StatesConverter implements AttributeConverter<States, String> {

    private static final Logger logger = LoggerFactory.getLogger(StatesConverter.class);

    @Override
    public String convertToDatabaseColumn(States state) {
        return state.name();
    }

    @Override
    public States convertToEntityAttribute(String state) {
        try {
            return States.valueOf(state);
        } catch (Exception e) {
            logger.error("Wrong state");
            return States.DRAFT;
        }
    }

}
