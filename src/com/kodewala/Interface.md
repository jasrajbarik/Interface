# Java Interfaces

## 1. What is an Interface in Java?

An **interface** in Java is a reference type that defines a contract that classes can implement.

It specifies what functionality a class must provide, without necessarily specifying how that functionality should be implemented.

An interface is primarily used to achieve:

1. Abstraction.
2. Loose coupling.
3. Multiple inheritance of type.
4. Polymorphism.
5. Standardization of behaviour across different classes.

**Real-world example:**

Consider a payment system.

Different payment methods can perform a payment:

- Credit Card
- UPI
- Debit Card
- Net Banking

Although their internal payment processes are different, all of them should provide a payment operation.

We can define a common interface called `Payment`.

```java
interface Payment {

    void pay(double amount);

}
```

Different classes can implement this interface.

```java
class UpiPayment implements Payment {

    public void pay(double amount) {

        System.out.println("Payment through UPI: " + amount);

    }
}

class CardPayment implements Payment {

    public void pay(double amount) {

        System.out.println("Payment through Card: " + amount);

    }
}
```

Main class:

```java
public class Driver {

    public static void main(String[] args) {

        UpiPayment upi = new UpiPayment();

        upi.pay(500);

        CardPayment card = new CardPayment();

        card.pay(1000);

    }
}
```

**Output:**

```text
Payment through UPI: 500.0
Payment through Card: 1000.0
```

Both classes provide their own implementation of the same interface method.

This is one of the main purposes of interfaces.

---

## 2. How to Declare an Interface?

We use the `interface` keyword to declare an interface.

**Syntax:**

```java
interface InterfaceName {

    // Constants

    // Abstract methods

    // Default methods

    // Static methods

    // Private methods

}
```

Example:

```java
interface Vehicle {

    void start();

    void stop();

}
```

Here:

- `Vehicle` is an interface.
- `start()` is an abstract method.
- `stop()` is an abstract method.

A class implements an interface using the `implements` keyword.

```java
class Car implements Vehicle {

    public void start() {

        System.out.println("Car started");

    }

    public void stop() {

        System.out.println("Car stopped");

    }
}
```

**Important:** A concrete class implementing an interface must provide implementations for all inherited abstract methods unless the class itself is declared abstract.

---

## 3. Important Characteristics of Interfaces

1. An interface is declared using the `interface` keyword.
2. An interface cannot be instantiated directly.
3. A class implements an interface using the `implements` keyword.
4. A class can implement multiple interfaces.
5. An interface can extend another interface.
6. An interface can extend multiple interfaces.
7. Interfaces support abstraction.
8. Interface reference variables can refer to objects of implementing classes.
9. Interface methods and fields have specific implicit modifiers.
10. An interface does not have constructors.

Example:

```java
interface Animal {

    void eat();

}
```

The following is invalid:

```java
Animal animal = new Animal(); // Compilation error
```

Why?

Because an interface cannot be instantiated directly.

However, the following is valid:

```java
Animal animal = new Dog();
```

Here, `Dog` must implement the `Animal` interface.

---

## 4. Interface Fields (Variables)

Variables declared inside an interface are implicitly:

- `public`
- `static`
- `final`

Therefore, every interface variable is a constant.

Example:

```java
interface Vehicle {

    int MAX_SPEED = 120;

}
```

The above declaration is equivalent to:

```java
interface Vehicle {

    public static final int MAX_SPEED = 120;

}
```

We can access this variable using the interface name.

```java
public class Driver {

    public static void main(String[] args) {

        System.out.println(Vehicle.MAX_SPEED);

    }
}
```

**Output:**

```text
120
```

### Important Rules

1. Interface variables are automatically public, static and final.
2. They must be initialized when declared.
3. They cannot be reassigned.
4. They belong to the interface, not to individual implementing objects.
5. They can be accessed using the interface name.

Example of an invalid operation:

```java
interface Vehicle {

    int MAX_SPEED = 120;

}

public class Driver {

    public static void main(String[] args) {

        Vehicle.MAX_SPEED = 150; // Compilation error

    }
}
```

Why?

