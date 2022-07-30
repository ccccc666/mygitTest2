<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="120px">
      <el-form-item label="业务客户姓名" prop="bsCtname">
        <el-input
          v-model="queryParams.bsCtname"
          placeholder="请输入客户姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户联系方式" prop="bsCtphone">
        <el-input
          v-model="queryParams.bsCtphone"
          placeholder="请输入联系方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交易方式" prop="bsTsway">
        <el-select v-model="queryParams.bsTsway" placeholder="请选择交易方式" clearable>
          <el-option
            v-for="dict in dict.type.ts_way"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['order:order:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['order:order:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-document-copy"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['order:order:remove']"
        >批量取消订单</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['order:order:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          @click="handleDelAll"
          v-hasPermi="['order:order:export']"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" ref="elTable" :data="orderList" border @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单编号" align="center" prop="id" />
      <el-table-column label="商品名称" align="center" prop="goodsName" />
      <el-table-column label="客户姓名" align="center" prop="bsCtname" />
      <el-table-column label="联系方式" align="center" prop="bsCtphone" width="110"/>
      <el-table-column label="单价" align="center" prop="bsPrice" />
      <el-table-column label="总量" align="center" prop="bsNumber" />
      <el-table-column label="总金额" align="center" prop="bsAllprice" />
      <el-table-column label="付款状态" align="center" prop="strBsGoodsSt" />
      <el-table-column label="发货状态" align="center" prop="strBsSentSt" />
      <el-table-column label="交易方式" align="center" prop="bsTsway">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.ts_way" :value="scope.row.bsTsway"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['order:order:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-document-copy"
            @click="handleDelete(scope.row)"
            v-hasPermi="['order:order:remove']"
          >取消订单</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelAll(scope.row)"
            v-hasPermi="['order:order:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改业务信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item label="客户" prop="bsTsway">
          <el-select v-model="form.bsCustomerid" placeholder="请选择客户" @change="customerSelect">
            <el-option
              v-for="dict in customerList"
              :key="dict.id"
              :label="dict.ctName"
              :value="dict.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否付款">
          <el-radio-group v-model="form.bsGoodsStates">
            <el-radio :label="0" border size="mini" style="margin-right: 15px">已付款</el-radio>
            <el-radio :label="1" border size="mini">未付款</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否发货">
          <el-radio-group v-model="form.bsSentStates">
            <el-radio :label="0" border size="mini" style="margin-right: 15px">未发货</el-radio>
            <el-radio :label="1" border size="mini">已发货</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="交易方式" prop="bsTsway">
          <el-select v-model="form.bsTsway" placeholder="请选择交易方式">
            <el-option
              v-for="dict in dict.type.ts_way"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item v-if="popType" label="选择商品:" prop="bsTsway">
          <el-table v-loading="goodsPage.loading"
                    @selection-change="handleGoodsSelection"
                    :data="goodsList"
                    ref="goods">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="商品名称" align="center" prop="gsName" />
            <el-table-column label="品牌" align="center" prop="gsBrand">
              <template slot-scope="scope">
                <dict-tag :options="dict.type.brand" :value="scope.row.gsBrand"/>
              </template>
            </el-table-column>
            <el-table-column label="发货数量" align="center" prop="gsBrand">
              <template slot-scope="scope">
                <el-input size="small" v-model="scope.row.num"></el-input>
              </template>
            </el-table-column>
          </el-table>

          <pagination
            v-show="goodsPage.total>0"
            :total="goodsPage.total"
            :page.sync="goodsPage.pageNum"
            :limit.sync="goodsPage.pageSize"
            @pagination="getGoodsList"
          />
        </el-form-item>
        <template v-else>
          <el-form-item label="商品名称">
            <div>{{ form.goodsName }}</div>
          </el-form-item>
          <el-form-item label="发货数量">
            <div>{{form.bsNumber}}</div>
<!--            <el-input v-model="form.bsNumber" style="width: 220px"></el-input>-->
          </el-form-item>
          <el-form-item label="修改后的发货数量">
            <el-input v-model="form.exChangeBsNumber" style="width: 220px"></el-input>
          </el-form-item>
        </template>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOrder, getOrder, delOrder, addOrder, updateOrder, deleteAll } from "@/api/order/order";
import {listCustomer} from "@/api/customer/customer";
import {listGoods} from "@/api/goods/goods";

const form = {
  id: null,
  bsCtname: null,
  bsCtphone: null,
  bsPrice: null,
  bsNumber: null,
  bsAllprice: null,
  bsTsway: null,
  bsCustomerid: null,
  bsGoodsname: null,
  bsGoodsStates:0,
  bsSentStates:0
}

export default {
  name: "Order",
  dicts: ['ts_way', 'brand'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 业务信息表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams2: {
        pageNum: 1,
        pageSize: 10,
        gsName: null,
        gsGsidnumber: null,
        gsBrand: null,
        gsSgprice: null,
        gsGsnumber: null,
        gsIfsupport: null,
        classifyId: [],
        gsCyid1: null,
        gsCyid2: null,
        gsCyid3: null
      },
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bsCtname: null,
        bsCtphone: null,
        bsPrice: null,
        bsNumber: null,
        bsAllprice: null,
        bsTsway: null,
        bsCustomerid: null,
        bsGoodsname: null
      },
      goodsPage: {
        loading: false,
        pageNum: 1,
        pageSize: 10,
        total: 0
      },
      // 表单参数
      form: Object.assign({}, form),
      // 表单校验
      rules: {
      },
      customerList: [],
      goodsList: [],
      popType: true
    };
  },
  created() {
    this.getList();
    this.getCustomerList();
    this.getGoodsList();
    // function ref2 () {
    //   console.log('2', this)
    // }
    // const ref3 = () => console.log('3', this)
    // const ref4 = () => 4
    // ref2()
    // ref3()
    // console.log(ref4())
    // this.demo(res => {
    //   console.log(res)
    // })
  },
  methods: {
    customerSelect(val) {
      var customer = this.customerList.find(item => item.id == val);
      console.log(val)
      console.log(customer)
      this.form.bsCtname = customer.ctName
      this.form.bsCtphone = customer.ctTelephone
    },
    handleGoodsSelection(val) {
      console.log(val)
      this.form.goods = val;
      console.log(this.form)
    },
    /** 查询业务信息列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /*查询商品信息列表*/
    // getList2() {
    //   var vm = this;
    //   this.loading = true;
    //   listGoods({
    //     pageNum: vm.goodsPage.pageNum,
    //     pageSize: vm.goodsPage.pageSize
    //   }).then(response => {
    //     this.goodsList = response.rows;
    //     this.goodsPage.total = response.total;
    //     this.loading = false;
    //   });
    // },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form =  Object.assign({}, form);
      // this.resetForm("form");
      this.$nextTick(() => {
        this.resetForm('form')
      })
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.popType = true
      this.open = true;
      this.title = "添加业务信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.popType = false
      const id = row.id || this.ids
      getOrder(id).then(response => {
        this.form = Object.assign({}, response.data, row);
        this.open = true;
        this.title = "修改业务信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {

          if (this.form.id != null) {
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            const data = Object.assign({}, this.form, {
              goods: this.$refs.goods.selection
            })
            addOrder(data).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认取消业务信息编号为"' + ids + '"的数据项？').then(function() {
        return delOrder(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("取消订单成功");
      }).catch(() => {});
    },
    handleDelAll(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否永久删除选中数据项？').then(function() {
        return deleteAll(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除订单成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('order/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
    },
    /** 查询客户信息管理列表 */
    getCustomerList() {
      this.loading = true;
      listCustomer({
        pageNum: 1,
        pageSize: 100
      }).then(response => {
        this.customerList = response.rows;
      });
    },
    /** 查询商品管理列表 */
    getGoodsList() {
      this.goodsPage.loading = true;
      // listGoods(this.goodsPage).then(response => {
      //   this.goodsList = response.rows;
      //   this.goodsPage.total = response.total;
      //   this.goodsPage.loading = false;
      // });
      const that = this
      listGoods(this.goodsPage).then(function (response) {
        that.goodsList = response.rows;
        that.goodsPage.total = response.total;
        that.goodsPage.loading = false;
      })
    },
    demo(callback) {
      setTimeout(() => {
        const data = { success: '成功了' }
        callback(data)
      }, 200)
    }
  }
};
</script>
