package fiap.com.br.hesol.domain.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class StatusAtivoConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean ativo) {
        if (ativo == null) return null;
        return ativo ? "A" : "I";
    }

    @Override
    public Boolean convertToEntityAttribute(String status) {
        if (status == null) return null;
        return "A".equalsIgnoreCase(status);
    }
}
