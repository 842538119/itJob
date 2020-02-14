import { Resume } from '../../../models/resume.js'
let resume = new Resume()
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    position:"",
    city:"",
    salaryList: ['3k-4k', '4k-6k', '6k-8k', '8k-10k', '10K-15K','15k以上'],
    salaryIndex:0
  },

  /*
   生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var resume=wx.getStorageSync('resume');
    this.setData({
      position:resume.position,
      city:resume.city,
      salaryIndex:resume.salary
    })
  },
  //期望职位
  positionTap: function (e) {
    var eValue = e.detail.value;
    this.setData({
      position: eValue
    })
  },
  //期望薪资
  bindPickerChangeSalary: function (e) {
    this.setData({
      salaryIndex: e.detail.value
    })
  },
  //期望城市
  cityTap: function (e) {
    var eValue = e.detail.value;
    this.setData({
      city: eValue
    })
  },
  //保存
  submitResumeBaseTap: function(){
    var resumeInfo=new Object();
    var person = wx.getStorageSync("person");
    resumeInfo=wx.getStorageSync('resume');
    resumeInfo.position=this.data.position;
    resumeInfo.salary=this.data.salaryIndex;
    resumeInfo.city=this.data.city;
    //更新简历
    resume.updateResume(resumeInfo,(res)=>{
        //更新上一级页面
        var pages = getCurrentPages();
        var curPage = pages[pages.length - 2];
        var prePage = pages[pages.length - 3];

        //更新简历详情页
        curPage.setData({
          resumeBaseInfo: resumeInfo
        });
        wx.setStorageSync('resume', resumeInfo);

        //更新简历列表页
        resume.getResumeByApplicantId(person.id, (res) => {
        prePage.setData({
          resumeList: res
        })
        wx.setStorageSync('resumeList', res);
        })

        //返回上一个页面
        setTimeout(function () {
          wx.navigateBack({
          })
        }, 800);
      })
   },
})