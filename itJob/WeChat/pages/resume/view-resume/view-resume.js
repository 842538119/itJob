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
    var resumeId = options.resumeId;;
    var that=this;
    if(wx.getStorageSync('resume')){
      var res = wx.getStorageSync('resume');
      that.setData({
        personInfo: res.applicant,
        resumeBaseInfo: res.resume,
        resumeExpList: res.resumeExperiences,
        resumeEduList: res.resumeEducation,
        resumeProjectList: res.resumeProjects
      })
    }
    else{
      //根据简历ID获得个人信息、简历信息、教育信息、实习信息和项目信息
      resume.getOneById(resumeId, (res) => {
        that.setData({
          personInfo: res.applicant,
          resumeBaseInfo: res.resume,
          resumeExpList: res.resumeExperiences,
          resumeEduList: res.resumeEducation,
          resumeProjectList: res.resumeProjects
        })
        wx.setStorageSync('resume', res);
      })  
    }
  },
})