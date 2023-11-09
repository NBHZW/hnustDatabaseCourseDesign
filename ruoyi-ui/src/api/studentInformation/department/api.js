import request from '@/utils/request'

// 查询院系信息列表
export function listDepartment(query) {
  return request({
    url: '/system/department/list',
    method: 'get',
    params: query
  })
}

// 查询院系信息详细
export function getDepartment(id) {
  return request({
    url: '/system/department/' + id,
    method: 'get'
  })
}

// 新增院系信息
export function addDepartment(data) {
  return request({
    url: '/system/department',
    method: 'post',
    data: data
  })
}

// 修改院系信息
export function updateDepartment(data) {
  return request({
    url: '/system/department',
    method: 'put',
    data: data
  })
}

// 删除院系信息
export function delDepartment(id) {
  return request({
    url: '/system/department/' + id,
    method: 'delete'
  })
}
