import request from '@/utils/request'

// 查询奖励等级代码列表
export function listCode(query) {
  return request({
    url: '/system/code/list',
    method: 'get',
    params: query
  })
}

// 查询奖励等级代码详细
export function getCode(code) {
  return request({
    url: '/system/code/' + code,
    method: 'get'
  })
}

// 新增奖励等级代码
export function addCode(data) {
  return request({
    url: '/system/code',
    method: 'post',
    data: data
  })
}

// 修改奖励等级代码
export function updateCode(data) {
  return request({
    url: '/system/code',
    method: 'put',
    data: data
  })
}

// 删除奖励等级代码
export function delCode(code) {
  return request({
    url: '/system/code/' + code,
    method: 'delete'
  })
}
