// components/resume-experience/resume-experience.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    experience:{
      type:Object
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    delIcon: "images/del.png"
  },

  /**
   * 组件的方法列表
   */
  methods: {
    onTap: function () {
      var expId = this.properties.experience.id;
      wx.navigateTo({
        url: '/pages/resume/edit-resume-exp/edit-resume-exp?expId=' + expId,
      })
    },
    delTap: function () {
      var expId = this.properties.experience.id;
      var that = this;
      wx.showModal({
        title: '确定删除该信息吗？',
        success(res) {
          if (res.confirm) {
            that.triggerEvent('delExp', { id: expId }, {})
          }
        }
      })
    }
  }
})