Because `MAX_SPEED` is implicitly final.

---

## 5. Types of Methods in Interfaces

The methods available in interfaces evolved across different Java versions.

Understanding Java 7, Java 8 and Java 9 is particularly important.

### 5.1 Interface Methods Before Java 8 (Java 7 and Earlier)

Before Java 8, interfaces primarily contained abstract methods.

These methods were implicitly:

- `public`
- `abstract`

Example:

```java
interface Animal {

    void eat();

    void sleep();

}
```

The above interface is equivalent to:

```java
interface Animal {

    public abstract void eat();

    public abstract void sleep();

}
```

The implementing class must provide the method implementations.

```java
class Dog implements Animal {

    public void eat() {

        System.out.println("Dog eats food");

    }

    public void sleep() {

        System.out.println("Dog sleeps");

    }
}
```

**Important:** The implementation methods must be public because interface abstract methods are implicitly public. We cannot reduce their visibility to protected or private.

### 5.2 Default Methods (Introduced in Java 8)

Java 8 introduced default methods in interfaces.

A default method is a method that has a method body inside an interface.

It is declared using the `default` keyword.

**Syntax:**

```java
interface InterfaceName {

    default void methodName() {

        // Method implementation

    }

}
```

Example:

```java
interface Vehicle {

    void start();

    default void fuelType() {

        System.out.println("Vehicle requires fuel");

    }
}
```

Implementation:

```java
class Car implements Vehicle {

    public void start() {

        System.out.println("Car started");

    }
}

class ElectricCar implements Vehicle {

    public void start() {

        System.out.println("Electric car started");

    }
}
```

Main class:

```java
public class Driver {

    public static void main(String[] args) {

        Car car = new Car();

        car.start();

        car.fuelType();

        ElectricCar electricCar = new ElectricCar();

        electricCar.start();

        electricCar.fuelType();

    }
}
```

**Output:**

```text
Car started
Vehicle requires fuel
Electric car started
Vehicle requires fuel
```

Notice that neither implementing class needs to override `fuelType()`.

They automatically inherit the default implementation from the interface.

### Why Were Default Methods Introduced?

Before Java 8, adding a new abstract method to an existing interface could break existing implementing classes.

Suppose we already have:

```java
interface Vehicle {

    void start();

}
```

And multiple classes implement it.

```java
class Car implements Vehicle {

    public void start() {

        System.out.println("Car started");

    }
}
```

Now suppose we add a new abstract method:

```java
interface Vehicle {

    void start();

    void stop();

}
```

The existing `Car` class will now fail to compile until it implements `stop()`.

Default methods help solve this problem.

We can add a new method with an existing implementation:

```java
interface Vehicle {

    void start();

    default void stop() {

        System.out.println("Vehicle stopped");

    }
}
```

Existing implementing classes can continue working without implementing the new method.

**Main purpose:** Default methods support backward compatibility and allow interfaces to evolve without forcing every existing implementation to change.

### Can We Override a Default Method?

Yes.

An implementing class can override a default method.

Example:

```java
interface Vehicle {

    default void start() {

        System.out.println("Vehicle starts");

    }
}

class Car implements Vehicle {

    @Override
    public void start() {

        System.out.println("Car starts with a key");

    }
}

public class Driver {

    public static void main(String[] args) {

        Car car = new Car();

        car.start();

    }
}
```

**Output:**

```text
Car starts with a key
```

The class implementation takes precedence over the interface's default implementation.

### Can We Call the Original Default Method?

Yes. An implementing class can use `InterfaceName.super.methodName()` to invoke an inherited default implementation.

Example:

```java
interface Vehicle {

    default void start() {

        System.out.println("Vehicle starts");

    }
}

class Car implements Vehicle {

    @Override
    public void start() {

        System.out.println("Car starts");

        Vehicle.super.start();

    }
}
```

**Output:**

```text
Car starts
Vehicle starts
```

---

### 5.3 Static Methods in Interfaces (Introduced in Java 8)

Java 8 also introduced static methods inside interfaces.

A static method belongs to the interface itself, not to its implementing class objects.

**Syntax:**

