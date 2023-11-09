import request from '@/utils/request'

// 查询变更代码列表
export function listChangeCode(query) {
  return request({
    url: '/system/changeCode/list',
    method: 'get',
    params: query
  })
}

// 查询变更代码详细
export function getChangeCode(id) {
  return request({
    url: '/system/changeCode/' + id,
    method: 'get'
  })
}

// 新增变更代码
export function addChangeCode(data) {
  return request({
    url: '/system/changeCode',
    method: 'post',
    data: data
  })
}

// 修改变更代码
export function updateChangeCode(data) {
  return request({
    url: '/system/changeCode',
    method: 'put',
    data: data
  })
}

// 删除变更代码
export function delChangeCode(id) {
  return request({
    url: '/system/changeCode/' + id,
    method: 'delete'
  })
}
