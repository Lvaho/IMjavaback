import request from '@/utils/request'

export function listMessage(query) {
  return request({
    url: '/message/list',
    method: 'get',
    params: query
  })
}

export function createMessage(data) {
  return request({
    url: '/message/create',
    method: 'post',
    data
  })
}

export function readMessage(data) {
  return request({
    url: '/message/read',
    method: 'get',
    data
  })
}

export function updateMessage(data) {
  return request({
    url: '/message/update',
    method: 'post',
    data
  })
}

export function deleteMessage(data) {
  return request({
    url: '/message/delete',
    method: 'post',
    data
  })
}
