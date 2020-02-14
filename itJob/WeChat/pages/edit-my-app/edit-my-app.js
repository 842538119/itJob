import { Applicant } from '../../models/applicant.js'
let applicant = new Applicant()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    person:"",
    faceImg:"",
    name:"",
    age:0,
    gender:0,
    education:0,
    phone:"",
    city:"",
    address:"",
    email:"",
    introduction:"",
    addressLen: 0, //地址的字数
    genderlist: ['男', '女'], //性别数组
    educationlist: ['中专', '大专', '本科', '硕士', '博士']  //学历数组
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    //从本地缓存中异步获取指定 key 对应的内容。
    wx.getStorage({
      key: 'person',
      success: function (res) {
        that.setData({
          person: res.data,
          faceImg:res.data.icon,
          name:res.data.name,
          age:res.data.age,
          phone:res.data.phone,
          city:res.data.city,
          address:res.data.address,
          email:res.data.email,
          gender: res.data.sex,
          education: res.data.education,
          introduction:res.data.introduction,
        })
        if (res.data.address != null && res.data.address != "") {
          that.setData({ addressLen: res.data.address.length, })
        }
      }
    })
  },
  //上传图片
  uploadImgTap: function(){
    var that = this;
    wx.chooseImage({
      count: 1, // 默认9
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        var tempFilePaths = res.tempFilePaths
        that.setData({
          faceImg:tempFilePaths[0]
        })
      }
    })
  },
  //姓名
  nameTap: function(e){
    var eValue = e.detail.value;
    this.setData({
      name: eValue
    })
  },
  //性别
  bindPickerChangeSex: function(e){
    this.setData({
      gender: e.detail.value
    })
  },
  //学历
  bindPickerChangeEducation: function (e) {
    this.setData({
      education: e.detail.value
    })
  },
  //年龄
  ageTap: function (e) {
    var eValue = e.detail.value;
    this.setData({
      age: eValue
    })
  },
  //电话
  phoneTap: function (e) {
    var eValue = e.detail.value;
    this.setData({
      phone: eValue
    })
  },
  //电话
  emailTap: function (e) {
    var eValue = e.detail.value;
    this.setData({
      email: eValue
    })
  },
  //城市
  cityTap: function (e) {
    var eValue = e.detail.value;
    this.setData({
      city: eValue
    })
  },
  //地址
  addressTap: function (e) {
    var eValueLen = e.detail.value.length;
    var eValue = e.detail.value;
    this.setData({
      addressLen:eValueLen,
      address: eValue
    })
  },
  //个人介绍
  introductionTap: function (e) {
    wx.setStorageSync("content", this.data.introduction)
    wx.navigateTo({
      url: '../editor/editor'
    })
  },
  //保存 同步保存到缓存里
  submitTap: function(){
    var that = this;
    var person=this.data.person
    var faceImg = this.data.faceImg;
    person.age=this.data.age;
    person.name = this.data.name;
    person.sex = this.data.gender;
    person.education = this.data.education;
    person.phone = this.data.phone;
    person.email = this.data.email;
    person.city = this.data.city;
    person.address = this.data.address;
    person.introduction = this.data.introduction;
    if (person.icon != '' && person.icon != this.data.faceImg) {
      //调用图片上传接口
      wx.uploadFile({
        url: 'http://localhost:8085/upload/faceImg',
        filePath: faceImg,
        name: 'file',
        success: (res) => {
          that.setData({
            faceImg: res.data,
          })
          person.icon = this.data.faceImg;
          //调用后台数据更新接口
          applicant.updateApplicant(person, (res) => {
            if (res != '' && res != null) {
              wx.setStorageSync('person', person);
              wx.removeStorageSync('content');
              //更新上一级页面
              var pages = getCurrentPages();
              var curPage = pages[pages.length - 2];
              curPage.setData({
                name: this.data.name,
                faceImg: this.data.faceImg,
              });
              //返回上一个页面
              setTimeout(function () {
                wx.navigateBack({
                })
              }, 1000);
            }
          });
        },
      })
    }  
    else{
      //调用后台数据更新接口
      applicant.updateApplicant(person, (res) => {
        if (res != '' && res != null) {
          wx.setStorageSync('person', person);
          wx.removeStorageSync('content');
          //更新上一级页面
          var pages = getCurrentPages();
          var curPage = pages[pages.length - 2];
          curPage.setData({
            name: this.data.name,
            faceImg: this.data.faceImg,
          });
          //返回上一个页面
          setTimeout(function () {
            wx.navigateBack({
            })
          }, 1000);
        }
      });
    }
  }
})