package sv.edu.ues.fia.siplanilla_backend.config;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.math.BigDecimal;

/**
 * Convertidor personalizado para mapear Boolean a NUMBER(1) en Oracle.
 * - 1 = true (bloqueado)
 * - 0 = false (no bloqueado)
 * 
 * Oracle devuelve BigDecimal para NUMBER(1), así que el convertidor maneja eso.
 */
@Converter(autoApply = true)
public class BooleanToNumberConverter implements AttributeConverter<Boolean, BigDecimal> {

    @Override
    public BigDecimal convertToDatabaseColumn(Boolean attribute) {
        if (attribute == null) {
            return BigDecimal.ZERO;
        }
        return attribute ? BigDecimal.ONE : BigDecimal.ZERO;
    }

    @Override
    public Boolean convertToEntityAttribute(BigDecimal dbData) {
        if (dbData == null) {
            return false;
        }
        return dbData.intValue() == 1;
    }
}
