import { Enterprise } from '../../models/enterprise.js'
import { Position } from '../../models/position.js'
let position = new Position()
let enterprise = new Enterprise()
Page({
  
  /**
     * 页面的初始数据
     */
  data: {
    enterpriseInfo: {},
    positionList:[],
  },

  /**
     * 生命周期函数--监听页面加载
     */
  onLoad: function (options) {
    var enterpriseId = options.enterpriseId;
    var that = this;
     position.getPositionListByEnterpriseId(enterpriseId,(res) =>{
       that.setData({
         positionList: res,
         enterpriseInfo: res[0].enterprise
       })
      })
  }
})
