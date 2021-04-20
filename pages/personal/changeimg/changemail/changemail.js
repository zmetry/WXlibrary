// pages/personal/changeimg/channame/channame.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userid:'',
    username: '',
    mail: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      this.setData({
        userid: wx.getStorageSync('userid'),
        mail: wx.getStorageSync('mail')
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
        url: 'http://localhost:8888/test/mail/mail/'+that.data.userid+'/'+that.data.username,
        method: 'POST',
        header: {
          'content-type': 'application/x-www-form-urlencoded' // 默认值
        },
        success(res){
            wx.setStorageSync('mail', that.data.username);
            if(res.data == true){
              wx.showModal({
                title: '更改邮箱成功',
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
            }else{
              wx.showToast({
                title: '邮箱格式不对',
                icon: 'none',
                duration: 2000
              })
            }
        }
      })
    }
    
  }

})