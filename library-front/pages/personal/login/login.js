//index.js
//获取应用实例
const app = getApp()
 
Page({
  data: {
    username: '',
    password: '',
    login:true,
    image:''
  },
  loginTap:function(){
    this.setData({
    login:true
    });
   },
   register:function(){
   this.setData({
    login: false
   });
   },

   findpwd: function(){
    wx.navigateTo({
      url: 'findpwd/findpwd'
    })
   },

  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onShow: function () {
    // 生命周期函数--监听页面显示
    this.setData({
      username: wx.getStorageSync('userid'),
      password: wx.getStorageSync('userpwd'),
      image: wx.getStorageSync('userimage')
    })
  },
  onLoad: function () {
    this.setData({
      username: wx.getStorageSync('userid'),
      password: wx.getStorageSync('userpwd')
    })
  },
 
 
  // 获取输入账号 
  usernameInput: function (e) {
    this.setData({
      username: e.detail.value
    })
  },
 
  // 获取输入密码 
  passwordInput: function (e) {
    this.setData({
      password: e.detail.value
    })
  },
 
  // 登录处理
  login: function () {
    var that = this;
    if (this.data.username.length == 0 || this.data.password.length == 0) {
      wx.showToast({
        title: '账号或密码不能为空',
        icon: 'none',
        duration: 2000
      })
    } else {
      wx.request({
        url: 'http://localhost:8888/test/login/'+that.data.username+'/'+that.data.password,
        method: 'post',
        data: {
          username: that.data.username,
          password: that.data.password
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded' // 默认值
        },
        //登陆成功的处理
        success(res) {
          var flag = res.data;
          console.log(that.data.username+"1" +that.data.password)
          if(flag == true){
            wx.setStorageSync("userid",that.data.username);
            wx.setStorageSync('userpwd',that.data.password);
            wx.request({
              url: 'http://localhost:8888/test/userimage/'+that.data.username,
              method: 'get',
              data: {
                username: that.data.username,
                password: that.data.password
              },
              header: {
                'content-type': 'application/x-www-form-urlencoded' // 默认值
              },
              success(res){
                var imaurl = '';
                if(res.statusCode == '200'){
                  imaurl = 'http://localhost:8888/test/userimage/'+that.data.username;
                }else{
                  imaurl = 'http://localhost:8888/test/userimage/user.jpg';
                }
                //去请求相应的用户图片
                wx.setStorageSync('images', imaurl)
                that.setData({
                username: that.data.username,
                password: that.data.password,
                image: imaurl
              })
            }
            })
            wx.showModal({
              title: '登录成功',
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
            wx.showModal({
              title: 'Error',
              content: '账号或者密码输入错误'
            })
          }
        }
      })
    }
  },
  //注册处理
  resgin: function(){
    var that = this;
    if (this.data.username.length == 0 || this.data.password.length == 0) {
      wx.showToast({
        title: '账号或密码不能为空',
        icon: 'none',
        duration: 2000
      })
    } else {
      wx.request({
        url: 'http://localhost:8888/test/resign/check/'+that.data.username,
        method: 'post',
        data: {
          username: that.data.username,
          password: that.data.password
        },
        header: {
        'content-type': 'application/x-www-form-urlencoded' // 默认值
        },
        success(res){
          var flag = res.data;
          //用户已存在
          if(flag == true){
            wx.showModal({
              title: 'Error',
              content: '用户已存在'
            });
          }else{
            wx.request({
              url: 'http://localhost:8888/test/resign/'+that.data.username+'/'+that.data.password,
              data: {
                username: that.data.username,
                password: that.data.password
              },
              header: {
              'content-type': 'application/x-www-form-urlencoded'
              },
              success(tes){
                wx.clearStorage()
                wx.setStorageSync('userid', that.data.username)
                wx.showModal({
                  title: '注册成功',
                  content: '即将返回登录界面',
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
    }
  },
})
 