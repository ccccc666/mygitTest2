import request from '@/utils/request'

// 查询客户信息管理列表
export function listCustomer(query) {
  return request({
    url: '/customer/customer/list',
    method: 'get',
    params: query
  })
}

// 查询客户信息管理详细
export function getCustomer(id) {
  return request({
    url: '/customer/customer/' + id,
    method: 'get'
  })
}

// 新增客户信息管理
export function addCustomer(data) {
  return request({
    url: '/customer/customer',
    method: 'post',
    data: data
  })
}

// 修改客户信息管理
export function updateCustomer(data) {
  return request({
    url: '/customer/customer',
    method: 'put',
    data: data
  })
}

// 删除客户信息管理
export function delCustomer(id) {
  return request({
    url: '/customer/customer/' + id,
    method: 'delete'
  })
}

export function addDwelling(){
  return request({
    url:'/customer/customer/DwellingTree',
    method:'get'
  })
}

export function getOrderById(id){
  return request({
    url:'/customer/customer/getOrderById/'+id,
    method:'get'
  })
}
