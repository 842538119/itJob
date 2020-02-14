import { Resume } from '../../../models/resume.js'
let resume = new Resume()
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    resumeEducation:"",
    resumeId:"",
    school:"",
    profession:"",
    startYear:"2015-02",
    endYear: "2016-02",
    edulevelList: ['高中', '大专', '本科', '硕士', '博士'],//学历
    edulevelIndex: 2,//默认本科
    raduationTime:"",      // 默认时间
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    //初始化时间
    var newDate = new Date();
    var Month = newDate.getMonth() < 10 ? "0" + (newDate.getMonth() + 1) : "" + (newDate.getMonth() + 1);
    this.setData({
      raduationTime: newDate.getFullYear() + "-" + Month,
      startYear: newDate.getFullYear() + "-" + Month,
      endYear: newDate.getFullYear() + "-" + Month,
    });
    //获得对应的教育信息ID
    var eduId=options.eduId;
    //ID存在，表示编辑
    if (eduId != undefined){
      resume.getResumeEducationById(eduId,(res)=>{
        that.setData({
          resumeEducation:res,
          school:res.school,
          profession:res.profession,
          edulevelIndex:res.education,
          startYear:res.startYear,
          endYear:res.endYear,
        })
      })
    }
  },

  //学校名称
  schoolTap: function(e){
  this.setData({
    school: e.detail.value
  })
  }, 

  //专业名称
  professionTap: function(e){
  this.setData({
   profession: e.detail.value
  })
  }, 

  //学历
  bindPickerChangeEduLevel: function (e) {
    this.setData({
      edulevelIndex: e.detail.value
    })
  },

  //入学时间
  bindDateChangeStartYear: function (e) {
    this.setData({
      startYear: e.detail.value
    })
  },
  //毕业时间
  bindDateChangeEndYear: function (e) {
    this.setData({
      endYear: e.detail.value
    })
  },
  //提交教育信息
  submitSchoolTap: function(e){ 
    if (this.data.school == '' || this.data.school == undefined || this.data.profession == '' || this.data.profession == undefined) {
      wx.showModal({
        content: "请填写完整信息!"
      });
    }else{
      var resumeInfo=new Object();
      //编辑处理
      if(this.data.resumeEducation!=""){
        resumeInfo=this.data.resumeEducation;
        resumeInfo.school = this.data.school;
        resumeInfo.profession = this.data.profession;
        resumeInfo.education = this.data.edulevelIndex;
        resumeInfo.startYear=this.data.startYear;
        resumeInfo.endYear=this.data.endYear;
        //调用更新教育信息接口
        resume.updateResumeEducation(resumeInfo,(res)=>{
          //获得更新后的教育信息
          resume.getOneById(resumeInfo.resumeId, (res) => {
            //更新上一级页面
            var pages = getCurrentPages();
            var curPage = pages[pages.length - 2];
            curPage.setData({
              resumeEduList: res.resumeEducation
            });
          })
          //返回上一个页面
          setTimeout(function () {
            wx.navigateBack({
            })
          }, 800);
        })
      }
      //新增处理
      else{
        resumeInfo.resumeId=wx.getStorageSync('resume').id;
        resumeInfo.school = this.data.school;
        resumeInfo.profession = this.data.profession;
        resumeInfo.education = this.data.edulevelIndex;
        resumeInfo.startYear = this.data.startYear;
        resumeInfo.endYear = this.data.endYear;
        //调用新增教育信息接口
        resume.insertResumeEducation(resumeInfo, (res) => {
          //获得更新后的教育信息
          resume.getOneById(resumeInfo.resumeId, (res) => {
            //更新上一级页面
            var pages = getCurrentPages();
            var curPage = pages[pages.length - 2];
            curPage.setData({
              resumeEduList: res.resumeEducation
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