```java
interface InterfaceName {

    static void methodName() {

        // Method implementation

    }

}
```

Example:

```java
interface Calculator {

    static int add(int a, int b) {

        return a + b;

    }

}
```

Calling the method:

```java
public class Driver {

    public static void main(String[] args) {

        int result = Calculator.add(10, 20);

        System.out.println(result);

    }
}
```

**Output:**

```text
30
```

### Important Rules of Static Methods

1. Static methods belong to the interface.
2. They are called using the interface name.
3. They are not inherited as instance methods by implementing classes.
4. They cannot be overridden by implementing classes.
5. A class can declare a separate static method with the same signature, but it does not override the interface's static method.

Example:

```java
interface Vehicle {

    static void display() {

        System.out.println("Interface static method");

    }
}

class Car implements Vehicle {

    static void display() {

        System.out.println("Car static method");

    }
}

public class Driver {

    public static void main(String[] args) {

        Vehicle.display();

        Car.display();

    }
}
```

**Output:**

```text
Interface static method
Car static method
```

These are two separate static methods.

The second method does not override the first one.

---

### 5.4 Private Methods in Interfaces (Introduced in Java 9)

Java 9 introduced private methods inside interfaces.

Private interface methods are used to avoid duplicating common logic across default and static methods.

A private method cannot be accessed directly by implementing classes.

It is intended for internal use within the interface.

**Syntax:**

```java
private void methodName() {

    // Common implementation

}
```

Example:

```java
interface Vehicle {

    default void start() {

        commonMessage();

        System.out.println("Vehicle started");

    }

    default void stop() {

        commonMessage();

        System.out.println("Vehicle stopped");

    }

    private void commonMessage() {

        System.out.println("Vehicle operation");

    }
}
```

Implementation:

```java
class Car implements Vehicle {

}
```

Main class:

```java
public class Driver {

    public static void main(String[] args) {

        Car car = new Car();

        car.start();

        car.stop();

    }
}
```

**Output:**

```text
Vehicle operation
Vehicle started
Vehicle operation
Vehicle stopped
```

Notice that both default methods use the same private method.

This avoids repeating the common logic.

### Why Were Private Methods Introduced?

Consider an interface with multiple default methods.

Without private methods, common logic may need to be repeated in each default method.

Private methods allow us to extract that common logic into one method.

### Important Rules of Private Methods

1. Private interface methods were introduced in Java 9.
2. They must have a method body.
3. They cannot be accessed directly by implementing classes.
4. They cannot be overridden.
5. They can be called from other methods within the same interface.
6. They can be static or non-static.
7. A private static method can be called from static or other suitable interface methods.
8. A private non-static method can be called from instance methods such as default methods.

Example of a private static method:

```java
interface Calculator {

    static int add(int a, int b) {

        return calculate(a, b);

    }

    private static int calculate(int a, int b) {

        return a + b;

    }
}
```

Calling it:

```java
public class Driver {

    public static void main(String[] args) {

        System.out.println(Calculator.add(10, 20));

    }
}
```

**Output:**

```text
30
```

We cannot call `calculate()` directly from outside the interface because it is private.

---

## 6. Complete Evolution of Interfaces: Java 7, 8 and 9

This is an important interview topic.

| Feature                       | Java 7 and Earlier | Java 8 | Java 9 |
|-------------------------------|--------------------|--------|--------|
| Abstract methods              | Yes                | Yes    | Yes    |
| Default methods               | No                 | Yes    | Yes    |
| Static methods                | No                 | Yes    | Yes    |
| Private instance methods      | No                 | No     | Yes    |
| Private static methods        | No                 | No     | Yes    |
| Public static final variables | Yes                | Yes    | Yes    |

### Java 7

Interfaces primarily supported:

- Constants.
- Public abstract methods.

### Java 8

Introduced:

- Default methods.
- Static methods.

### Java 9

Introduced:

- Private instance methods.
- Private static methods.

**Why is this evolution important?**

It made interfaces more flexible while maintaining backward compatibility.

---

## 7. Implementing Multiple Interfaces

One of the most important features of interfaces is that a class can implement multiple interfaces.

