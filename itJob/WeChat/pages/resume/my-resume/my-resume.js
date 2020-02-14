import { Resume } from '../../../models/resume.js'
let resume = new Resume()
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    personInfo:"",
    resumeBaseInfo: "",
    resumeExpList: [],
    resumeEduList: [],
    resumeProjectList: [],
    avatarUrl: '/images/small_avatar.png',
    genderlist: ['男', '女'],//性别
    edulevellist: ['高中', '大专', '本科', '硕士', '博士'],//学历
    worksYearlist: ['应届毕业生', '1年以下', '1-3年', '3-5年', '5-10年', ' 10年以上'],//工作年限
    citylist: ['北京', '上海', '广州', '杭州', '深圳', '其它'],//所在城市
    workTypelist: ['全职', '兼职', '实习生'],//工作类型
    salaryList: ['3k-4k', '4k-6k', '6k-8k', '8k-10k', '10K-15K', '15k以上'],//期望薪资
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var personInfo=wx.getStorageSync('person');
      var resumeInfo=wx.getStorageSync('resume');
      var that=this;
      //设置简历标题
      wx.setNavigationBarTitle({
        title: resumeInfo.name
      })
      //设置个人信息和简历信息
      this.setData({
        personInfo:personInfo,
        resumeBaseInfo: resumeInfo
      })
      //根据简历ID获得教育信息、实习信息和项目信息
    resume.getOneById(this.data.resumeBaseInfo.id,(res)=>{
      that.setData({
        resumeExpList: res.resumeExperiences,
        resumeEduList: res.resumeEducation,
        resumeProjectList: res.resumeProjects
      })
    }) 
  },
  //编辑求职信息
  editResumeInfoTap: function(){
    wx.navigateTo({
      url: '/pages/resume/edit-resume-base/edit-resume-base',
    })
  },
  //编辑实习信息
  editExpTap: function () {
    wx.navigateTo({
      url: '/pages/resume/edit-resume-exp/edit-resume-exp',
    })
  },
  //删除实习信息
  delExp: function (event) {
    var that=this;
    resume.deleteResumeExperience(event.detail.id,(res)=>{
      resume.getOneById(that.data.resumeBaseInfo.id, (res) => {
        that.setData({
          resumeExpList: res.resumeExperiences,
        })
      })
    })
  },
  //编辑教育信息
  editEduTap: function () {
    wx.navigateTo({
      url: '/pages/resume/edit-resume-edu/edit-resume-edu',
    })
  },
  //删除教育信息
  delEdu:function (event) {
    var that = this;
    resume.deleteResumeEducation(event.detail.id, (res) => {
      resume.getOneById(that.data.resumeBaseInfo.id, (res) => {
        that.setData({
          resumeEduList: res.resumeEducation,
        })
    })
    })
  },
  //编辑项目信息
  editProjectTap: function () {
    wx.navigateTo({
      url: '/pages/resume/edit-resume-project/edit-resume-project',
    })
  },
  //删除项目信息
  delProject: function (event) {
    var that = this;
    resume.deleteResumeProject(event.detail.id, (res) => {
      resume.getOneById(that.data.resumeBaseInfo.id, (res) => {
        that.setData({
          resumeProjectList: res.resumeProjects,
        })
      })
    })
  },
})