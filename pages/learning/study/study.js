// pages/learning/study/study.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    BfDate: '',
    hour: '',
    min: '',
    btHour: '',
    btMin: '',
    Learid: '',
    userid: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var date = new Date();
    that.setData({
      hour: date.getHours(),
      min: date.getMinutes(),
      userid: wx.getStorageSync('userid')
    })
    wx.request({
      url: 'http://localhost:8888/test/UserStudy/getMsg/'+that.data.userid,
      method: 'POST',
      header:{
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function(res){
        console.log(res.data.bfDate)
        that.setData({
          BfDate: res.data.bfDate,
          Learid: res.data.learnId
        })
      }
    })
    setInterval(function(){
      var date11 = new Date();
      var hour = date11.getHours();
      var min = date11.getMinutes();
      var NoDate = hour * 60 + min;
      var BfDate = that.data.BfDate;
      that.setData({
        btHour: Math.floor((NoDate - BfDate) / 60),
        btMin:  (NoDate - BfDate) % 60
      })
    },1000)
  },
  
  onUnload: function() {
    // 页面销毁时执行
    wx.reLaunch({
      url: '/pages/index/index',
    })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  leave: function(){
    var that = this;
    wx.request({
      url: 'http://localhost:8888/test/libseat/unreserve/'+
      that.data.Learid,
      method:'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function(res){
        if(res.data == true){
          wx.request({
            url: 'http://localhost:8888/test/UserStudy/unreserve/'+that.data.userid,
            method:'POST',
            header: {
                'content-type': 'application/x-www-form-urlencoded'
            },
          })
          wx.showModal({
            title: '签退成功',
            content: '即将返回首页',
            success(res){
              if(res.confirm){
                wx.switchTab({
                  url: '/pages/index/index'
                });
              }else{
                wx.switchTab({
                  url: '/pages/index/index'
                });
              }
            }
          });
        }
      }
    })
  },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})