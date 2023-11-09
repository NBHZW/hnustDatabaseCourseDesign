import request from '@/utils/request'

// 查询学籍信息变更列表
export function listChange(query) {
  return request({
    url: '/system/change/list',
    method: 'get',
    params: query
  })
}

// 查询学籍信息变更详细
export function getChange(id) {
  return request({
    url: '/system/change/' + id,
    method: 'get'
  })
}

// 新增学籍信息变更
export function addChange(data) {
  return request({
    url: '/system/change',
    method: 'post',
    data: data
  })
}

// 修改学籍信息变更
export function updateChange(data) {
  return request({
    url: '/system/change',
    method: 'put',
    data: data
  })
}

// 删除学籍信息变更
export function delChange(id) {
  return request({
    url: '/system/change/' + id,
    method: 'delete'
  })
}
