package com.example.annotations

//main function in order to test the annotations
fun main() {
    //create an instance of the testing class
    val testInstance = SimpleClass()

    try {
        validation(testInstance, "someFunction", 3, 7)
        validation(testInstance, "anotherFunction", 20)
        validation(testInstance, "someFunction", 6, 7)  // This should throw an exception
    } catch (e: Exception) {
        println(e.message)
    }

    //testing of the implemented field annotation

    try {
        validateAndSetField(testInstance, "someValue", 3)
        println("someValue successfully set to ${testInstance.someValue}")

        validateAndSetField(testInstance, "someValue", 6) // This should throw an exception
    } catch (e: Exception) {
        println(e.message)
    }
}




