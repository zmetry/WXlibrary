// pages/personal/leared/leared.js
Page({

  data: {
    userid: "",
    usermessages: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      that.setData({
        userid: wx.getStorageSync('userid')
      })
      wx.request({
        url: 'http://localhost:8888/test/usermessage/allmessage/'+that.data.userid,
        method: 'post',
        data: {
          username: that.data.userid
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success(res){
          console.log(res.data)
            that.setData({
              usermessages: res.data
            })
        }
      })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var that = this;
    that.setData({
      userid: wx.getStorageSync('userid')
    })
    
  }



})