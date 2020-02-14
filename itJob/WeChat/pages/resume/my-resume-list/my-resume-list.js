import { Resume } from '../../../models/resume.js'
let resume = new Resume()
var app = getApp();
Page({
  /**
  * 页面的初始数据
  */
  data: {
    resumeList: [],
    id:"",
    name:"",
    showModal:false,  //简历输入框
    editList: true,//此属性用于标识编辑职位or预览职位
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    var person = wx.getStorageSync("person");
    if (wx.getStorageSync('resumeList')){
      this.setData({
        resumeList: wx.getStorageSync('resumeList')
      })
    }
    else{
      resume.getResumeByApplicantId(person.id, (res) => {
        this.setData({
          resumeList: res
        })
        wx.setStorageSync('resumeList', res);
      })
    }
  },
  //新增简历
  onAdd: function (event) {
    var person = wx.getStorageSync("person");
    var that = this;
    var resumeInfo = new Object();
    resumeInfo.applicantId = person.id;
    resumeInfo.name = this.data.name;
    resume.insertResume(resumeInfo, (res) => {
      resume.getResumeByApplicantId(person.id, (res) => {
        that.setData({
          resumeList: res
        })
        wx.setStorageSync('resumeList', res);
        this.hiddenModal();
      })
    })
  },
  //删除简历
  onDel: function (event) {
    var person = wx.getStorageSync("person");
    var that = this;
    resume.deleteResume(event.detail.id, (res) => {
      resume.getResumeByApplicantId(person.id, (res) => {
        that.setData({
          resumeList: res
        })
        wx.setStorageSync('resumeList', res);
      })
    })
  },
  //获得当前简历名并弹框
  onName:function(event){
    this.setData({
      name: event.detail.name,
      id: event.detail.id
    })
    this.showModal();
  },
  //更改简历名
  onUpdate: function(resumeId) {
    var person = wx.getStorageSync("person");
    var that = this;
    var resumeInfo = new Object();
    resumeInfo.id = this.data.id;
    resumeInfo.name = this.data.name;
    resume.updateResume(resumeInfo, (res) => {
      resume.getResumeByApplicantId(person.id, (res) => {
        that.setData({
          resumeList: res
        })
        wx.setStorageSync('resumeList', res);
        this.hiddenModal();
      })
    })
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
  // 模态框确定按钮
  sure: function () {
    if (this.data.name == '') {
      wx.showToast({
        title: '请填写简历名!',
        icon: 'none'
      })
    } 
    else 
    {
      if(this.data.id!=''){
        this.onUpdate();
        }
      else
      {
       this.onAdd();
      }
    }
  },
  changeResumeName: function (e) {
    this.setData({
      name: e.detail.value
    })
  },
})