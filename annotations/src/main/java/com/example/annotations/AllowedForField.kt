package com.example.annotations
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.KMutableProperty


//annotation for field
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class AllowedForField(val values: IntArray)


//reflection runs at runtime to validate the fields
fun validateAndSetField(instance: Any, fieldName: String, value: Int) {
    val kClass = instance::class
    val property = kClass.declaredMemberProperties.find { it.name == fieldName } as? KMutableProperty<*>
        ?: throw NoSuchFieldException("Field $fieldName not found in class ${kClass.simpleName}")

    val annotation = property.findAnnotation<AllowedValues>()
    if (annotation != null && value !in annotation.values) {
        throw IllegalArgumentException("Invalid value for $fieldName: $value. Allowed values are: ${annotation.values.joinToString()}")
    }

    property.isAccessible = true
    property.setter.call(instance, value)
}