Java does not support multiple inheritance of classes, but it supports implementing multiple interfaces.

Example:

```java
interface Camera {

    void takePhoto();

}

interface MusicPlayer {

    void playMusic();

}

interface GPS {

    void navigate();

}
```

A class can implement all three interfaces.

```java
class SmartPhone implements Camera, MusicPlayer, GPS {

    public void takePhoto() {

        System.out.println("Taking photo");

    }

    public void playMusic() {

        System.out.println("Playing music");

    }

    public void navigate() {

        System.out.println("Navigation started");

    }
}
```

Main class:

```java
public class Driver {

    public static void main(String[] args) {

        SmartPhone phone = new SmartPhone();

        phone.takePhoto();

        phone.playMusic();

        phone.navigate();

    }
}
```

**Output:**

```text
Taking photo
Playing music
Navigation started
```

### Important Points

1. A class can implement multiple interfaces.
2. The `implements` keyword is used.
3. The interface names are separated by commas.
4. A concrete class must implement all inherited abstract methods.
5. This allows a class to follow multiple contracts.

---

## 8. Multiple Inheritance Using Interfaces

Java does not allow a class to extend multiple classes.

For example, this is invalid:

```java
class C extends A, B {

}
```

However, Java allows an interface to extend multiple interfaces.

Example:

```java
interface Camera {

    void takePhoto();

}

interface MusicPlayer {

    void playMusic();

}

interface SmartDevice extends Camera, MusicPlayer {

    void connectToInternet();

}
```

Now a class can implement the child interface:

```java
class SmartPhone implements SmartDevice {

    public void takePhoto() {

        System.out.println("Taking photo");

    }

    public void playMusic() {

        System.out.println("Playing music");

    }

    public void connectToInternet() {

        System.out.println("Connected to internet");

    }
}
```

The `SmartPhone` class must implement all three abstract methods.

### Important Difference

| Class Inheritance                                   | Interface Inheritance                              |
|-----------------------------------------------------|----------------------------------------------------|
| A class extends one class.                          | An interface can extend multiple interfaces.       |
| Uses `extends`.                                     | Uses `extends`.                                    |
| A class implements an interface using `implements`. | An interface does not implement another interface. |

---

## 9. What Happens When Two Interfaces Have the Same Default Method?

This is an important interview question.

Suppose two interfaces contain default methods with the same signature.

Example:

```java
interface A {

    default void display() {

        System.out.println("Display from A");

    }
}

interface B {

    default void display() {

        System.out.println("Display from B");

    }
}
```

Now a class implements both interfaces:

```java
class C implements A, B {

}
```

This produces a compilation error because Java cannot decide which default implementation should be used.

### How Do We Resolve This?

The implementing class must override the conflicting method.

```java
class C implements A, B {

    @Override
    public void display() {

        System.out.println("Display from C");

    }
}
```

Main class:

```java
public class Driver {

    public static void main(String[] args) {

        C obj = new C();

        obj.display();

    }
}
```

**Output:**

```text
Display from C
```

### Can We Call Both Interface Default Methods?

Yes.

We can explicitly invoke the required default implementation using `InterfaceName.super.methodName()`.

Example:

```java
class C implements A, B {

    @Override
    public void display() {

        A.super.display();

        B.super.display();

        System.out.println("Display from C");

    }
}
```

**Output:**

```text
Display from A
Display from B
Display from C
```

### Important Rule

When two unrelated interfaces provide conflicting default methods, the implementing class must resolve the conflict by overriding the method.

---

## 10. Rules for Resolving Default Method Conflicts

Java follows important rules when multiple inheritance and default methods are involved.

### Rule 1: Class Method Wins Over Interface Default Method

If a superclass provides an instance method and an interface provides a default method with the same signature, the superclass method takes priority.

Example:

```java
class Parent {

    public void display() {

        System.out.println("Parent class method");

    }
}

interface A {

    default void display() {

        System.out.println("Interface default method");

    }
}

class Child extends Parent implements A {

}
```

Main class:

```java
public class Driver {

    public static void main(String[] args) {

        Child obj = new Child();

        obj.display();

    }
}
```

