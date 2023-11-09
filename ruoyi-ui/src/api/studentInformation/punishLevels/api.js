import request from '@/utils/request'

// 查询处罚等级代码列表
export function listLevels(query) {
  return request({
    url: '/system/levels/list',
    method: 'get',
    params: query
  })
}

// 查询处罚等级代码详细
export function getLevels(id) {
  return request({
    url: '/system/levels/' + id,
    method: 'get'
  })
}

// 新增处罚等级代码
export function addLevels(data) {
  return request({
    url: '/system/levels',
    method: 'post',
    data: data
  })
}

// 修改处罚等级代码
export function updateLevels(data) {
  return request({
    url: '/system/levels',
    method: 'put',
    data: data
  })
}

// 删除处罚等级代码
export function delLevels(id) {
  return request({
    url: '/system/levels/' + id,
    method: 'delete'
  })
}
