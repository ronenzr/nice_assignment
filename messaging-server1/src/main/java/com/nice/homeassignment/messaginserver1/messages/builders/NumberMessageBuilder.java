package com.nice.homeassignment.messaginserver1.messages.builders;

import com.nice.homeassignment.messaginserver1.messages.NumberMessage;
import com.nice.homeassignment.messaginserver1.messages.interfaces.Message;
import com.nice.homeassignment.messaginserver1.messages.interfaces.MessageBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


@Component
public class NumberMessageBuilder implements MessageBuilder<String> {

    private static final String FRACTION_DELIMITER = "/";

    @Override
    public boolean isValid(String messageValue) {
        return StringUtils.isNumeric(messageValue) || isFraction(messageValue) || parse(messageValue) < 0F;
    }

    @Override
    public Message build(String messageValue) throws IllegalArgumentException {
        if(!isValid(messageValue)) {
            throw new IllegalArgumentException(String.format("[NumberMessage] Message value cannot be parsed correctly: %s", messageValue));
        }

        NumberMessage result = new NumberMessage();
        result.setValue(parse(messageValue));
        result.setOriginal(messageValue);
        return result;
    }


    //---

    private Float parse(String value) {
        if (value.contains(FRACTION_DELIMITER)) {
            String[] rat = value.split(FRACTION_DELIMITER);
            return Float.parseFloat(rat[0]) / Float.parseFloat(rat[1]);
        } else {
            return Float.parseFloat(value);
        }
    }

    private boolean isFraction(String ratio) {
        if (ratio.contains(FRACTION_DELIMITER)) {
            String[] rat = ratio.split(FRACTION_DELIMITER);
            return rat.length == 2 && StringUtils.isNumeric(rat[0])
                    && StringUtils.isNumeric(rat[1]) && Float.parseFloat(rat[1]) != 0;
        } else {
            return false;
        }
    }
}
