import request from '@/utils/request'

// 查询分类查询列表
export function listClassify(query) {
  return request({
    url: '/classify/classify/list',
    method: 'get',
    params: query
  })
}

// 查询分类查询详细
export function getClassify(id) {
  return request({
    url: '/classify/classify/' + id,
    method: 'get'
  })
}

// 新增分类查询
export function addClassify(data) {
  return request({
    url: '/classify/classify',
    method: 'post',
    data: data
  })
}

// 修改分类查询
export function updateClassify(data) {
  return request({
    url: '/classify/classify',
    method: 'put',
    data: data
  })
}

// 删除分类查询
export function delClassify(id) {
  return request({
    url: '/classify/classify/' + id,
    method: 'delete'
  })
}
