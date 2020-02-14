// pages/resume/edit-resume-exp/edit-resume-exp.js
import { Resume } from '../../../models/resume.js'
let resume = new Resume()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    resumeExperience:"",
    company:"",
    department:"",
    job:"",
    startTime:"",
    endTime: "",
    raduationTime:"",
    content:"",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    //初始化时间
    var newDate = new Date();
    var Month = newDate.getMonth() < 10 ? "0" + (newDate.getMonth() + 1) : "" + (newDate.getMonth() + 1);
    var Day = newDate.getDay() < 10 ? "0"+ (newDate.getDay() + 1) : "" + (newDate.getDay());
    this.setData({
      raduationTime: newDate.getFullYear() + "-" + Month + "-" + Day,
      startTime: newDate.getFullYear() + "-" + Month + "-" + Day,
      endTime: newDate.getFullYear() + "-" + Month +"-" + Day,
    });
    //获得对应的实习信息ID
    var expId = options.expId;
    //ID存在，表示编辑
    if (expId != undefined) {
      resume.getResumeExperienceById(expId, (res) => {
        that.setData({
          resumeExperience: res,
          company: res.company,
          department: res.department,
          job: res.job,
          startTime: res.startTime,
          endTime: res.endTime,
          content:res.content
        })
      })
    }
  },
  //公司名称
  companyTap: function (e) {
    this.setData({
      company: e.detail.value
    })
  }, 
  //所属部门
  departmentTap: function (e) {
    this.setData({
      department: e.detail.value
    })
  }, 
  //职位
  jobTap: function (e) {
    this.setData({
      job: e.detail.value
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
  contentTap: function(e){
    wx.setStorageSync("content", this.data.content)
    wx.navigateTo({
      url: '../../editor/editor'
    })
  },
  //提交实习信息
  submitTap: function (e) {
    if (this.data.company == '' || this.data.company == undefined || this.data.department == '' || this.data.department == undefined || this.data.job == '' || this.data.job == undefined) {
      wx.showModal({
        content: "请填写完整信息!"
      });
    } 
    else {
      var resumeInfo = new Object();
      //编辑处理
      if (this.data.resumeExperience != "") {
        resumeInfo = this.data.resumeExperience;
        resumeInfo.company = this.data.company;
        resumeInfo.department = this.data.department;
        resumeInfo.job = this.data.job;
        resumeInfo.startTime = this.data.startTime;
        resumeInfo.endTime = this.data.endTime;
        resumeInfo.content=this.data.content
        //调用更新实习信息接口
        resume.updateResumeExperience(resumeInfo, (res) => {
          //获得更新后的实习信息
          resume.getOneById(resumeInfo.resumeId, (res) => {
            //更新上一级页面
            var pages = getCurrentPages();
            var curPage = pages[pages.length - 2];
            curPage.setData({
              resumeExpList: res.resumeExperiences
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
        resumeInfo.company = this.data.company;
        resumeInfo.department = this.data.department;
        resumeInfo.job = this.data.job;
        resumeInfo.startTime = this.data.startTime;
        resumeInfo.endTime = this.data.endTime;
        resumeInfo.content = this.data.content
        //调用新增实习信息接口
        resume.insertResumeExperience(resumeInfo, (res) => {
          //获得更新后的实习信息
          resume.getOneById(resumeInfo.resumeId, (res) => {
            //更新上一级页面
            var pages = getCurrentPages();
            var curPage = pages[pages.length - 2];
            curPage.setData({
              resumeExpList: res.resumeExperiences
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