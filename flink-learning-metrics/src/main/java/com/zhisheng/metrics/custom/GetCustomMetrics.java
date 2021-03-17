package com.zhisheng.metrics.custom;

import org.apache.flink.metrics.Metric;

public class GetCustomMetrics<T extends Metric> {
    T getMetricHD (MetricUtil metricUtil) {
        return (T) metricUtil.getMetricHD();
    }
}
