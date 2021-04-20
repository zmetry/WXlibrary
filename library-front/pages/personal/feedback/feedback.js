// pages/personal/feedback/feedback.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isShowSelectcall: "none",
    isShowSelectdayu: "inline"
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      this.setData({
        isShowSelectcall: "none",
        isShowSelectdayu: "inline"
      })
  },
  show: function(){
    this.setData({
      isShowSelectcall: "inline",
      isShowSelectdayu: "none"
    })
  }
  
})