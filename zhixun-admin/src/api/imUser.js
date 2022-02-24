import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/imUser/list',
    method: 'get',
    params: query
  })
}

export function userDetail(id) {
  return request({
    url: '/imUser/detail',
    method: 'get',
    params: { id }
  })
}

export function updateUser(data) {
  return request({
    url: '/imUser/update',
    method: 'post',
    data
  })
}

export function deleteUser(data) {
  return request({
    url: '/imUser/delete',
    method: 'post',
    data
  })
}

