Component({
  /**
  * 组件的属性列表
  */
  properties: {
    name:{
      type:Object
    },
    applicant:{
      type: Object
    },
    resume: {
      type: Object
    },
    position:{
      type: Object
    },
    flag: {
      type: Boolean
    },
  },

  /**
   * 组件的初始数据
   */
  data: {
    salaryList: ['3k-4k', '4k-6k', '6k-8k', '8k-10k', '10K-15K', '15k以上'],   
    editImg: './images/edit.png',
    editNameImg: './images/edit1.png',
    delImg: './images/delete.png'
  },

  /**
   * 组件的方法列表
   */
  methods: {
    onTap(event) {
      wx.setStorageSync('resume', this.properties.resume);
      wx.navigateTo({
        url: '/pages/resume/my-resume/my-resume',
      })
    },
    editTap(event) {
      wx.setStorageSync('resume', this.properties.resume);
      wx.navigateTo({
        url: '/pages/resume/my-resume/my-resume',
      })
    },
    delTap(event) {
      var resumeId = this.properties.resume.id;
      var that = this;
      wx.showModal({
        title: '确定删除该简历吗',
        success(res) {
          if (res.confirm) {
            that.triggerEvent('delete', { id: resumeId }, {})
          }
        }
      })
    },
    nameTap(e) {
      var resumeName = this.properties.resume.name;
       var resumeId = this.properties.resume.id;
       this.triggerEvent('name', { id: resumeId, name: resumeName }, {})
    }
  }
});