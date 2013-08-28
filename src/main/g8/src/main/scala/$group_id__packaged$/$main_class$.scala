package $group_id$.$name$

import spark.SparkContext
import SparkContext._
import spark._
import com.esotericsoftware.kryo.Kryo


/*

//Class Registration

class MyRegistrator extends spark.KryoRegistrator {
  override def registerClasses(kryo: Kryo) {
    kryo.register(classOf[MyClass1])
    kryo.register(classOf[MyClass2])
  }
}
*/


object $main_class$ extends App {
	System.setProperty("spark.serializer", "spark.KryoSerializer")	//better but not mandatory
	System.setProperty("spark.executor.memory", "12g")	//change if needed
	val sc = new SparkContext("spark://INSERT_YOUR_IP:7077", "$name$","/home/hadoop/spark/", List($name$-$version$-shaded.jar))
}
