<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品名称" prop="gsName">
        <el-input
          v-model="queryParams.gsName"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品编号" prop="gsGsidnumber">
        <el-input
          v-model="queryParams.gsGsidnumber"
          placeholder="请输入商品编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="品牌" prop="gsBrand">
        <el-select v-model="queryParams.gsBrand">
          <el-option
            v-for="item in brandList"
            :key="item.brId"
            :label="item.brName"
            :value="item.brId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否自营" prop="gsIfsupport">
        <el-select v-model="queryParams.gsIfsupport" placeholder="请选择分类等级">
          <el-option
            v-for="dict in dict.type.self_support"
            :key="dict.value"
            :label="dict.label"
            :value="parseInt(dict.value)"
          ></el-option>
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
          v-hasPermi="['goods:goods:add']"
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
          v-hasPermi="['goods:goods:edit']"
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
          v-hasPermi="['goods:goods:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['goods:goods:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="goodsList" border @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="商品id" align="center" prop="gsId" />
      <el-table-column label="商品名称" align="center" prop="gsName" />
      <el-table-column label="商品编号" align="center" prop="gsGsidnumber" />
      <el-table-column label="品牌" align="center" prop="gsBrand">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.brand" :value="scope.row.gsBrand"/>
        </template>
      </el-table-column>
      <el-table-column label="商品单价" align="center" prop="gsSgprice" />
      <el-table-column label="商品库存" align="center" prop="gsGsnumber" />
      <el-table-column label="是否自营" align="center" prop="gsIfsupport">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.self_support" :value="scope.row.gsIfsupport"/>
        </template>
      </el-table-column>
      <el-table-column label="一级分类" align="center" prop="cfname1" />
      <el-table-column label="二级分类" align="center" prop="cfname2" />
      <el-table-column label="三级分类" align="center" prop="cfname3" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['goods:goods:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['goods:goods:remove']"
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

    <!-- 添加或修改商品管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="商品名称" prop="gsName">
          <el-input v-model="form.gsName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品编号" prop="gsGsidnumber">
          <el-input v-model="form.gsGsidnumber" placeholder="请输入商品编号" />
        </el-form-item>
        <el-form-item label="品牌" prop="gsBrand">
          <el-select v-model="form.gsBrand">
            <el-option
              v-for="item in brandList"
              :key="item.brId"
              :label="item.brName"
              :value="item.brId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品分类" prop="">
          <el-cascader
            @change="onClassifyChange"
            v-model="form.classifyId"
            :options="classifyList"
            :props="classProps"></el-cascader>
        </el-form-item>
        <el-form-item label="商品单价" prop="gsSgprice">
          <el-input v-model="form.gsSgprice" placeholder="请输入商品单价" />
        </el-form-item>

        <el-form-item  v-if="popType"  label="商品库存" prop="gsGsnumber">
          <el-input v-model="form.gsGsnumber" placeholder="请输入初始商品库存" />
        </el-form-item>
        <template v-else>
          <el-form-item  label="商品库存" prop="gsGsnumber">
            <div>{{form.gsGsnumber}}</div>
          </el-form-item>
          <el-form-item  label="商品新增库存" prop="gsGsnumberNew">
            <el-input v-model="form.gsGsnumberNew" placeholder="请输入新增商品库存(可以不填)" />
          </el-form-item>
        </template>
        <el-form-item label="是否自营" prop="gsIfsupport">
          <el-select v-model="form.gsIfsupport" placeholder="请选择分类等级">
            <el-option
              v-for="dict in dict.type.self_support"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listGoods, getGoods, delGoods, addGoods, updateGoods ,returnTree,returnCfName} from "@/api/goods/goods";
import { listBrand } from "@/api/brand/brand"
export default {
  name: "Goods",
  dicts: ['self_support', 'brand'],
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
      // 商品管理表格数据
      goodsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
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
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        gsName:[
          { required: true, message: '请输入商品名称', trigger: 'blur' },
        ],
        gsGsidnumber:[
          { required: true, message: '请输入商品编号', trigger: 'blur' },
        ]
      },
      // 品牌数据
      brandList: [],
      // 商品分类
      classifyList: [],
      classProps: {
        label: 'cfName',
        value: `id`,
        children: 'children'
      },
      //分类名字集合
      classifyName:[],
      popType : true
    };
  },
  created() {
    this.getList();
    this.getClassifyList()
    this.getBrandList()
  },
  methods: {
    /** 查询商品管理列表 */
    getList() {
      this.loading = true;
      listGoods(this.queryParams).then(response => {
        this.goodsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getBrandList() {
      listBrand({
        pageNum: 1,
        pageSize: 100
      }).then(res => {
        this.brandList = res.rows
      })
    },
    getClassifyList() {
      returnTree({
        pageNum: 1,
        pageSize: 100
      }).then(res => {
        console.log(res)
        this.classifyList = res.data
      })
    },
    onClassifyChange ( selected ) {
      console.log(selected)
      if (selected) {
        const [ gsCyid1, gsCyid2, gsCyid3 ] = selected
        this.form = Object.assign({}, this.form, {
          gsCyid1, gsCyid2, gsCyid3
        })
      }
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        gsId: null,
        gsName: null,
        gsGsidnumber: null,
        gsBrand: null,
        gsSgprice: null,
        gsGsnumber: null,
        gsIfsupport: null,
        gsCyid1: null,
        gsCyid2: null,
        gsCyid3: null
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
      this.ids = selection.map(item => item.gsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.popType=true;
      this.open = true;
      this.title = "添加商品管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.popType=false;
      const gsId = row.gsId || this.ids
      getGoods(gsId).then(response => {
        const { gsCyid1, gsCyid2, gsCyid3 } = response.data
        this.form = response.data;
        this.form.classifyId = [ gsCyid1, gsCyid2, gsCyid3 ]
        this.open = true;
        this.title = "修改商品管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.gsId != null) {
            updateGoods(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGoods(this.form).then(response => {
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
      const gsIds = row.gsId || this.ids;
      this.$modal.confirm('是否确认删除商品管理编号为"' + gsIds + '"的数据项？').then(function() {
        return delGoods(gsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('goods/goods/export', {
        ...this.queryParams
      }, `goods_${new Date().getTime()}.xlsx`)
    },
  }
};
</script>
