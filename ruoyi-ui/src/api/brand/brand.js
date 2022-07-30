import request from '@/utils/request'

// 查询品牌信息列表
export function listBrand(query) {
  return request({
    url: '/brand/brand/list',
    method: 'get',
    params: query
  })
}

// 查询品牌信息详细
export function getBrand(brId) {
  return request({
    url: '/brand/brand/' + brId,
    method: 'get'
  })
}

// 新增品牌信息
export function addBrand(data) {
  return request({
    url: '/brand/brand',
    method: 'post',
    data: data
  })
}

// 修改品牌信息
export function updateBrand(data) {
  return request({
    url: '/brand/brand',
    method: 'put',
    data: data
  })
}

// 删除品牌信息
export function delBrand(brId) {
  return request({
    url: '/brand/brand/' + brId,
    method: 'delete'
  })
}

// 删除品牌
export function delDict(dictValue) {
  return request({
    url: '/brand/brand/delDict/' + dictValue,
    method: 'delete'
  })
}


