import { Apply } from '../../models/apply.js'
let apply = new Apply()
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    applyList:[],
    statusList:['已申请','待沟通','不合适','已撤销'],
    currentData: 0,
    selectPerson: true,
    editList:false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    var id=wx.getStorageSync('person').id;
    apply.getAppliesByApplicantId(id, that.data.currentData,(res)=>{
      that.setData({
        applyList:res
      })
    })
  },
  //获取当前滑块的index
  bindchange: function (e) {
    var that = this;
    var id = wx.getStorageSync('person').id;
    that.setData({
      applyList:[],
      currentData: e.detail.current
    })
    apply.getAppliesByApplicantId(id, that.data.currentData, (res) => {
      that.setData({
        applyList: res
      })
    })
  },
  //点击切换，滑块index赋值
  checkCurrent: function (e) {
    const that = this;

    if (that.data.currentData === e.target.dataset.current) {
      return false;
    } else {

      that.setData({
        currentData: e.target.dataset.current
      })
    }
  }
})