<template>
  <div class="chart" ref="chart"></div>
</template>

<script>
import echarts from "echarts";
export default {
  name: 'bar',
  props: {
    xAxis: {
      type: Array,
      default: () => ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    },
    series: {
      type: Array,
      default: () => [120, 200, 150, 80, 70, 110, 130]
    }
  },
  data() {
    return {
      chart: null
    }
  },
  watch: {
    series: {
      handler: 'init'
    }
  },
  methods: {
    init() {
      this.chart = echarts.init(this.$refs.chart)
      this.chart.setOption({
        xAxis: {
          type: 'category',
          data: this.xAxis
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: this.series,
            type: 'bar',
            showBackground: true,
            backgroundStyle: {
              color: 'rgba(180, 180, 180, 0.2)'
            }
          }
        ]
      })
    }
  }
}
</script>
<style>
.chart {
  width: 100%;
  height: 300px;
}
</style>
