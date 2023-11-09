import request from '@/utils/request'

// 查询处罚表列表
export function listPunishment(query) {
  return request({
    url: '/system/punishment/list',
    method: 'get',
    params: query
  })
}

// 查询处罚表详细
export function getPunishment(id) {
  return request({
    url: '/system/punishment/' + id,
    method: 'get'
  })
}

// 新增处罚表
export function addPunishment(data) {
  return request({
    url: '/system/punishment',
    method: 'post',
    data: data
  })
}

// 修改处罚表
export function updatePunishment(data) {
  return request({
    url: '/system/punishment',
    method: 'put',
    data: data
  })
}

// 删除处罚表
export function delPunishment(id) {
  return request({
    url: '/system/punishment/' + id,
    method: 'delete'
  })
}
