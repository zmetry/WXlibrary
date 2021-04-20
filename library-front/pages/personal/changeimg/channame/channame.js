// pages/personal/changeimg/channame/channame.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userid:'',
    username: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      this.setData({
        userid: wx.getStorageSync('userid')
      })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },
  usernameInput: function (e) {
    this.setData({
      username: e.detail.value
    })
  },
  changeName: function(){
    var that = this;
    if (this.data.username.length == 0 ) {
      wx.showToast({
        title: '用户名不能为空',
        icon: 'none',
        duration: 2000
      })
    } else{
      wx.request({
        url: 'http://localhost:8888/test/changename/'+that.data.userid+'/'+that.data.username,
        method: 'POST',
        header: {
          'content-type': 'application/x-www-form-urlencoded' // 默认值
        },
        success(res){
            wx.setStorageSync('userid', that.data.username);
            wx.request({
              url: 'http://localhost:8888/test/mail/name/'+that.data.userid+'/'+that.data.username,
              method: 'POST',
              header:{
                'content-type': 'application/x-www-form-urlencoded'
              }
            })
            wx.showModal({
              title: '更改用户名称成功',
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
      })
    }
    
  }

})