package com.zhisheng.metrics.custom;

import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.metrics.Gauge;

public class GaugeMetricUtil extends MetricUtil{

    public GaugeMetricUtil() {
    }

    public GaugeMetricUtil(RuntimeContext runtimeContext, String appTag, String metricName, Integer valueToExpose) {
        super(runtimeContext, appTag, metricName, valueToExpose);
    }

    public Gauge getMetricHD() {
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

}