**Output:**

```text
Parent class method
```

The inherited class method wins over the interface default method.

### Rule 2: More Specific Interface Wins

Suppose one interface extends another interface and both provide a default method with the same signature.

The more specific child interface's default method takes priority.

Example:

```java
interface A {

    default void display() {

        System.out.println("Display from A");

    }
}

interface B extends A {

    @Override
    default void display() {

        System.out.println("Display from B");

    }
}

class C implements B {

}
```

Main class:

```java
public class Driver {

    public static void main(String[] args) {

        C obj = new C();

        obj.display();

    }
}
```

**Output:**

```text
Display from B
```

Because `B` extends `A`, it is more specific.

### Rule 3: Unrelated Interfaces Require Explicit Resolution

If two unrelated interfaces contain the same default method, the implementing class must override it.

This is explained in Section 9.

---

## 11. Interface Reference and Object Creation

An interface cannot be instantiated directly, but we can create an interface reference.

This is closely related to upcasting and runtime polymorphism.

Example:

```java
interface Animal {

    void sound();

}

class Dog implements Animal {

    public void sound() {

        System.out.println("Dog barks");

    }

}

class Cat implements Animal {

    public void sound() {

        System.out.println("Cat meows");

    }

}
```

Main class:

```java
public class Driver {

    public static void main(String[] args) {

        Animal animal;

        animal = new Dog();

        animal.sound();

        animal = new Cat();

        animal.sound();

    }
}
```

**Output:**

```text
Dog barks
Cat meows
```

### How Does This Work?

Consider:

```java
Animal animal = new Dog();
```

Here:

- `Animal` is the reference type.
- `animal` is the reference variable.
- `new Dog()` creates the actual object.
- `Dog` implements `Animal`.

The reference type determines which methods can be accessed at compile time.

The actual object type determines which overridden implementation executes at runtime.

For example:

```java
animal.sound();
```

The JVM executes the `sound()` implementation belonging to the actual `Dog` object.

When the reference points to a `Cat` object, the `Cat` implementation executes instead.

This is runtime polymorphism.

### Important Rule

An interface reference can access:

1. Abstract methods declared in the interface.
2. Default methods declared in the interface.
3. Methods inherited from `Object`, subject to normal Java access rules.

It cannot directly access class-specific methods that are not declared in the interface.

Example:

```java
interface User {

    void userClass();

}

class AdminUser implements User {

    public void userClass() {

        System.out.println("User method");

    }

    public void adminClass() {

        System.out.println("Admin method");

    }
}
```

Now:

```java
User user = new AdminUser();

user.userClass();  // Allowed

// user.adminClass(); // Compilation error
```

Why?

Because `adminClass()` is not declared in the `User` interface.

---

## 12. Interface and Abstract Class: Differences

Both interfaces and abstract classes are used to achieve abstraction, but they have different purposes.

| Feature              | Interface                                                       | Abstract Class                       |
|----------------------|-----------------------------------------------------------------|--------------------------------------|
| Declaration          | `interface`                                                     | `abstract class`                     |
| Object creation      | Cannot be instantiated                                          | Cannot be instantiated               |
| Constructors         | Not allowed                                                     | Allowed                              |
| Instance variables   | Not allowed as ordinary instance fields                         | Allowed                              |
| Constants            | Implicitly public static final                                  | Can have constants and normal fields |
| Abstract methods     | Allowed                                                         | Allowed                              |
| Concrete methods     | Default, static and private methods                             | Normal concrete methods              |
| Multiple inheritance | A class can implement multiple interfaces                       | A class can extend only one class    |
| Inheritance keyword  | `implements` / `extends`                                        | `extends`                            |
| Access modifiers     | Public abstract methods; other method types have specific rules | Normal access modifiers              |
| State management     | No ordinary per-object instance state                           | Can maintain object state            |

### When Should We Use an Interface?

Use an interface when:

- Different classes should follow a common contract.
- Multiple unrelated classes need common behaviour definitions.
- Multiple inheritance of type is required.
- You want loose coupling.
- You want to support interchangeable implementations.

