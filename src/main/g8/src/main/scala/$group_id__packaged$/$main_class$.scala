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


object $main_class$ {

	case class Config(
		context: String = "",
		spark_dir: String = "",
		jar_dir: String = ""
		/*
		/    Define here the variables that will be passed through the command-line
		*/
	)

	def main(args: Array[String]) {

		System.setProperty("spark.serializer", "spark.KryoSerializer")	//better but not mandatory
		System.setProperty("spark.executor.memory", "12g")	//change if needed

		val parser = new scopt.OptionParser[Config]("$name$"){
			opt[String]("context") required() action {
			  (x, c) => c.copy(context = x)
			} text("The Master's IP (if you are using a cluster) or local[n] (if you are using a local environment)")
			opt[String]("spark-dir") action {
			  (x, c) => c.copy(spark_dir = x)
			} text("The spark-dir")
			opt[String]("jar-dir") action {
			  (x, c) => c.copy(jar_dir = x)
			} text("The spark-dir")
			/*
			/     Define here how to pass the command-line parameters to the variables defined above
			*/
		}

		parser.parse(args, Config()) map {
			c => {
				println(c)
				run(c)}
			}
		}

		def run(c : Config) {

			val job_name = "$name$"

			val sc = {
			if (c.context contains "local")
				new SparkContext(c.context,job_name)
			else
				new SparkContext(c.context,job_name,c.spark_dir,Seq(c.jar_dir))
		}
		/*
		/     Your code here (use c.variable_name to get the command-line variables)
		*/
	}

}
