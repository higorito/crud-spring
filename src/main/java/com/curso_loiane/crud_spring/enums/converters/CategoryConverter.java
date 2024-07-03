package com.curso_loiane.crud_spring.enums.converters;

import com.curso_loiane.crud_spring.enums.Category;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true) //vai ser aplicado automaticamente pelo JPA
public class CategoryConverter implements AttributeConverter<Category, String>{
    //como vai transformar o dado do banco em objeto e vice-versa(nesse caso string pra enum e vice-versa)

    @Override
    public String convertToDatabaseColumn(Category category) {
        if (category == null) return null;
        return category.getValue(); //vai pegar o valor do enum aquele que foi passado no construtor(Backend, Frontend, Mobile)
    }

    @Override
    public Category convertToEntityAttribute(String value) {
        if (value == null) return null;
        //consegue converter qlq array de informação em stream
        //adiciona um filtro, se o valor passado for igual ao valor do enum, retorna o enum
        return Stream.of(Category.values())
                .filter(c -> c.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
