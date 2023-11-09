import request from '@/utils/request'

// 查询奖励信息记录列表
export function listReward(query) {
  return request({
    url: '/system/reward/list',
    method: 'get',
    params: query
  })
}

// 查询奖励信息记录详细
export function getReward(id) {
  return request({
    url: '/system/reward/' + id,
    method: 'get'
  })
}

// 新增奖励信息记录
export function addReward(data) {
  return request({
    url: '/system/reward',
    method: 'post',
    data: data
  })
}

// 修改奖励信息记录
export function updateReward(data) {
  return request({
    url: '/system/reward',
    method: 'put',
    data: data
  })
}

// 删除奖励信息记录
export function delReward(id) {
  return request({
    url: '/system/reward/' + id,
    method: 'delete'
  })
}
