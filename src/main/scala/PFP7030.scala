import org.apache.spark.ml.fpm.FPGrowth

import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature._
import org.apache.spark.sql.functions._
import scala.collection.JavaConverters._
import java.io.File
import java.text.DateFormat.{LONG, getDateInstance}
import java.util.{Date, Locale}

import org.apache.spark.sql.{DataFrame, DataFrameReader}

import scala.collection.mutable.ListBuffer
import scala.io.Source
import org.apache.spark.sql._
import scala.math



object PFP7030 extends App{


  val spark = SparkSession
    .builder()
    .appName("PFP")
    .master("local")
    .getOrCreate()

  import spark.implicits._


  print("\n \n \n")
  println(" -*-*-*-*  THIS IS PARALLEL FP-GROWTH Implementation *-*-*-*-")
  println()

  val fn_itemset = scala.io.StdIn.readLine("Insert the transactions file name within $PWD/src/main/scala/data: ")
  print("Insert Support por favor (between 0-1): ")
  val sup = scala.io.StdIn.readDouble()
  print("Now, insert Confidence (between 0-1): ")
  val conf = scala.io.StdIn.readDouble() // STOPPED FOR FALAFEL ON READDOUBLE ... TEST IT WHEN BACK ...


  val transactionsFile = fn_itemset
  val support = sup
  val confidence = conf

  val start = System.currentTimeMillis()
  val data =spark.read.textFile("src/main/scala/data/"+transactionsFile).map(t => t.split(" ").distinct).toDF("items")

  var Array(transDF,testDF)= data.randomSplit(Array(0.7,0.3))

  transDF.show()

  transDF.write.format("json").mode("ignore").save("src/main/scala/data/"+ transactionsFile.split("\\.").head +"70.json")
  testDF.write.format("json").mode("ignore").save("src/main/scala/data/"+ transactionsFile.split("\\.").head +"30.json")

/*
  transDF = spark.read.textFile("src/main/scala/data/"+ transactionsFile.split("\\.").head +"70.txt").map(t => t.split(" ").distinct).toDF("items")
  testDF = spark.read.textFile("src/main/scala/data/"+ transactionsFile.split("\\.").head +"30.txt").map(t => t.split(" ").distinct).toDF("items")

  transDF.show(20)
  transDF.printSchema()

  testDF.show(20)
  testDF.printSchema()
  */

  /*

  val fpgrowth = new FPGrowth().setItemsCol("items").setMinSupport(support).setMinConfidence(confidence)

  val model = fpgrowth.fit(transDF)


  // Display frequent itemsets.
  val fpDF = model.freqItemsets
  fpDF.show()

  // Display generated association rules.
  val arDF = model.associationRules
  arDF.show()

  // transform examines the input items against all the association rules and summarize the
  // consequents as prediction
  val resultDF = model.transform(testset)
  resultDF.show()

  //  peopleDF.select("name", "age").write.format("parquet").save("namesAndAges.parquet")

  val end = System.currentTimeMillis()

  val duration = (end-start)/1000

  println(s"\n ===== Total duration is: $duration ===== \n \n ")


  val outputFile = transactionsFile.split("\\.").head +"_"+ "S"+support.toString +"_"+ "C"+confidence.toString +"_"+ duration.toString


  fpDF.write.format("json").mode("overwrite").save("src/main/scala/data/output/FP_"+outputFile+".json")

  arDF.write.format("json").mode("overwrite").save("src/main/scala/data/output/AR_"+outputFile+".json")

  resultDF.write.format("json").mode("overwrite").save("src/main/scala/data/output/RESULT_"+outputFile+".json")

  */

}
//spark-submit --class PFP7030 --deploy-mode cluster --num-executors 16 --driver-memory 16g --driver-cores 12 target/scala-2.11/fp_2.11-1.0.jar

