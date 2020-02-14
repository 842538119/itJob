import { Apply } from '../../models/apply.js'
import { Position } from '../../models/position.js'
import { Collection } from '../../models/collection.js'
let position = new Position()
let apply = new Apply()
let collection = new Collection()
var app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        userType:false,
        positionInfo: {},
        positionTypeInfo:{},
        enterpriseInfo:{},
        isHaveApply: false,
        isHaveCollect: false,
        showModal:false,
        resumeList:[],
        resumeIndex:0,
        educationList: ['不限', '中专', '大专', '本科', '硕士', '博士'],
        experienceList: ['不限', '应届毕业', '1年以下', '1-3年', '3-5年', '5年以上'],
        height:"100%"
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
      var positionId=options.positionId;
      var that=this;
      //获得职位详情并设置初始值
      position.getOneById(positionId,(res)=>{
        this.setData({
          positionInfo: res.position,
          positionTypeInfo:res.positionType,
          enterpriseInfo: res.enterprise
        })
        //根据身份判断是否显示投递、收藏按钮
        if (wx.getStorageSync('user').type == '求职者') {
          var applyInfo = new Object();
          applyInfo.applicantId = wx.getStorageSync('person').id;
          applyInfo.positionId = that.data.positionInfo.id;
          //获得职位投递信息
          apply.isApplied(applyInfo, (res) => {
            var flag = res === "false" ? false : true;
            that.setData({
              isHaveApply: flag,
              userType: true
            })
          })
          //获得职位收藏信息
          collection.isCollected(applyInfo, (res) => {
            var flag = res === "false" ? false : true;
            that.setData({
              isHaveCollect: flag,
            })
          })
        }
        else {
          that.setData({
            userType: false
          })
        }
      })   
    },

    //公司详情
    bindPositionDetailTap: function (event) {
        var enterpriseId=this.data.enterpriseInfo.id;
        // 切换页面
        wx.navigateTo({
          url: '../ent-detail/ent-detail?enterpriseId=' + enterpriseId
        });
    },
    //收藏职位
    onCollect: function () {
      var that=this;
      var applyInfo = new Object();
      applyInfo.applicantId = wx.getStorageSync('person').id;
      applyInfo.positionId = that.data.positionInfo.id;
      //取消收藏
      if(this.data.isHaveCollect){
        collection.deleteCollection(applyInfo,(res)=>{})
      }
      //收藏
      else{  
        collection.insertCollection(applyInfo,(res)=>{})
      }
      that.setData({
        isHaveCollect:!this.data.isHaveCollect
      })
    },
    //申请职位
    onSubmit: function () {
      if(wx.getStorageSync('resumeList').length==0){
        wx.showModal({
          title: '',
          content: '请先创建简历!',
        })
      }
      else{
        this.setData({
          resumeList: wx.getStorageSync('resumeList')
        })
        this.showModal();
      }
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
    //选择简历
  bindPickerChangeResume:function(e) {
    this.setData({
      resumeIndex: e.detail.value,
    })
  },
  //确定投递
  sure:function(){
    var that=this;
    var applicantId=wx.getStorageSync('person').id;
    var positionId = this.data.positionInfo.id;
    var resumeId = this.data.resumeList[this.data.resumeIndex].id;
    var applyInfo = new Object();
    applyInfo.applicantId=applicantId;
    applyInfo.positionId=positionId;
    applyInfo.resumeId=resumeId;
    apply.insertApply(applyInfo,(res)=>{
      that.setData({
        isHaveApply:true
      })
      this.hiddenModal();
    })
  }
})