package com.example.annotations


// A sample class to demonstrate the annotations on methods
class SimpleClass {

    @AllowedValues(values = [1, 2, 3, 4, 5])
    fun someFunction(x: Int, y: Int) {
        println("Function called with x = $x and y = $y")
    }

    @AllowedValues(values = [10, 20, 30])
    fun anotherFunction(x: Int) {
        println("Another function called with x = $x")
    }

}