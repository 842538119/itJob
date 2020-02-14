import { Apply } from '../../models/apply.js'
let apply = new Apply()
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    applyId:"",
    applyList:[],
    statusIndex:0,
    submitStatusList:['暂不处理','通过','拒绝','岗位撤销'],
    statusList:['已申请','待沟通','不合适','已撤销'],
    currentData: 0,
    showModal:false,
    selectPerson: true,
    editList:false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    var id=wx.getStorageSync('person').id;
    apply.getAppliesByEnterpriseId(id, that.data.currentData,(res)=>{
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
    apply.getAppliesByEnterpriseId(id, that.data.currentData, (res) => {
      that.setData({
        applyList: res
      })
    })
  },
  //点击切换，滑块index赋值
  checkCurrent: function (e) {
    var that = this;

    if (that.data.currentData === e.target.dataset.current) {
      return false;
    } else {

      that.setData({
        currentData: e.target.dataset.current
      })
    }
  },
  submitTap:function(e){
   this.setData({
     applyId:e.detail.id
   })
   this.showModal();
  },
  //显示模态框
  showModal: function () {
    this.setData({
      showModal: true
    })
  },
  //隐藏模态框
  hiddenModal: function () {
    this.setData({
      showModal: false,
    })
  },
  //选择投递状态
  bindPickerChangeStatus: function (e) {
    this.setData({
      statusIndex: e.detail.value,
    })
  },
  //确定投递
  sure: function () {
    var that = this;
    var applyId = this.data.applyId;
    var applyInfo = new Object();
    applyInfo.id = this.data.applyId;
    applyInfo.status = this.data.statusIndex;
    apply.updateApply(applyInfo, (res) => {
      this.hiddenModal();
      var id = wx.getStorageSync('person').id;
      apply.getAppliesByEnterpriseId(id, that.data.currentData, (res) => {
        that.setData({
          statusIndex:0,
          applyList: res
        })
      })
    })
  }
})