### When Should We Use an Abstract Class?

Use an abstract class when:

- Related classes share common state.
- Related classes share common implementation.
- Constructors are required.
- You need protected or private instance fields and methods.
- You want to provide a common base implementation.

---

## 13. Functional Interface

A functional interface is an interface that contains exactly one abstract method.

It can contain:

- One abstract method.
- Multiple default methods.
- Multiple static methods.
- Private methods.

It may also contain methods corresponding to public methods of `Object`, without violating the functional-interface rule.

Functional interfaces are used extensively with lambda expressions and the Java Stream API.

### Example

```java
@FunctionalInterface
interface Calculator {

    int add(int a, int b);

}
```

Implementation using a normal class:

```java
class Addition implements Calculator {

    public int add(int a, int b) {

        return a + b;

    }
}
```

Main class:

```java
public class Driver {

    public static void main(String[] args) {

        Calculator calculator = new Addition();

        System.out.println(calculator.add(10, 20));

    }
}
```

**Output:**

```text
30
```

### Functional Interface Using Lambda Expression

Instead of creating a separate implementation class, we can use a lambda expression.

```java
public class Driver {

    public static void main(String[] args) {

        Calculator calculator = (a, b) -> a + b;

        System.out.println(calculator.add(10, 20));

    }
}
```

**Output:**

```text
30
```

### What is @FunctionalInterface?

`@FunctionalInterface` is an annotation used to indicate that an interface is intended to be a functional interface.

If we accidentally add another abstract method, the compiler reports an error.

Example:

```java
@FunctionalInterface
interface Calculator {

    int add(int a, int b);

    int subtract(int a, int b); // Compilation error

}
```

---

## 14. Important Built-in Functional Interfaces

Java provides several built-in functional interfaces in the `java.util.function` package.

| Interface             | Abstract Method   | Purpose                                |
|-----------------------|-------------------|----------------------------------------|
| `Predicate<T>`        | `test(T t)`       | Checks a condition and returns boolean |
| `Function<T, R>`      | `apply(T t)`      | Takes input and returns a result       |
| `Consumer<T>`         | `accept(T t)`     | Accepts input and returns nothing      |
| `Supplier<T>`         | `get()`           | Supplies a value without input         |
| `BiFunction<T, U, R>` | `apply(T t, U u)` | Takes two inputs and returns a result  |

### Predicate Example

```java
import java.util.function.Predicate;

public class Driver {

    public static void main(String[] args) {

        Predicate<Integer> check = number -> number > 10;

        System.out.println(check.test(20));

    }
}
```

**Output:**

```text
true
```

### Function Example

```java
import java.util.function.Function;

public class Driver {

    public static void main(String[] args) {

        Function<Integer, Integer> square = number -> number * number;

        System.out.println(square.apply(5));

    }
}
```

**Output:**

```text
25
```

### Consumer Example

```java
import java.util.function.Consumer;

public class Driver {

    public static void main(String[] args) {

        Consumer<String> display = name -> System.out.println(name);

        display.accept("Jasraj");

    }
}
```

### Supplier Example

```java
import java.util.function.Supplier;

public class Driver {

    public static void main(String[] args) {

        Supplier<String> message = () -> "Hello Java";

        System.out.println(message.get());

    }
}
```

---

## 15. Marker Interface

A marker interface is an interface that does not contain any abstract methods or constants.

It is used to mark a class as having a particular property or capability.

The Java runtime or a framework can recognize the marker and apply special behaviour.

### Examples

1. `Serializable`
2. `Cloneable`
3. `Remote`

### Example: Serializable

```java
import java.io.Serializable;

class Student implements Serializable {

    int id;

    String name;

}
```

Here, `Serializable` marks the `Student` class as eligible for Java's serialization mechanism, subject to the serialization rules.

**Important:** A marker interface communicates metadata through the type system. It does not automatically implement serialization logic.

---

## 16. Nested Interfaces

An interface can be declared inside another class or interface.

Such an interface is called a nested interface.

### Example

```java
class Outer {

    interface Inner {

        void display();

    }

}

class Test implements Outer.Inner {

    public void display() {

        System.out.println("Nested interface");

    }

}
```

