## Interface:
Java 7 → 100% contract based → in the future, interface is introducing functionalities where are required
            for some of the classes
Java 8 → introduced default method (optional) → problem of code duplicate
Java 8 → Allow to write static method
Java 9 → introduced private method in interface. This will reduce code duplicate

## Types of Interface:
1. Normal Interface: Standard interface with multiple abstract methods, default methods, and static methods.
2. Functional Interface: Only one abstract method. (SAM: Single Abstract Method)
3. Marker/Tag Interface: Empty Interface is called marker interface.
4. Nested Interface: An interface declared inside a class or another interface, usually for better encapsulation

## Lambda Expression:
implement functions/methods without name.
    int add() → What we are going to do?
    {
        //logic → How we are going to do?
    }

## Serialization: 
    -->  Process of writing object into byte stream so that it can be transferred over the network,
         store to databases or write to file system.