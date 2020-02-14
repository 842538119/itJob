import { HTTP } from '../utils/http.js'
class Collection extends HTTP {
  getCollectionListByApplicantId(id, sCallback) {
    this.request({
      url: '/collection/getCollectionListByApplicantId',
      method: 'get',
      data: {
        applicantId: id,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  isCollected(collection, sCallback) {
    this.request({
      url: '/collection/isCollected',
      method: 'get',
      data: {
        applicantId: collection.applicantId,
        positionId: collection.positionId,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  insertCollection(collection, sCallback) {
    this.request({
      url: '/collection/insertCollection',
      method: 'post',
      data: {
        applicantId: collection.applicantId,
        positionId: collection.positionId,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  deleteCollection(collection, sCallback) {
    this.request({
      url: '/collection/deleteCollection',
      method: 'post',
      data: {
        applicantId: collection.applicantId,
        positionId: collection.positionId,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
}
export { Collection }