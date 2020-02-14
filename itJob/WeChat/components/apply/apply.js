// components/apply/apply.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    applicant:{
      type:Object
    },
    position: {
      type: Object
    },
    apply:{
      type: Object
    },
    resumeId:{
      type:String
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    genderList:['男','女'],
    educationList: ['', '中专', '大专', '本科', '硕士', '博士'],
  },

  /**
   * 组件的方法列表
   */
  methods: {
    sureTap:function(){
      var applyId = this.properties.apply.id;
      this.triggerEvent('sure', { id: applyId }, {})
    },
    resumeTap:function(){
      var resumeId=this.data.resumeId;
      //比较缓存已有的简历是否一样
      if (wx.getStorageSync('resume')){
        if (wx.getStorageSync('resume').resume.id != resumeId) {
          wx.removeStorageSync('resume');
        }
      }
      wx.navigateTo({
        url: '/pages/resume/view-resume/view-resume?resumeId=' + resumeId,
      })
    }
  }
})
