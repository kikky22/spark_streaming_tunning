# Spark Streaming Performance Tunning

### Spark cluster node 수가 부족하여 초기 개발시 지역별 Filtering 기능 적용하여 운영
### 지역확대 요청으로 데이터량이 증가하면서 CPU 점유율 상승으로 인한 잦은 Spark Application 다운 이슈 발생
### Shuffle 최소화를 위한 Executor 개수 최적화 및 Partition 개수 최적화
### 최종적으로 Spark Streaming의 micro batch process time 변경을 통해 문제 해결


![alt](https://github.com/kikky22/spark_streaming_tunning/blob/master/assets/spark_duration_time.png?raw=true)

![alt](https://github.com/kikky22/spark_streaming_tunning/blob/master/assets/spark_cpu_uage.png?raw=true)

![alt](https://github.com/kikky22/spark_streaming_tunning/blob/master/assets/spark_process_time_tunning.png?raw=true)


### 사용방법

### Spark 실행옵션
<spark.streaming.checkpoint.disable>true</spark.streaming.checkpoint.disable>  
<spark.streaming.clock use.even.part="true">org.apache.spark.util.custom.System1MinuteAddClock</spark.streaming.clock>