Main class:

```java
public class Driver {

    public static void main(String[] args) {

        Outer.Inner obj = new Test();

        obj.display();

    }
}
```

**Output:**

```text
Nested interface
```

### Important Points

1. A nested interface is accessed using the enclosing type name.
2. A nested interface declared inside a class is implicitly static.
3. An interface declared inside another interface is implicitly public and static.
4. A nested interface can be implemented by an external class.

---

## 17. Can an Interface Extend Another Interface?

Yes.

An interface uses the `extends` keyword to inherit another interface.

Example:

```java
interface Animal {

    void eat();

}

interface Pet extends Animal {

    void play();

}

class Dog implements Pet {

    public void eat() {

        System.out.println("Dog eats");

    }

    public void play() {

        System.out.println("Dog plays");

    }

}
```

Here:

- `Pet` extends `Animal`.
- `Dog` implements `Pet`.
- `Dog` must implement both `eat()` and `play()`.

This is because `Pet` inherits the abstract method from `Animal`.

---

## 18. Can an Interface Extend Multiple Interfaces?

Yes.

An interface can extend multiple interfaces.

Example:

```java
interface Camera {

    void takePhoto();

}

interface MusicPlayer {

    void playMusic();

}

interface SmartPhone extends Camera, MusicPlayer {

    void connectInternet();

}

class Phone implements SmartPhone {

    public void takePhoto() {

        System.out.println("Taking photo");

    }

    public void playMusic() {

        System.out.println("Playing music");

    }

    public void connectInternet() {

        System.out.println("Internet connected");

    }

}
```

Here, `SmartPhone` inherits the abstract methods of both `Camera` and `MusicPlayer`.

The `Phone` class implements all three methods.

---

## 19. Important Rules of Interface Methods

| Method Type             | Keyword                     | Method Body | Can Be Overridden by Implementing Class?     |
|-------------------------|-----------------------------|-------------|----------------------------------------------|
| Abstract method         | No special keyword required | No          | Yes, must be implemented by a concrete class |
| Default method          | `default`                   | Yes         | Yes                                          |
| Static method           | `static`                    | Yes         | No, not as an override                       |
| Private instance method | `private`                   | Yes         | No                                           |
| Private static method   | `private static`            | Yes         | No                                           |

### Important Additional Rules

1. Interface abstract methods are implicitly public.
2. Default methods are implicitly public.
3. Interface static methods are implicitly public unless declared private.
4. Private interface methods cannot be accessed outside the interface.
5. Private methods must have a method body.
6. Interface variables are implicitly public, static and final.
7. An interface cannot have a constructor.
8. A class can implement multiple interfaces.
9. An interface can extend multiple interfaces.
10. A concrete class must implement all inherited abstract methods.

---

## 20. Common Compilation Errors in Interfaces

### Case 1: Trying to Create an Interface Object

```java
interface Animal {

    void eat();

}

public class Driver {

    public static void main(String[] args) {

        Animal animal = new Animal(); // Compilation error

    }
}
```

**Reason:** An interface cannot be instantiated directly.

### Case 2: Implementing an Abstract Method Without public

```java
interface Animal {

    void eat();

}

class Dog implements Animal {

    void eat() {

        System.out.println("Dog eats");

    }
}
```

**Reason:** The implementation method has package-private access, but the interface method is implicitly public.

Correct version:

```java
class Dog implements Animal {

    public void eat() {

        System.out.println("Dog eats");

    }
}
```

### Case 3: Modifying an Interface Variable

```java
interface Vehicle {

    int SPEED = 100;

}

public class Driver {

    public static void main(String[] args) {

        Vehicle.SPEED = 150; // Compilation error

    }
}
```

**Reason:** Interface variables are final.

### Case 4: Calling a Static Interface Method Through an Object

```java
interface Vehicle {

    static void display() {

        System.out.println("Vehicle");

    }
}
```

Use:

```java
Vehicle.display();
```

Static interface methods are called using the interface name, not through an implementing-class object.

### Case 5: Conflicting Default Methods

