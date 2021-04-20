// pages/personal/changeimg/changepwd/changepwd.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
      userid: '',
      userpwd:'',
      oldpwd: '',
      newpwd1:'',
      newpwd2:'',
      old: 'none',
      new1: 'none',
      new2: 'none'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      this.setData({
        userid: wx.getStorageSync('userid'),
        userpwd: wx.getStorageSync('userpwd')
      })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.setData({
      userid: wx.getStorageSync('userid'),
      userpwd: wx.getStorageSync('userpwd')
    })
  },
  inputOldpwd: function(e){
    var that = this;
    this.setData({
      oldpwd: e.detail.value
    })
  },
  inputNewpwd1: function(e){
    var that  = this;
    if(that.data.userpwd != that.data.oldpwd){
        this.setData({
          old: 'inline'
        })
    }else{
      this.setData({
        old: 'none',
        newpwd1: e.detail.value
      })
    }
  },
  inputNewpwd2: function(e){
    var that = this;
    if (that.data.newpwd1.length == 0 || 
      that.data.newpwd1 == that.data.userpwd){
      this.setData({
        new1: "inline"
      })
    }else{
      this.setData({
        new1: "none",
        newpwd2:e.detail.value
      })
    }
  },
  submit: function(){
    var that = this;
    //先判断前面的是否全部相等
    if(that.data.newpwd1 != that.data.newpwd2){
      this.setData({
        new1: 'inline',
        new2: 'inline'
      })
    }else{
      this.setData({
        new1: 'none',
        new2: 'none'
      })
      //开始更改密码
      wx.request({
        url: 'http://localhost:8888/test/changepwd/'+that.data.userid+'/'+that.data.newpwd2,
        method: 'POST',
        header: {
          'content-type': 'application/x-www-form-urlencoded' // 默认值
        },
        data:{
          userid: this.data.userid,
          userpwd: that.data.userpwd
        },
        success(res){
          console.log(res.data)
          if(res.data == true){
            wx.setStorageSync('userpwd', that.data.newpwd2)
            wx.showModal({
              title: '修改密码成功',
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
    }
  }

})