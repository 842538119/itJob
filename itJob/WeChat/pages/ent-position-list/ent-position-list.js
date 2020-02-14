import { Position } from '../../models/position.js'
let position = new Position()
var app = getApp();
Page({
  /**
  * 页面的初始数据
  */
  data: {
    positioresumeList:[],
    editList:true,//此属性用于标识编辑职位or预览职位
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    var person=wx.getStorageSync("person");
    position.getPositionListByEnterpriseId(person.id,(res)=>{
      this.setData({
        positionList:res
      })
    })
  },
  onRevoke:function(event){
    var person = wx.getStorageSync("person");
    var that=this;
    position.revokePosition(event.detail.id, (res) => {
      position.getPositionListByEnterpriseId(person.id, (res) => {
        that.setData({
          positionList: res
        })
      })
    }) 
  },
})