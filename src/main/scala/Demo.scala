package demo
import com.github.tototoshi.csv._
import java.io.File

val reader = CSVReader.open(new File("data/12-AirBnBLondon.csv"))

@main def hello() = println("hello, world")
   

case class Dog(name:String):
    def bark=s"warf"
    def sayName=s"my name is $name"

@main def moreThings()=
    println("Welcome to my program")
    println(reader.all());
    val d = Dog("charlie")
    d.bark