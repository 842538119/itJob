import { Position } from '../../models/position.js'
let position = new Position()
var app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    name:"",
    positionTypeId:"",
    salary:"",
    education:0,
    experience:0,
    city:"",
    duties:"",
    positionTypeList: [], //岗位类别数组
    educationList: ['不限', '中专', '大专', '本科', '硕士', '博士'], 
    experienceList: ['不限', '应届毕业', '1年以下', '1-3年', '3-5年', '5年以上'],
    positionTypeIndex:0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;

    //获得岗位类别集合
    var positionType=wx.getStorageSync('positionType');
    for(var i=0;i<positionType.length;i++){
      var posId = "positionTypeList[" + i + "].id";
      var posName = "positionTypeList[" + i + "].name";
      that.setData({
        [posId]: positionType[i].id,
        [posName]: positionType[i].name
      }) 
    }
    //设置默认的岗位类型初始值
    that.setData({
      positionTypeId: positionType[0].id
    })
    //获得岗位职责描述
    if(wx.getStorageSync("content")){
      that.setData({
        duties: wx.getStorageSync("content")
      })
    }
  },
  //岗位名称
  nameTap: function(e){
    var eValue = e.detail.value;
    this.setData({
      name: eValue
    })
  },
  //岗位类型
  bindPickerChangeType: function(e){
    var eValue=e.detail.value
    var posId = this.data.positionTypeList[eValue].id
    this.setData({
      positionTypeId: posId,
      positionTypeIndex:eValue
    })
  },
  //学历要求
  bindPickerChangeEducation: function (e) {
    var eValue = e.detail.value;
    this.setData({
      education: eValue
    })
  },
  //经验要求
  bindPickerChangeExperience: function (e) {
    var eValue = e.detail.value;
    this.setData({
      experience: eValue
    })
  },
  //薪资
  salaryTap: function (e) {
    var eValue = e.detail.value;
    this.setData({
      salary: eValue
    })
  },
  //城市
  cityTap: function (e) {
    var eValue = e.detail.value;
    this.setData({
      city: eValue
    })
  },
  //岗位职责
  dutiesTap: function (e) {
    wx.setStorageSync("content", this.data.duties)
    wx.navigateTo({
      url: '../editor/editor'
    })
  },
  //保存 同步保存到缓存里
  submitTap: function(){
    var that = this;
    var positionInfo=new Object();
    var enterpriseId=wx.getStorageSync('person').id;
    positionInfo.name = this.data.name;
    positionInfo.enterpriseId = enterpriseId;
    positionInfo.positionTypeId=this.data.positionTypeId;
    positionInfo.salary = this.data.salary;
    positionInfo.education = this.data.education;
    positionInfo.experience = this.data.experience;
    positionInfo.city = this.data.city;
    positionInfo.duties = this.data.duties;
    //调用后台接口
    position.insertPosition(positionInfo, (res) => {
        if (res != '' && res != null) {
          wx.showToast({
            title: '提交成功',
            icon: 'success',
            duration: 800
          })
          wx.removeStorageSync('content');
          //返回上一个页面
          setTimeout(function () {
            wx.navigateBack({
            })
          }, 1000);
        }
      });
    
  }
})