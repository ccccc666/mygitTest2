<template>
  <div class="chart" ref="chart"></div>
</template>

<script>
import echarts from "echarts";
export default {
  name: 'Pie',
  props: {
    series: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      chart: null
    }
  },
  watch: {
    series: {
      handler: 'init',
    }
  },
  methods: {
    init() {
      this.chart = echarts.init(this.$refs.chart)

      this.chart.setOption({
        title: {
          text: '商品分类 ',
          subtext: 'Fake Data',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: '50%',
            data: this.series,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
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
