// pages/personal/login/findpwd/code/code.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    minute: 60,
    yanStatus: false,
    userid: '',
    ycode: ''
  },
  submit: function(){
    var that = this;
    var inputCode = that.data.ycode;
    if(inputCode == ''){

    }else{
      if(inputCode == that.data.verifymo){
        wx.request({
          url: 'http://localhost:8888/test/resign/findpwd/'+that.data.userid,
          method: 'POST',
          header:{
            'content-type': 'application/x-www-form-urlencoded'
          },
          success: function(res){
            var pwd = res.data;
            wx.showModal({
              title: '验证码正确,即将返回登陆界面',
              content: '您的密码为：'+pwd,
              success(res){
                if(res.confirm){
                  wx.navigateTo({
                    url: '/pages/personal/login/login'
                  });
                }else{
                  wx.navigateTo({
                    url: '/pages/personal/login/login'
                  });
                }
              }
            });
          }
        })
      }else{
        wx.showModal({
          title: 'Error',
          content: '验证码输入错误'
        });
      }
    }
    
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.setData({
      userid: wx.getStorageSync("findpwd")
    })
  },

  verify: function(e) {
    this.setData({
      verifymo:  e.detail.value,
    })
  },
  

  yanZheng: function(e) {
    var that = this
    wx.request({
      url: 'http://localhost:8888/test/mail/SendMail/'+that.data.userid,
      method: 'GET',
      header:{
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function(res){
        that.setData({
          ycode: res.data
        })
      }
    })
    this.minute = setInterval(function() {//倒计时
      var minute = that.data.minute;
      minute--;
      that.setData({
        minute: minute,
        yanStatus: true,
      })
      if (minute == 0) {
        clearInterval(that.minute);
        that.setData({
          minute: 60,
          yanStatus: false,
        })
      }
    }, 1000)//一秒执行一次


  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

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

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

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