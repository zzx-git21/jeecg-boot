package org.jeecg.common.util.jsonschema;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountSerializer extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        if (value != null) {
            /**在这里定制我们需要格式化的逻辑*/
            // 比如保留六位小数，四舍五入
            BigDecimal number = value.setScale(2, RoundingMode.HALF_UP);
            gen.writeNumber(number);
        } else {
            gen.writeNumber(value);
        }

    }
}