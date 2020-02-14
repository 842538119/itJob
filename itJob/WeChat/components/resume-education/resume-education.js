// components/resume-education/resume-education.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    education:{
      type:Object
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    delIcon:"images/del.png",
    edulevelList: ['高中', '大专', '本科', '硕士', '博士'],//学历
  },

  /**
   * 组件的方法列表
   */
  methods: {
    onTap:function(){
      var eduId = this.properties.education.id;
      wx.navigateTo({
        url: '/pages/resume/edit-resume-edu/edit-resume-edu?eduId=' + eduId,
      })
    },
    delTap: function () {
      var eduId = this.properties.education.id;
      var that = this;
      wx.showModal({
        title: '确定删除该信息吗？',
        success(res) {
          if (res.confirm) {
            that.triggerEvent('delEdu', { id: eduId }, {})
          }
        }
      }) 
    }
  }
})
