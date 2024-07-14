package com.example.annotations
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions
import kotlin.reflect.jvm.isAccessible

// Annotation declaration
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class AllowedValues(val values: IntArray)

// Function that implements the validation on all the annotated functions so they only accept a set of values of x.
fun <T> validation(instance: T, functionName: String, vararg args: Any): Any? {
    val kClass = instance!!::class
    val function = kClass.functions.find { it.name == functionName }

    function?.let {
        val annotation = it.findAnnotation<AllowedValues>()
        if (annotation != null) {
            val xIndex = it.parameters.indexOfFirst { param -> param.name == "x" }
            if (xIndex != -1) {
                val xValue = args[xIndex - 1]
                if (xValue !is Int || xValue !in annotation.values) {
                    throw IllegalArgumentException("Invalid value for x: $xValue. Allowed values are: ${annotation.values.joinToString()}")
                }
            }
        }
        it.isAccessible = true
        return it.call(instance, *args)
    }
    throw NoSuchMethodException("Function $functionName not found in class ${kClass.simpleName}")
}
