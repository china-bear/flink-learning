package com.zhisheng.metrics.custom;

import com.codahale.metrics.SlidingWindowReservoir;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.dropwizard.metrics.DropwizardHistogramWrapper;
import org.apache.flink.metrics.Histogram;
import org.apache.flink.metrics.Metric;

public class HistogramMetricUtil extends MetricUtil {

    public HistogramMetricUtil() {
    }

    public HistogramMetricUtil(RuntimeContext runtimeContext, String appTag, String metricName) {
        super(runtimeContext, appTag, metricName);
    }

    public Histogram getMetricHD() {
        Histogram histogram = runtimeContext.getMetricGroup()
                .addGroup(appTag)
                .histogram(metricName , new DropwizardHistogramWrapper(new com.codahale.metrics.Histogram(new SlidingWindowReservoir(500))));

        return histogram;
    }
}

