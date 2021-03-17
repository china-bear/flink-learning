package com.zhisheng.metrics.custom;

import com.codahale.metrics.SlidingWindowReservoir;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.dropwizard.metrics.DropwizardHistogramWrapper;
import org.apache.flink.dropwizard.metrics.DropwizardMeterWrapper;
import org.apache.flink.metrics.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomMetricUtil3 <T extends Metric> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomMetricUtil.class);
    //private T metric;
    private RuntimeContext runtimeContext;
    private String appTag;
    private String metricName;
    private int valueToExpose;

    public CustomMetricUtil3() {
    }
    //https://juejin.cn/post/6844903801510428686  通过方法的重载间接的支持默认的参数值

    public CustomMetricUtil3(RuntimeContext runtimeContext, String metricName) {
        this.runtimeContext = runtimeContext;
        this.appTag = "DW";
        this.metricName = metricName;

    }
    public CustomMetricUtil3(RuntimeContext runtimeContext, String appTag, String metricName) {
        this.runtimeContext = runtimeContext;
        this.appTag = appTag;
        this.metricName = metricName;
    }




    public T getMetric(T metric) {
             if (metric instanceof Counter) {
                metric = (T) getCountMetric();
            } else if (metric instanceof Gauge) {
                metric = (T) getGaugeMetric();
            } else if (metric instanceof Meter) {
                metric = (T) getMeterMetric();
            } else if (metric instanceof Histogram) {
                metric = (T) getHistogramMetric();
            } else {
                LOGGER.warn("Cannot add unknown metric type {}. " , metric.getClass().getName());
            }

        return metric;
   }



    public void setMetricTypp(T metric) {
        if (metric instanceof Counter) {
            ((Counter) metric).inc();
        } else if (metric instanceof Gauge) {
            valueToExpose++;
        } else if (metric instanceof Meter) {
            ((Meter) metric).markEvent();
        } else if (metric instanceof Histogram) {
            ((Histogram) metric).update(10);
        } else {
            LOGGER.warn("Cannot add unknown metric type {}. " , metric.getClass().getName());
        }
    }


    public Counter getCountMetric(){
       Counter counter = runtimeContext.getMetricGroup()
               .addGroup(appTag)
               .counter(metricName);

       return counter;

   }

    public Gauge  getGaugeMetric() {
        Gauge  gauge = runtimeContext.getMetricGroup()   //如果返回类型为Object则该类需要重写toString方法。
                .addGroup(appTag)
                .gauge(metricName, new Gauge<Integer>() {
                    @Override
                    public Integer getValue() {
                        return valueToExpose;
                    }
                });

        return gauge;
    }

    public Meter getMeterMetric() {
        Meter meter = runtimeContext.getMetricGroup()
                .addGroup(appTag)
                .meter(metricName , new DropwizardMeterWrapper(new com.codahale.metrics.Meter()));

        return meter;
    }

    public Histogram getHistogramMetric() {
        Histogram histogram = runtimeContext.getMetricGroup()
                .addGroup(appTag)
                .histogram(metricName , new DropwizardHistogramWrapper(new com.codahale.metrics.Histogram(new SlidingWindowReservoir(500))));

        return histogram;
    }

    public static void main(String[] args) {

    }

}
