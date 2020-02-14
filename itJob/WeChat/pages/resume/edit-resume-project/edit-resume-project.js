// pages/resume/edit-resume-project/edit-resume-project.js
import { Resume } from '../../../models/resume.js'
let resume = new Resume()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    resumeProject: "",
    name: "",
    personJob: "",
    startTime: "",
    endTime: "",
    raduationTime: "",
    content: "",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    //初始化时间
    var newDate = new Date();
    var Month = newDate.getMonth() < 10 ? "0" + (newDate.getMonth() + 1) : "" + (newDate.getMonth() + 1);
    var Day = newDate.getDay() < 10 ? "0" + (newDate.getDay() + 1) : "" + (newDate.getDay());
    this.setData({
      raduationTime: newDate.getFullYear() + "-" + Month + "-" + Day,
      startTime: newDate.getFullYear() + "-" + Month + "-" + Day,
      endTime: newDate.getFullYear() + "-" + Month + "-" + Day,
    });
    //获得对应的项目信息ID
    var projectId = options.projectId;
    //ID存在，表示编辑
    if (projectId != undefined) {
      resume.getResumeProjectById(projectId, (res) => {
        that.setData({
          resumeProject: res,
          name: res.name,
          personJob: res.personJob,
          startTime: res.startTime,
          endTime: res.endTime,
          content: res.content
        })
      })
    }
  },
  //项目名称
  nameTap: function (e) {
    this.setData({
      name: e.detail.value
    })
  },
  //主要职责
  personJobTap: function (e) {
    this.setData({
      personJob: e.detail.value
    })
  },
  //入职时间
  bindDateChangeStartTime: function (e) {
    this.setData({
      startTime: e.detail.value
    })
  },
  //离职时间
  bindDateChangeEndTime: function (e) {
    this.setData({
      endTime: e.detail.value
    })
  },
  //实习描述
  contentTap: function (e) {
    wx.setStorageSync("content", this.data.content)
    wx.navigateTo({
      url: '../../editor/editor'
    })
  },
  //提交项目信息
  submitTap: function (e) {
    if (this.data.name == '' || this.data.name == undefined || this.data.personJob == '' || this.data.personJob == undefined ) {
      wx.showModal({
        content: "请填写完整信息!"
      });
    }
    else {
      var resumeInfo = new Object();
      //编辑处理
      if (this.data.resumeProject != "") {
        resumeInfo = this.data.resumeProject;
        resumeInfo.name = this.data.name;
        resumeInfo.personJob = this.data.personJob;
        resumeInfo.startTime = this.data.startTime;
        resumeInfo.endTime = this.data.endTime;
        resumeInfo.content = this.data.content
        //调用更新项目信息接口
        resume.updateResumeProject(resumeInfo, (res) => {
          //获得更新后的项目信息
          resume.getOneById(resumeInfo.resumeId, (res) => {
            //更新上一级页面
            var pages = getCurrentPages();
            var curPage = pages[pages.length - 2];
            curPage.setData({
              resumeProjectList: res.resumeProjects
            });
          })
          wx.removeStorageSync('content');
          //返回上一个页面
          setTimeout(function () {
            wx.navigateBack({
            })
          }, 800);
        })
      }
      //新增处理
      else {
        resumeInfo.resumeId = wx.getStorageSync('resume').id;
        resumeInfo.name = this.data.name;
        resumeInfo.personJob = this.data.personJob;
        resumeInfo.startTime = this.data.startTime;
        resumeInfo.endTime = this.data.endTime;
        resumeInfo.content = this.data.content
        //调用新增项目信息接口
        resume.insertResumeProject(resumeInfo, (res) => {
          //获得更新后的项目信息
          resume.getOneById(resumeInfo.resumeId, (res) => {
            //更新上一级页面
            var pages = getCurrentPages();
            var curPage = pages[pages.length - 2];
            curPage.setData({
              resumeProjectList: res.resumeProjects
            });
          })
          //返回上一个页面
          setTimeout(function () {
            wx.navigateBack({
            })
          }, 800);
        })
      }
    }
  },  
})