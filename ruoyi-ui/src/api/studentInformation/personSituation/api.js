import request from '@/utils/request'

// 查询个人情况列表
export function listPersonsituation(query) {
  return request({
    url: '/system/personsituation/list',
    method: 'get',
    params: query
  })
}

// 查询个人情况详细
export function getPersonsituation(id) {
  return request({
    url: '/system/personsituation/' + id,
    method: 'get'
  })
}

// 新增个人情况
export function addPersonsituation(data) {
  return request({
    url: '/system/personsituation',
    method: 'post',
    data: data
  })
}

// 修改个人情况
export function updatePersonsituation(data) {
  return request({
    url: '/system/personsituation',
    method: 'put',
    data: data
  })
}

// 删除个人情况
export function delPersonsituation(id) {
  return request({
    url: '/system/personsituation/' + id,
    method: 'delete'
  })
}
