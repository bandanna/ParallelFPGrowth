# Parallel FP-Growth (PFP)

This implementation of Parallel FP-Growth uses PFP available in Spark 2.2.x.
Parallel FP-Growth is a parallelized solution FP-Growth that exploits the best out of distributed processing architectures.

The algorithm expects the following inputs:
1) <path-to-training-dataset>
2) <path-to-test-dataset>
3) support-rate [i.e. float(0,1)]
4) confidence-rate [i.e. float(0,1)]


Run using *sbt* ```~/src/main/scala/PFP.scala``` or *spark-submit* using the provide \*.jar i.e.:
```
spark-submit --class PFP --deploy-mode cluster <path-to-jar>/fp_2.11-1.0.jar
```

You can add parameters to tune it like:
```
spark-submit --class PFP --deploy-mode cluster --num-executors 16 --driver-memory 16g --driver-cores 12 target/scala-2.11/fp_2.11-1.0.jar
```

If the transaction DB is not super small (i.e. the provided test.txt DB), it is highly important to add memory to driver and executors.
