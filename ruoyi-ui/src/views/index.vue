<template>
  <div class="home">
    <el-card>
      <header class="header flex-row items-center">
        <div class="flex-row items-center">
          <img
            class='picture'
            :src="avatar"
          />
          <div class="userinfo">
            <div class="name">{{ name }}: {{ welcome }}  </div>
            <div class="name">今天也是充满希望的一天!</div>
          </div>
        </div>
      </header>
    </el-card>
    <br />
    <br />
    <el-row :gutter="30">
      <el-col :span="24">
        <el-card><Bar :x-axis="barData.xAxis" :series="barData.series"/></el-card>
      </el-col>
      <br />
      <br />
      <el-col :span="24">
        <el-card><Pie :series="barData.classify" /></el-card>
      </el-col>
    </el-row>
    <br />
    <el-row :gutter="30">
      <el-col :span="12">
        <el-card>
          <div>销售单</div>
          <br />
          <el-table
            :data="orderData"
            height="500"
          >
            <el-table-column prop="id" label="订单编号" align="center" show-overflow-tooltip></el-table-column>
            <el-table-column label="总量" align="center" prop="bsNumber" />
            <el-table-column label="总金额" align="center" prop="bsAllprice" />
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <div>库存</div>
          <br />
          <el-table
            :data="storeData"
            height="500"
          >
            <el-table-column label="商品名称" align="center" prop="gsName" show-overflow-tooltip/>
            <el-table-column prop="gsGsnumber" label="库存" align="center"></el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import Bar from '@/components/Charts/Bar'
import Pie from '@/components/Charts/Pie'
import {listOrder} from "@/api/order/order";
import {listGoods} from "@/api/goods/goods";
const page = {
  pageNum: 1,
  pageSize: 10
}
export default {
  name: 'Workplace',
  components: {
    Bar,
    Pie
  },
  data () {
    return {
      orderData: [],
      storeData: [],
      welcome: {}
    }
  },
  computed: {
    ...mapGetters([ 'name',  'avatar' ]),
    barData() {
      return this.storeData.reduce((res, item) => {
        const { gsName, gsGsnumber, cfname3, gsCyid3 } = item
        res.xAxis.push(gsName)
        res.series.push(gsGsnumber)
        res.classify.push({ value: gsCyid3, name: cfname3 })
        return res
      }, { xAxis: [], series: [], classify: [] })
    }
  },
  created () {
    this.getOrderList()
    this.getGoodsList()
    this.setWelcome()
  },
  methods: {
    getOrderList() {
      this.loading = true;
      listOrder(page).then(response => {
        this.orderData = response.rows;
      });
    },
    getGoodsList() {
      this.loading = true;
      listGoods(page).then(response => {
        this.storeData = response.rows;
      });
    },
    setWelcome() {
      const date = new Date().getHours()

      this.welcome = date <= 11
        ? '上午好'
        : date <= 13
          ? '中午好'
          : date < 18
            ? '下午好'
            : '晚上好'
    }
  }
}
</script>

<style lang="scss" scoped>
.flex-row {
  display: flex;
  flex-direction: row;
}
.items-center {
  align-items: center;
}
.home {
  background-color: #f5f5f5;
  .header {
    .picture {
      width: 60px;
      height: 60px;
      border-radius: 50%;
      overflow: hidden;
      margin-right: 20px;
    }
    &-right {
      margin-left: auto;
    }
  }
  ::v-deep .el-table {
    &:before {
      display: none;
    }
    .el-table__header-wrapper th {
      background-color: #fff;
    }
    .el-table--medium .el-table__cell {
      padding: 4px 0;
      border-color: #f2eaea
    }
  }
}
</style>
