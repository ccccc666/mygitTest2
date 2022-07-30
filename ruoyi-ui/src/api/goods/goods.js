import request from '@/utils/request'

// 查询商品管理列表
export function listGoods(query) {
  return request({
    url: '/goods/goods/list',
    method: 'get',
    params: query
  })
}

// 查询商品管理详细
export function getGoods(gsId) {
  return request({
    url: '/goods/goods/' + gsId,
    method: 'get'
  })
}

// 新增商品管理
export function addGoods(data) {
  return request({
    url: '/goods/goods',
    method: 'post',
    data: data
  })
}

// 修改商品管理
export function updateGoods(data) {
  return request({
    url: '/goods/goods',
    method: 'put',
    data: data
  })
}

// 删除商品管理
export function delGoods(gsId) {
  return request({
    url: '/goods/goods/' + gsId,
    method: 'delete'
  })
}

export function returnTree(){
  return request({
    url:'/classify/classify/classifyTree',
    method:'get'
  })
}

export function returnCfName(){
  return request({
    url:'/goods/goods//getName',
    method:'get'
  })
}
