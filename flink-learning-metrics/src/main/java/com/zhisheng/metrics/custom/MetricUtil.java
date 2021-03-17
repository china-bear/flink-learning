package com.zhisheng.metrics.custom;

import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.metrics.Metric;

public abstract class MetricUtil {
//public abstract class MetricUtil<T extends Metric> {
    RuntimeContext runtimeContext;
    String appTag;
    String metricName;
    Integer valueToExpose;

    public MetricUtil() {
    }

    public MetricUtil(RuntimeContext runtimeContext, String appTag, String metricName) {
        this.runtimeContext = runtimeContext;
        this.appTag = appTag;
        this.metricName = metricName;
    }

    public MetricUtil(RuntimeContext runtimeContext, String appTag, String metricName, Integer valueToExpose) {
        this.runtimeContext = runtimeContext;
        this.appTag = appTag;
        this.metricName = metricName;
        this.valueToExpose = valueToExpose;
    }

    public abstract Metric getMetricHD();
}
