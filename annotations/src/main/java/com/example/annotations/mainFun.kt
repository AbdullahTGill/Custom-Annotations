package com.example.annotations

//main function in order to test the annotations
fun main() {
    val testInstance = SimpleClass()

    try {
        validation(testInstance, "someFunction", 3, 7)
        validation(testInstance, "anotherFunction", 20)
        validation(testInstance, "someFunction", 6, 7)  // This should throw an exception
    } catch (e: Exception) {
        println(e.message)
    }
}
