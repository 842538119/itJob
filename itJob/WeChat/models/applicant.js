import { HTTP } from '../utils/http.js'
class Applicant extends HTTP {
  getApplicantByUserId(id,sCallback) {
    this.request({
      url: '/applicant/getOneByUserId',
      method: 'get',
      data: {
        userid:id,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  updateApplicant(person, sCallback){
    this.request({
      url: '/applicant/updateApplicant',
      method: 'post',
      data: {
        id:person.id,
        userId:person.userid,
        name:person.name,
        sex:person.sex,
        age:person.age,
        icon:person.icon,
        education:person.education,
        phone:person.phone,
        city:person.city,
        address:person.address,
        email:person.email,
        introduction:person.introduction,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
}
export { Applicant }