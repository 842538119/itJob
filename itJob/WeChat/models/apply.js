import { HTTP } from '../utils/http.js'
class Apply extends HTTP {
  getAppliesByApplicantId(id, status,sCallback) {
    this.request({
      url: '/apply/getAppliesByApplicantId',
      method: 'get',
      data: {
        applicantId: id,
        status:status,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  getAppliesByEnterpriseId(id, status, sCallback) {
    this.request({
      url: '/apply/getAppliesByEnterpriseId',
      method: 'get',
      data: {
        enterpriseId: id,
        status: status,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  isApplied(apply, sCallback) {
    this.request({
      url: '/apply/isApplied',
      method: 'get',
      data: {
        applicantId: apply.applicantId,
        positionId: apply.positionId,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  insertApply(apply, sCallback) {
    this.request({
      url: '/apply/insertApply',
      method: 'post',
      data: {
        applicantId: apply.applicantId,
        positionId: apply.positionId,
        resumeId:apply.resumeId,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  updateApply(apply, sCallback) {
    this.request({
      url: '/apply/updateApply',
      method: 'put',
      data: {
        id: apply.id,
        status:apply.status,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
}
export { Apply }