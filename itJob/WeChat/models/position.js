import { HTTP } from '../utils/http.js'
class Position extends HTTP {
  getPositionList(pageNum,pageSize,name,city,sCallback){
    this.request({
      url: '/position/getPositionList',
      method: 'get',
      data: {
        pageNum: pageNum,
        pageSize: pageSize,
        name:name,
        city:city,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  getOneById(id,sCallback){
    this.request({
      url: '/position/getOneById',
      method: 'get',
      data: {
        id: id,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  getPositionListByEnterpriseId(id,sCallback){
    this.request({
      url: '/position/getPositionListByEnterpriseId',
      method: 'get',
      data: {
        enterpriseId: id,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  getPositionTypeList(sCallback){
    this.request({
      url: '/position/getPositionTypeList',
      method: 'get',
      data: {},
      success: (res) => {
        sCallback(res)
      }
    })
  }
  insertPosition(position, sCallback) {
    this.request({
      url: '/position/insertPosition',
      method: 'post',
      data: {
        enterpriseId: position.enterpriseId,
        positionTypeId:position.positionTypeId,
        name: position.name,
        salary: position.salary,
        city: position.city,
        education: position.education,
        experience: position.experience,
        duties: position.duties,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  updatePosition(position, sCallback) {
    this.request({
      url: '/position/updatePosition',
      method: 'post',
      data: {
        id:position.id,
        enterpriseId: position.enterpriseId,
        positionTypeId: position.positionTypeId,
        name: position.name,
        salary: position.salary,
        city: position.city,
        education: position.education,
        experience: position.experience,
        duties: position.duties,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  revokePosition(id, sCallback) {
    this.request({
      url: '/position/revokePosition',
      method: 'post',
      data: {
        id: id,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
}
export { Position }