If two unrelated interfaces provide the same default method, a class implementing both must override that method to resolve the conflict.

---

## 21. Important Interview Questions

**Q1. What is an interface in Java?**

An interface is a reference type that defines a contract that implementing classes must follow.

**Q2. Why do we use interfaces?**

For abstraction, loose coupling, polymorphism, standardization and multiple inheritance of type.

**Q3. Can we create an object of an interface?**

No. We cannot instantiate an interface directly, but we can create an interface reference pointing to an implementing-class object.

**Q4. Can an interface have a constructor?**

No. Interfaces cannot have constructors.

**Q5. Can an interface contain variables?**

Yes. Interface variables are implicitly public, static and final.

**Q6. Can an interface contain a method with a body?**

Yes. Default, static and private methods can contain method bodies.

**Q7. Can a class implement multiple interfaces?**

Yes. A class can implement multiple interfaces using the `implements` keyword.

**Q8. Can an interface extend multiple interfaces?**

Yes. An interface can extend multiple interfaces using the `extends` keyword.

**Q9. Can a default method be overridden?**

Yes. An implementing class can override a default method.

**Q10. Can a static interface method be overridden?**

No. Static methods belong to the interface itself and are not inherited as instance methods by implementing classes.

**Q11. Can an interface contain private methods?**

Yes. Java 9 introduced private instance and private static methods.

**Q12. Why were default methods introduced?**

To allow interfaces to evolve while maintaining backward compatibility with existing implementing classes.

**Q13. Why were private methods introduced in interfaces?**

To reuse common logic inside default and static methods without exposing helper methods to implementing classes.

**Q14. What happens when two interfaces have the same default method?**

The implementing class must override the conflicting method and resolve the ambiguity.

**Q15. What is a functional interface?**

An interface with exactly one abstract method. It can be implemented using lambda expressions.

**Q16. What is a marker interface?**

An interface without abstract methods or constants that marks a class as having a particular property or capability.

**Q17. What is the difference between `extends` and `implements`?**

- `extends` is used by a class to inherit another class or by an interface to inherit another interface.
- `implements` is used by a class to implement an interface.

**Q18. Can an abstract class implement an interface without implementing all its methods?**

Yes. An abstract class can leave some or all inherited abstract methods unimplemented. A concrete subclass must implement the remaining abstract methods.

**Q19. Can an interface extend an abstract class?**

No. An interface can extend only other interfaces.

**Q20. Can an interface extend another interface containing default methods?**

Yes. It inherits the accessible interface methods according to Java's inheritance rules.

---

## 22. Final Revision Table

| Concept                        | Key Point                                         |
|--------------------------------|---------------------------------------------------|
| Interface                      | Defines a contract                                |
| `interface`                    | Keyword used to declare an interface              |
| `implements`                   | Used by a class to implement an interface         |
| `extends`                      | Used by an interface to inherit another interface |
| Abstract method                | Implicitly public and abstract                    |
| Interface variable             | Implicitly public, static and final               |
| Default method                 | Introduced in Java 8                              |
| Static method                  | Introduced in Java 8                              |
| Private method                 | Introduced in Java 9                              |
| Private static method          | Introduced in Java 9                              |
| Multiple interfaces            | A class can implement multiple interfaces         |
| Multiple interface inheritance | An interface can extend multiple interfaces       |
| Functional interface           | Contains exactly one abstract method              |
| Marker interface               | Contains no abstract methods                      |
| Nested interface               | Interface declared inside another type            |
| Default conflict               | Must be resolved by implementing class            |

## 23. Conclusion

Interfaces are one of the most important concepts in Java's object-oriented programming model.

They provide abstraction and allow different classes to follow a common contract without requiring them to share the same implementation.

Java 8 introduced default and static methods, while Java 9 introduced private methods. These improvements made interfaces more powerful and flexible.

Understanding interfaces is essential for working with:

- Collections Framework.
- Lambda expressions.
- Functional programming.
- Java Stream API.
- Spring Framework.
- Dependency Injection.
- Loose coupling and application architecture.

A strong understanding of interfaces is particularly important for Java backend development.



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