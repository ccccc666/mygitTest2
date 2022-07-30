<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
      label-position="left"
    >
      <el-form-item label="客户姓名" prop="ctName">
        <el-input
          v-model="queryParams.ctName"
          placeholder="请输入客户姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户地址" prop="ctAddress">
        <el-input
          v-model="queryParams.ctAddress"
          placeholder="请输入客户地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户电话号码" prop="ctTelephone">
        <el-input
          v-model="queryParams.ctTelephone"
          placeholder="请输入客户电话号码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户会员编号" prop="ctVipnumber">
        <el-input
          v-model="queryParams.ctVipnumber"
          placeholder="请输入客户会员编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户信誉等级" prop="ctCredit">
        <el-input
          v-model="queryParams.ctCredit"
          placeholder="请输入客户信誉等级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户会员等级" prop="ctViplevel">
        <el-select v-model="queryParams.ctViplevel" placeholder="请选择客户会员等级" clearable>
          <el-option
            v-for="dict in dict.type.ct_level"
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
          v-hasPermi="['customer:customer:add']"
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
          v-hasPermi="['customer:customer:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['customer:customer:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['customer:customer:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="customerList" border @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="客户id" align="center" prop="id" />
      <el-table-column label="客户姓名" align="center" prop="ctName" />
      <el-table-column label="客户地址" align="center" prop="addressName" width="200" />
      <el-table-column label="客户电话号码" align="center" prop="ctTelephone" />
      <el-table-column label="客户会员编号" align="center" prop="ctVipnumber" />
      <el-table-column label="客户信誉等级" width="200" align="center" prop="ctCredit">
        <template slot-scope="{ row }">
          <el-rate
            v-model="row.ctCredit"
            disabled
            :max="5"
            show-score
            text-color="#ff9900"
            :score-template="`${row.ctCredit}`">
          </el-rate>
        </template>
      </el-table-column>
      <el-table-column label="客户会员等级" align="center" prop="ctViplevel">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.ct_level" :value="scope.row.ctViplevel"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['customer:customer:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-data"
            @click="handleOrderView(scope.row)"
            v-hasPermi="['customer:customer:remove']"
          >订单信息</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['customer:customer:remove']"
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

    <!-- 添加或修改客户信息管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="客户姓名" prop="ctName">
          <el-input v-model="form.ctName" placeholder="请输入客户姓名" />
        </el-form-item>
        <el-form-item label="客户地址" prop="ctAddress">
          <el-cascader
            v-model="form.ctAddress"
            :options="dwelling"
            :props="dwellingProps"></el-cascader>
        </el-form-item>
        <el-form-item label="客户电话号码" prop="ctTelephone">
          <el-input v-model="form.ctTelephone" placeholder="请输入客户电话号码" />
        </el-form-item>
        <el-form-item label="客户会员编号" prop="ctVipnumber">
          <el-input v-model="form.ctVipnumber" placeholder="请输入客户会员编号" />
        </el-form-item>
        <el-form-item label="客户信誉等级" prop="ctCredit">
          <el-input v-model="form.ctCredit" placeholder="请输入客户信誉等级(等级评分在1-5之间)" />
        </el-form-item>
        <el-form-item label="客户会员等级" prop="ctViplevel">
          <el-select v-model="form.ctViplevel" placeholder="请选择客户会员等级">
            <el-option
              v-for="dict in dict.type.ct_level"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


    <el-dialog title="订单信息" :visible.sync="orderShow" width="900px" append-to-body>
      <el-table v-loading="orderLoading" :data="orderList" border>
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="业务订单编号" align="center" prop="id" />
        <el-table-column label="商品名称" align="center" prop="gsName" />
        <el-table-column label="业务客户姓名" align="center" prop="bsCtname" />
        <el-table-column label="客户联系方式" align="center" prop="bsCtphone" />
        <el-table-column label="商品交易单价" align="center" prop="bsPrice" />
        <el-table-column label="商品交易总量" align="center" prop="bsNumber" />
        <el-table-column label="业务贸易总额" align="center" prop="bsAllprice" />
        <el-table-column label="交易方式" align="center" prop="bsTsway">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.ts_way" :value="scope.row.bsTsway"/>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import {
  listCustomer,
  getCustomer,
  delCustomer,
  addCustomer,
  updateCustomer,
  addDwelling,
  getOrderById
} from "@/api/customer/customer";

export default {
  name: "Customer",
  dicts: ['ct_level','ts_way'],
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
      // 客户信息管理表格数据
      customerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ctName: null,
        ctAddress: null,
        ctTelephone: null,
        ctVipnumber: null,
        ctCredit: null,
        ctViplevel: null,
        addressName:null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        ctName:[
          { required: true, message: '必须输入客户姓名', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        ctCredit:[
          { required: true, message: '必须输入信誉等级', trigger: 'blur' }
        ],
        ctAddress:[
          { required: true, message: '必须输入客户地址信息', trigger: 'blur' }
        ],
        ctTelephone:[
          { required: true, message: '必须输入客户联系方式', trigger: 'blur' }
        ]
      },
      dwelling: [],
      dwellingProps: {
        label: 'name',
        value: 'code'
      },
      orderShow: false,
      orderList: [],
      orderLoading: false
    };
  },
  created() {
    this.getList();
    this.getDwelling();
  },
  methods: {
    /** 查询客户信息管理列表 */
    getList() {
      this.loading = true;
      listCustomer(this.queryParams).then(response => {
        this.customerList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        ctName: null,
        ctAddress: null,
        ctTelephone: null,
        ctVipnumber: null,
        ctCredit: null,
        ctViplevel: null,
        addressName:null
      };
      this.resetForm("form");
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
      this.open = true;
      this.title = "添加客户信息管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCustomer(id).then(response => {
        response.data.ctAddress = response.data.ctAddress.split(',').map(Number)
        this.form = response.data;
        console.log(this.form)
        this.open = true;
        this.title = "修改客户信息管理";
      });
    },
    handleOrderView (row) {
      this.orderLoading = true
      getOrderById( row.id ).then(res => {
        this.orderLoading = false
        if (res.code === 200) {
          this.orderShow = true
          this.orderList = res.data
        }
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.ctAddress = this.form.ctAddress.join(',')
          console.log(this.form)
          if (this.form.id != null) {
            updateCustomer(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCustomer(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除客户信息管理编号为"' + ids + '"的数据项？').then(function() {
        return delCustomer(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('customer/customer/export', {
        ...this.queryParams
      }, `customer_${new Date().getTime()}.xlsx`)
    },
    getDwelling () {
      addDwelling().then(res => {
        if (res.code === 200) {
          console.log(res)
          this.dwelling = res.data
        }
      })
    }
  }
};
</script>
