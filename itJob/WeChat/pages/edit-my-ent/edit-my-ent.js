import { Enterprise } from '../../models/enterprise.js'
let enterprise = new Enterprise()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    person:"",
    faceImg:"",
    name:"",
    type:0,
    financing:"",
    phone:"",
    address:"",
    email:"",
    introduction:"",
    addressLen: 0, //地址的字数
    typelist: ['有限责任公司', '股份有限公司', '集团公司', '一人制公司']  //公司类型数组
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
          type:res.data.type,
          financing: res.data.financing,
          phone:res.data.phone,
          address:res.data.address,
          email:res.data.email,
          introduction:res.data.introduction,
        })
        if (res.data.address!=null && res.data.address!=""){
          that.setData({ addressLen: res.data.address.length,})
        }
        if (res.data.introduction!=null && res.data.introduction!=""){
          that.setData({ selfLen: res.data.introduction.length,})
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
         faceImg: tempFilePaths[0]
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
  //公司类型
  bindPickerChangeType: function(e){
    this.setData({
      type: e.detail.value
    })
  },
  //融资情况
  financingTap: function (e) {
    var eValue = e.detail.value;
    this.setData({
      financing: eValue
    })
  },
  //电话
  phoneTap: function (e) {
    var eValue = e.detail.value;
    this.setData({
      phone: eValue
    })
  },
  //邮箱
  emailTap: function (e) {
    var eValue = e.detail.value;
    this.setData({
      email: eValue
    })
  },
  //地址
  addressTap: function (e) {
    var eValueLen = e.detail.value.length;
    var eValue = e.detail.value;
    this.setData({
      addressLen: eValueLen,
      address: eValue
    })
  },
  //公司介绍
  introductionTap: function (e) {
    wx.setStorageSync("content", this.data.introduction)
    wx.navigateTo({
      url: '../editor/editor'
    })
  },
  //保存 同步保存到缓存里
  submitTap: function(){
    var that = this;
    var person=this.data.person;
    var faceImg=this.data.faceImg;
    person.type=this.data.type;
    person.name = this.data.name;
    person.phone = this.data.phone;
    person.email = this.data.email;
    person.financing = this.data.financing;
    person.address = this.data.address;
    person.introduction = this.data.introduction;
    if(person.icon!='' && person.icon!=this.data.faceImg){
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
          enterprise.updateEnterprise(person, (res) => {
            if (res != '' && res != null) {
              wx.setStorageSync('person', person);
              wx.removeStorageSync('content');
              //更新上一级页面
              var pages = getCurrentPages();
              var curPage = pages[pages.length - 2];
              curPage.setData({
                name: that.data.name,
                faceImg: that.data.faceImg,
              });
            }
            //返回上一个页面
            setTimeout(function () {
              wx.navigateBack({
              })
            }, 1000);
          });
        },
      })
    }
    else{
      //调用后台数据更新接口
      enterprise.updateEnterprise(person, (res) => {
        if (res != '' && res != null) {
          wx.setStorageSync('person', person);
          wx.removeStorageSync('content');
          //更新上一级页面
          var pages = getCurrentPages();
          var curPage = pages[pages.length - 2];
          curPage.setData({
            name: that.data.name,
            faceImg: that.data.faceImg,
          });
        }
        //返回上一个页面
        setTimeout(function () {
          wx.navigateBack({
          })
        }, 1000);
      });
    }   
  }
})