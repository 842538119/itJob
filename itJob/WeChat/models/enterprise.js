import { HTTP } from '../utils/http.js'
class Enterprise extends HTTP {
  getEnterpriseById(id, sCallback) {
    this.request({
      url: '/enterprise/getOneById',
      method: 'get',
      data: {
        id: id,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  getEnterpriseByUserId(id, sCallback) {
    this.request({
      url: '/enterprise/getOneByUserId',
      method: 'get',
      data: {
        userid: id,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  updateEnterprise(person, sCallback) {
    this.request({
      url: '/enterprise/updateEnterprise',
      method: 'post',
      data: {
        id: person.id,
        userId: person.userid,
        name: person.name,
        icon: person.icon,
        type: person.type,
        financing: person.financing,
        phone: person.phone,
        address: person.address,
        email: person.email,
        introduction: person.introduction,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  insertEnterprise(person, sCallback) {
    this.request({
      url: '/enterprise/insertEnterprise',
      method: 'post',
      data: {
        userid: person.userid,
        name: person.name,
        icon: person.icon,
        type: person.type,
        financing: person.financing,
        phone: person.phone,
        address: person.address,
        email: person.email,
        introduction: person.introduction,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
}
export { Enterprise }