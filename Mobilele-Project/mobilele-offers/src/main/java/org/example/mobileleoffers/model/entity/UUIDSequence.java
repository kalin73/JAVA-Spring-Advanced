package org.example.mobileleoffers.model.entity;

import org.hibernate.annotations.ValueGenerationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@ValueGenerationType(generatedBy = UUIDSequenceGenerator.class)
public @interface UUIDSequence {

}
