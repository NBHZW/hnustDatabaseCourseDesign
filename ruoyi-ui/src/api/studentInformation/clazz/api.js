import request from '@/utils/request'

// 查询班级信息列表
export function listClazz(query) {
  return request({
    url: '/system/clazz/list',
    method: 'get',
    params: query
  })
}

// 查询班级信息详细
export function getClazz(id) {
  return request({
    url: '/system/clazz/' + id,
    method: 'get'
  })
}

// 新增班级信息
export function addClazz(data) {
  return request({
    url: '/system/clazz',
    method: 'post',
    data: data
  })
}

// 修改班级信息
export function updateClazz(data) {
  return request({
    url: '/system/clazz',
    method: 'put',
    data: data
  })
}

// 删除班级信息
export function delClazz(id) {
  return request({
    url: '/system/clazz/' + id,
    method: 'delete'
  })
}
