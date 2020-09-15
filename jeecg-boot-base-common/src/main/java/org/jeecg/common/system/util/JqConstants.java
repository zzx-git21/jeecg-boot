// ******************************************************************************
// Copyright (C) 2017, All Rights Reserved.
// ******************************************************************************
package org.jeecg.common.system.util;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;

/**
 * @description 常量类
 *
 * @author wenye
 *
 * @date 2017年11月15日
 */
public class JqConstants {
    
    // general response 返回错误
    public static final Byte GENERAL_RESPONSE_STATE_FALSE = 0;
    
    // general response 返回正确
    public static final Byte GENERAL_RESPONSE_STATE_TRUE = 1;
    
    // general response 需要登陆
    public static final Byte GENERAL_RESPONSE_STATE_NEED_LOGIN = 2;
    
    // 删除状态：未删除
    public static final Byte DELETE_STATUS_NO = 0;
    
    // 删除状态：已删除
    public static final Byte DELETE_STATUS_YES = 1;
    
    // 删除状态：未删除
    public static final Boolean DELETE_STATUS_BOOLEAN_NO = false;
    
    // 删除状态：已删除
    public static final Boolean DELETE_STATUS_BOOLEAN_YES = true;
    
    // 数据库：表示否
    public static final Byte DB_YESORNO_NO = 0;
    
    // 数据库：表示是
    public static final Byte DB_YESORNO_YES = 1;
    
    // 商品：卖家承担运费
    public static final Integer GOODS_TRANSFEE_SELLER = 1;
    
    // 商品：买家承担运费
    public static final Integer GOODS_TRANSFEE_BUYER = 0;
    
    // 运费模板-计价方式：按件数
    public static final Integer TRANSPORT_TRANS_TYPE_NUMBER = 0;
    
    // 运费模板-计价方式：按重量
    public static final Integer TRANSPORT_TRANS_TYPE_WEIGHT = 1;
    
    // 运费模板-计价方式：按体积
    public static final Integer TRANSPORT_TRANS_TYPE_VOLUME = 2;
    
    // 运费模板 全国的cityId
    public static final Byte TRANSPORT_TEMPLATE_DEFAULT_CITY = -1;
    
    // 余额变动明细表操作类型：增加
    public static final String SHOPPING_PREDEPOSIT_LOG_STR_OPTYPE_ADD = "增加";
    
    // 余额变动明细表操作类型：减少
    public static final String SHOPPING_PREDEPOSIT_LOG_STR_OPTYPE_MINUS = "减少";
    
    // 余额变动明细表资金变动类型：可用预存款
    public static final String SHOPPING_PREDEPOSIT_LOG_STR_PDTYPE_AVAILABLE = "可用预存款";
    
    // 余额变动明细表资金变动类型：冻结余额
    public static final String SHOPPING_PREDEPOSIT_LOG_STR_PDTYPE_FREEZE = "冻结余额";
    
    // 利润分成：个人 30%(个人消费指数PV30%)
    public static final double PROFIT_SETTLE_SELF = 0.3;
    
    public static final String PROFIT_SETTLE_SELF_STR = "0.3";
    
    // 利润分成：上级用户10%(直接分享链接消费指数PV10)
    public static final double PROFIT_SETTLE_PARENT = 0.1;
    
    // 利润分成：祖父用户6%(间接分享链接消费指数PV6%)
    public static final double PROFIT_SETTLE_GRANDPARENT = 0.06;
    
    // 利润分成：所属店主4%
    public static final double PROFIT_SETTLE_SHOP_CONSUME = 0.04;
    
    // 利润分成：销售店主4%
    public static final double PROFIT_SETTLE_SHOP_SELL = 0.04;

    // 利润分成：所属馆主 3%
    public static final double PROFIT_SETTLE_MUSEUM_CONSUME = 0.03;
    
    // 利润分成：销售馆主 3%
    public static final double PROFIT_SETTLE_MUSEUM_SELL = 0.03;
    
    // 利润分成：生活馆上级邀请者 2%(间接分享链接消费指数PV6%)
    public static final double PROFIT_SETTLE_MUSEUM_PARENT = 0.02;
    
    // 利润分成：大区经理 4%
    public static final double PROFIT_SETTLE_ZONE_MANAGER = 0.04;
    
    // 可用余额增加
    public static final byte BALANCE_CHANGE_TYPE_AVAI_ADD = 1;
    
    // 可用余额减少
    public static final byte BALANCE_CHANGE_TYPE_AVAI_MINUS = 2;
    
    // 冻结金额增加
    public static final byte BALANCE_CHANGE_TYPE_FREEZE_ADD = 3;
    
    // 冻结金额减少
    public static final byte BALANCE_CHANGE_TYPE_FREEZE_MINUS = 4;
    
    // 商户类型：周边服务
    public static final Byte SHOP_TYPE_SHANGJIA = 1;
    
    // 商户类型：联盟电商
    public static final Byte SHOP_TYPE_STORE = 2;
    
    // 线下扫码支付
    public static final Byte SHOP_TYPE_PAY = 3;
    
    // 利润分成的url
    public static final String STR_ACTION_PROFIT_SETTLE = "/profitsettle";
    
    // md5的额外字符串
    public static final String MD5_EXTRA = "'TG-]Y=FD?p3Dtxh&'";
    
    //业绩统计 
    public static final String SHOPPING_PERFORMANCE_STATISTICS = "achievement" ;
    
    //我的钱包
    public static final String SHOPPING_WALLET ="wallet";
    
    //交易记录
    public static final String SHOPPING_TRANSACTION ="transaction";
    
    //资金记录
    public static final String SHOPPING_GOLD ="gold";
    
    //收益中心
    public static final String SHOPPING_PROFIT ="profit";
    
    //店铺列表
    public static final String STOREPAGE = "storepage";
    
    //注册金币
    public static final BigDecimal REG_GOLD_INTI = BigDecimal.ZERO;
    
    //注册积分
    public static final Integer REG_COIN_INIT = 0;
    
    // 注册：登陆次数
    public static final Integer REG_LOGIN_COUNT = 0;
    
    // 性别：未知
    public static final Integer SEX_UNKNOWN = 0;
    
    // 性别：男
    public static final Integer SEX_MALE = 1;
    
    // 性别：女
    public static final Integer SEX_FEMALE = 2;
    
    // 商品分类 显示
    public static final Byte DISPLAY = 1 ;
    // 商品分类 不显示
    public static final Byte DISPLAY_NOT = 0 ;
    // 用户状态：初始化
    public static final Integer USER_STATUS_INIT = 0;
    
    // 用户信誉：初始状态
    public static final Integer USER_CREDIT_INIT = 0;
    
    // 举报?：初始状态
    public static final Integer USER_REPORT_INIT = 0;
    
    // userRank：会员
    public static final String USER_RANK_STR_MEMBER = "1";
    
    // userRank：馆主
    public static final String USER_RANK_STR_MUSEUM = "2";
    
    // userRank：联盟电商
    public static final String USER_RANK_STR_STORE = "3";
    
    // userRank：周边服务
    public static final String USER_RANK_STR_OFFSTORE = "4";
    
    //商品推荐 数量
    public static final Integer RECOMMEND_GOODS_COUNT = 4;
    
    // token 签发
    public static final String JWT_TOKEN_ISSUER = "immortal international holdings";
    
    // 一个月的秒数
    public static final int ONE_MONTH_MILLISECONDS = 30 * 24 * 60 * 60;
    
    // 半小时秒数
    public static final int HALF_ONE_HOUR = 30 * 60;
    
    // 5分钟
    public static final int FIVE_MINUTES = 5 * 60;
    
    // 10分钟
    public static final int TEN_MINUTES = 10 * 60;
    
    // token的秘钥
    public static final String JWT_TOKEN_KEY = "jeeqin@2017";
    
    // token的subject
    public static final String JWT_TOKEN_SUBJECT = "jeeqin@server";
    
    //120秒
    public static final int SECOND = 120;
    
    // 热销市场只有5个
    public static final int HOT_MARKET_SIZE = 5;
    
    // 商品状态：有效
    public static final Byte GOODS_STATUS_VALID = 0;
    
    // 商品状态：无效
    public static final Byte GOODS_STATUS_INVALID = 1;
    //商品状态：下架
    public static final Byte GOODS_STATUS_Off_shelf = -1;
    
    //商品状态：违规下架
    public static final Byte GOODS_STATUS_ILLEGAL = -2;
    
    //商品审核状态：审核不通过
    public static final Byte GOODS_AUDIT_NOT_PASS = 2;
    
    // 商品审核状态：审核通过
    public static final Byte GOODS_AUDIT_YES = 1;
    
    // 商品审核状态：未审核
    public static final Byte GOODS_AUDIT_NO = 0;
    
    // 店铺申请不存在
    public static final Byte STORE_NOT_EXISTS = -1;
    
    //申请次数 超限
    public static final Byte STORE_APPLY_OUTLIMIT = -2;
    
    //店铺申请入驻 次数限制
    public static final byte STORE_APPLY_COUNT = 100;
    
    //店铺状态：待审核
    public static final Byte STORE_STATUS_PENDING = 1;
    
    //店铺状态：正常
    public static final Byte STORE_STATUS_NORMAL = 2;
    
    //店铺状态：违规关闭
    public static final Byte STORE_STATUS_ILLEGAL=3;
    
    //店铺分类等级 --顶级
    public static final Long STORE_LEVEL_PARENT = 0L;
    
    //店铺分类等级 --子级
    public static final Long STORE_LEVEL_CHILD = 1L;
    
    // 状态：yes
    public static final Byte STATUS_YES = 0;
    
    // 状态：no
    public static final Byte STATUS_NO = 1;
    
    // 代金券状态：未过期
    public static final Byte COUPON_STATUS_NOT_EXPIRED = 0;
    
    // 代金券状态：已过期
    public static final Byte COUPON_STATUS_EXPIRED = 1;
    
    
    // ----------------------------------------------------------- 订单状态开始
    // 订单状态：已取消
    public static final Integer ORDER_STATUS_CANCELED = 0;
    
    // 订单状态：未支付
    public static final Integer ORDER_STATUS_UNPAIED = 10;
    
    // 订单状态：线下支付待审核
    public static final Integer ORDER_STATUS_OFFLINE_UNVERIFIED = 15;
    
    // 订单状态：货到付款待发货
    public static final Integer ORDER_STATUS_PAY_ONRECEIVED_UNSHIPPED = 16;
    
    // 订单状态：未发货
    public static final Integer ORDER_STATUS_UNSHIP = 20;
    
    // 申请退款
    public static final Integer ORDER_STATUS_REFUND_COMMIT = 21;
    
    // 已退款  -- > 退款中
    public static final Integer ORDER_STATUS_REFUND_AGREE = 22;
    
    // 拒绝退款
    public static final Integer ORDER_STATUS_REFUND_REJECT = 23;
    
    // 退款成功
    public static final Integer ORDER_STATUS_REFUND_AGREED = 24;
    
    
    // 订单状态：已发货
    public static final Integer ORDER_STATUS_SHIPPED = 30;
    
    // 订单状态：已收货
    public static final Integer ORDER_STATUS_RECEIVED = 40;
    
    // 订单状态：申请退货中
    public static final Integer ORDER_STATUS_RETURN_APPLY = 45;
    
    // 订单状态：退货中
    public static final Integer ORDER_STATUS_IN_RETURN = 46;
    
    // 订单状态：退货完成,已结束
    public static final Integer ORDER_STATUS_RETURN_COMPLETE = 47;
    
    // 订单状态：卖家拒绝退货申请
    public static final Integer ORDER_STATUS_RETURN_REFUSED = 48;
    
    // 订单状态：退货失败
    public static final Integer ORDER_STATUS_RETURN_FAILED = 49;
    
    // 订单状态：已完成,已评价
    public static final Integer ORDER_STATUS_COMPLETE = 50;
    
    // 订单状态：已结束
    public static final Integer ORDER_STATUS_OVER = 60;
    
    // 订单状态：已结束,不可评价
    public static final Integer ORDER_STATUS_OVER_CANTEVAL = 65;
    
    // 订单状态：退款退货完成
    public static final Integer ORDER_STATUS_REFUND_OVER = 70;
    //
 // ----------------------------------------------------------- 线下订单状态开始
    //已取消
    public static final Byte OFF_ORDER_STATUS_CANCELED = 0;
  	
  	//待付款
  	public static final Byte OFF_ORDER_STATUS_OFFLINE_TOBEPAIED = 1;
  	
  	//待使用
  	public static final Byte OFF_ORDER_STATUS_TOBEUSED = 2;
  	
  	//退款/售后
  	public static final Byte OFF_ORDER_STATUS_REFUND_AND_AFTERSALE = 3;
  	
  	//退款已拒绝
  	public static final Byte OFF_ORDER_STATUS_REFUND_REFUSED = 4;
  	
  	//退款成功
  	public static final Byte OFF_ORDER_STATUS_REFUND_SUCCESS = 5;
  	
  	//已完成
  	public static final Byte OFF_ORDER_STATUS_SUCCEED = 6;
  	
  	//待评价
  	public static final Byte OFF_ORDER_STATUS_TOBECOMMENT = 7;
  	
  	//退款已取消
  	public static final Byte OFF_ORDER_STATUS_REFUND_CANCELED = 8;
  	
  	//退款中
  	public static final Byte OFF_ORDER_STATUS_REFUNDING = 9;
    

    
    /**
     * 订单状态输入
     */
    // 订单查询：全部
    public static final Byte ORDER_STATUS_INPUT_ALL = 0;
    
    // 订单查询：待付款
    public static final Byte ORDER_STATUS_INPUT_UNPAIED = 1;
    
    // 订单查询：待收货
    public static final Byte ORDER_STATUS_INPUT_UNRECEIVED = 2;
    
    // 订单查询：待评价
    public static final Byte ORDER_STATUS_INPUT_UNEVALED = 3;
    
    // 订单查询：已完成
    public static final Byte ORDER_STATUS_INPUT_COMPLETE = 4;
    
    // ----------------------------------------------------------- 订单状态结束
    
    // 订单发票类型：个人
    public static final Byte ORDER_INVOICE_TYPE_PERSONAL = 0;
    
    // 订单发票类型：公司
    public static final Byte ORDER_INVOICE_TYPE_COMPANY = 1;
    
    // 不需要发票
    public static final Byte ORDER_INVOICE_TYPE_NO = 2;
    
    // 地区级别：全球
    public static final Integer AREA_LEVEL_GLOBAL = 0;
    
    // 地区级别：国家
    public static final Integer AREA_LEVEL_NATION = 1;
    
    // 地区级别：省
    public static final Integer AREA_LEVEL_PROVINCE = 2;
    
    // 地区级别：市
    public static final Integer AREA_LEVEL_CITY = 3;
    
    // 地区级别：县、区
    public static final Integer AREA_LEVEL_DISTRICT = 4;
    
    // 订单商品类型：web
    public static final String ORDER_TYPE_WEB = "web";
    
    // 订单商品类型：mobile
    public static final String ORDER_TYPE_MOBILE = "mobile";
    
    // 订单商品类型：weixin
    public static final String ORDER_TYPE_WEIXIN = "weixin";
    
    // 订单商品类型：线下商品
    public static final String ORDER_TYPE_OFFLINE_PRODUCT = "offline_product";
    
    //订单类型:线下支付订单
    public static final String ORDER_TYPE_OFFLINE = "offline";
    
    
    // id：0
    public static final Long ID_ZERO = 0L;
    
    // id:-1
    public static final Long ID_IMPOSSIBLE = -1L;
    
    // 店铺分类：线上
    public static final Byte STORE_CLASS_TYPE_ONLINE = 1;
    
    // 店铺分类：线下
    public static final Byte STORE_CLASS_TYPE_OFFLINE = 0;
    
    // 假的图片url
    public static final String DUMMY_IMG_URL = "https://gw.alicdn.com/bao/uploaded/TB1ftH4annI8KJjy0FfSuwdoVXa.jpg_q90";
    
    // 商家排序：订单量
    public static final Byte SHANGJIA_ORDERBY_ORDERCNT = 1;
    
    // 商家排序：adddate
    public static final Byte SHANGJIA_ORDERBY_ADDDATE = 2;

    //用户申请类型：1:账户升级 
    public static final int  USER_APPLY_TYPE_UPGRADE=1;
    //用户申请类型：2:手机号变更
    public static final int  USER_APPLY_TYPE_NEW_PHONE=2;
    //用户申请类型：3:银行卡变更 
    public static final int  USER_APPLY_TYPE_NEW_CARD=3;
    //用户申请类型：4:转账
    public static final int  USER_APPLY_TYPE_TRANSFER=4;
    //用户申请类型：5:提现到银行卡 
    public static final int  USER_APPLY_TYPE_WITHDRAWALS_CARD=5;
    //用户申请类型：6:更改店铺名
    public static final int  USER_APPLY_TYPE_NEW_STORENAME=6;
    //用户申请类型：7:其他
    public static final int  USER_APPLY_TYPE_OTHER=7;
    //gda转余额  8 
    public static final int  USER_APPLY_GDA_TO_AVALIBANCE=8;
    
    //用户申请状态：外部状态 0 未审核 
    public static final Integer  USER_APPLY_AUDIT_WAIT=0;
    //用户申请状态：1 已审核
    public static final Integer  USER_APPLY_AUDIT_PASS=1;
    
    //用户支付状态:0 待支付
    public static final Integer  USER_PAY_AUDIT_WAIT=0;
    //用户支付状态:2 已支付
    public static final Integer  USER_PAY_AUDIT_PASS=2;
    
    //用户申请：内部状态 0 审核不通过
    public static final Integer  USER_APPLY_AUDIT_WAIT_I=0;
    //用户申请状态：1 审核通过
    public static final Integer  USER_APPLY_AUDIT_PASS_I=1;
    //用户申请状态：2 审核不通过
    public static final Integer  USER_APPLY_AUDIT_NOTPASS_I=-1;
    
    // 图片上传 ： temp
    public static final Byte UPLOAD_IMG_TEMP = 0 ;
    // 图片上传路径 ：temp
    public static final String IMG_TEMP_PREFIX = "temp/" ;
    
    // 手机端图片上传：头像
    public static final Byte UPLOAD_IMG_AVATAR = 1;
    
    // 图片路径：头像
    public static final String IMG_AVATAR_PREFIX = "avatar/";
    
    // 手机端图片上传：线上评论
    public static final Byte UPLOAD_IMG_EVAL_ONLINE = 2;
    
    // 图片路径：线上评论
    public static final String IMG_EVAL_ONLINE_PREFIX = "on/eval/";
    
    // 手机端图片上传：线下评论
    public static final Byte UPLOAD_IMG_EVAL_OFFLINE = 3;
    
    // 图片路径：线下评论
    public static final String IMG_EVAL_OFFLINE_PREFIX = "off/eval/";
    
    // 手机端图片上传：线上店铺
    public static final Byte UPLOAD_IMG_ONLINE_STORE = 4;
    
    // 图片路径：线上店铺
    public static final String IMG_ONLINE_STORE_PREFIX = "on/store/";
    
    // 手机端图片上传：线下店铺
    public static final Byte UPLOAD_OFFLINE_STORE_OFFLINE = 5;
    
    // 图片路径：线下店铺
    public static final String IMG_OFFLINE_STORE_PREFIX = "off/store/";
    
    // 手机端图片上传：身份证
    public static final Byte UPLOAD_ID_CARD = 6;
    
    // 图片路径：身份证
    public static final String IMG_IDCARD_PREFIX = "idcard/";
    
    // 图片上传 ：线下商品
    public static final Byte UPLOAD_OFF_GOODS = 7 ;
    
    // 图片路径 ：线下商品
    public static final String  IMG_OFF_GOODS_PREFIX="off/package/";
    
    //图片上传 ：线下套餐
    
    public static final Byte UPLOAD_OFF_PACKAGE = 8 ;
    
    // 图片路径 ：线下套餐
    public static final String  IMG_OFF_PACKAGE_PREFIX="off/package/";
    
    // 图片上传 ：线上商品 
    public static final Byte UPLOAD_ON_GOODS = 9 ;
    
    // 图片路径 ：线上商品
    public static final String  IMG_ON_GOODS_PREFIX = "on/package/";
    
    // 图片上传 ：线上分类
    public static final Byte UPLOAD_ON_CLS = 10;
    
    // 图片路径 ：线上分类
    public static final String  IMG_ON_CLS_PREFIX = "on/cls/";
    
    // 图片上传 ：商家图片
    public static final Byte UPLOAD_ON_STORE = 11;
    
    // 图片路径 ：商家图片
    public static final String  IMG_ON_STORE_PREFIX = "on/store/";
    
    // POINT
    public static final char POINT = '.';
    
    // 代金券状态：未使用
    public static final Byte COUPON_INPUT_STATUS_UNUSE = 1;
    
    // 代金券状态：已使用
    public static final Byte COUPON_INPUT_STATUS_USED = 2;
    
    // 代金券状态：已过期
    public static final Byte COUPON_INPUT_STATUS_EXPIRED = 3;
    

    // 一天的秒数
    public static final int ONE_DAY_MILLISECONDS = 24 * 60 * 60;
    
 // 三天的秒数
    public static final int THREE_DAY_MILLISECONDS = 3 * 24 * 60 * 60;
     
    // 一天的毫秒数
    public static final long ONE_DAY_BILLISECONDS = 24 * 60 * 60 * 1000;

    // 一小时的毫秒数
    public static final long ONE_HOUR_BILLISECONDS = 60 * 60 * 1000;
    // 登陆：成功
    public static final Byte LOGIN_RETURN_SUCCESS = 1;

    // 登陆：用户名/密码错误
    public static final Byte LOGIN_RETURN_FAILED_NAMEPWD = 2;
     
    // 登陆：用户名/密码错误 快达到数量
    public static final Byte LOGIN_RETURN_FAILED_NAMEPWD_EXCESSIVE = 3;

    // 登陆：账号被锁定
    public static final Byte LOGIN_RETURN_FAILED_LOCKED = 4;

    // 登陆：系统异常
    public static final Byte LOGIN_RETURN_FAILED_EXCEPTION = 5;
    
    // 登陆：店铺状态异常
    public static final Byte LOGIN_RETURN_STATUS_EXCEPTION = 6;
    
    // 三方登陆：成功
    public static final Byte OTHER_LOGIN_RETURN_OK = 1;
    
    // 三方登陆：token获取异常
    public static final Byte OTHER_LOGIN_RETURN_TOKEN_ERR = 2;
    
    // 三方登陆：系统异常
    public static final Byte OTHER_LOGIN_RETURN_SYSTEM_ERR = 3;
     
    // 用户角色：BUYER  买家
    public static final String USER_ROLE_BUYER = "BUYER";
     
    // 用户角色：BUYER_SELLER  线上卖家
    public static final String USER_ROLE_BUYER_SELLER = "BUYER_SELLER";
    
    // 用户角色：ONLY_SELLER 仅限用线上卖家子账号
    public static final String USER_ROLE_ONLY_SELLER = "ONLY_SELLER";
    
    // 用户角色：USER_ROLE_BUYER_OFF_SELLER  线下卖家
    public static final String USER_ROLE_BUYER_OFF_SELLER = "BUYER_OFF_SELLER";
     
    // 用户角色：ADMIN_BUYER_SELLER  双角色卖家  既有线上又有线下
    public static final String USER_ROLE_ADMIN_BUYER_SELLER = "ADMIN_BUYER_SELLER";
     
    // 密码尝试次数
    public static final Byte PWD_RETRY_LIMIT = 5;

    // 验证码：手机
    public static final Byte PIN_WAY_MOBILE = 1;
    
    // 验证码：email
    public static final Byte PIN_WAY_EMAIL = 2;
    
    // 用户名注册
    public static final Byte WAY_USER_NAME = 3;
    
    // 手机号码正则
    public static final String REG_MOBILE = "^(1)\\d{10}$";
    
    // 邮箱正则
    public static final String REG_EMAIL = "^([a-z0-9A-Z]+[_-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+([_-a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    
    // 短信平台用户名
    public static final String SMS_NAME = "skycc";
    
    // 短信平台密码
    public static final String SMS_PWD = "skycc510520";
    
    // 分号
    public static final String COLON = ":";
    
    // 下划线
    public static final String UNDERLINE = "_";
    
    // 验证码长度
    public static final int PIN_CODE_LENGTH = 4;
    
    // 用户关系：fx级别 1
    public static final Integer USER_RELATIONS_FXLEVEL_ONE = 1;
    
    // 用户关系：fx级别 2
    public static final Integer USER_RELATIONS_FXLEVEL_TWO = 2;
    
    // 用户关系：fx级别 3
    public static final Integer USER_RELATIONS_FXLEVEL_THREE = 3;
    
    // 用户注册 验证不正确
    public static final Integer REG_RET_ERR_CODE = 1;
    
    // 用户注册 正确返回
    public static final Byte REG_RET_OK = 2;
    
    // 用户注册 邀请码不正确
    public static final Byte REG_RET_INVITE_ERR = 3;
    
    // 重置密码 用户不不存在
    public static final Byte RESETPW_RET_USER_NOT_EXISTS = 1;
    
    // 重置密码：验证码不正确
    public static final Byte RESETPW_RET_ERR_CODE = 2;
    
    // 重置密码：正确
    public static final Byte RESETPW_RET_OK = 3;
    
    // 小程序
    public static final Byte OTHER_LOGIN_APPLET = 1;
    
    // 微信(兼容老版本代码业务逻辑)
    public static final Byte OTHER_LOGIN_WX= 2 ;
    
    // QQ
    public static final Byte OTHER_LOGIN_QQ= 3 ;
    // 微信(新版本第三方登录标识)
    public static final Byte OTHER_LOGIN_NWX= 4 ;
    
    // general response obj 返回错误
    public static final Byte GENERAL_RESPONSE_OBJ_FALSE = 0;
    
    // general response obj 返回正确
    public static final Byte GENERAL_RESPONSE_OBJ_TRUE = 1;
    
    // sms默认模板
    public static final String SMS_TEMPLATE_CODE_DEFAULT = "短信验证码模板";
    
    // 商城支付：密码不正确
    public static final Byte SHOP_PAY_PASSWORD_ERR = 1;
    
    // 商城支付: 密码错误次数超过5次
    public static final Byte SHOP_PAY_PASSWORD_EXCESSIVE_ATTEMPTS = 6;
    
    // 商城支付：金额不组
    public static final Byte SHOP_PAY_AMOUNT_LACK = 7;
    
    // 店铺支付：成功
    public static final Byte SHOP_PAY_OK = 8;
    
    // 实名认证：未提交
    public static final Byte REAL_NAME_CERT_UNCOMMIT = -1;
    
    // 实名认证：未审核
    public static final Byte REAL_NAME_CERT_STATUS_COMMIT = 0;
    
    // 实名认证：审核通过
    public static final Byte REAL_NAME_CERT_STATUS_SUCCESS = 1;
    
    // 实名认证：审核不通过
    public static final Byte REAL_NAME_CERT_STATUS_FAIL = 2;
    
    // 实名认证: 验证码不正确
    public static final Byte REAL_NAME_CERT_CODE_FAIL = 3;
    
    // 版本最新
    public static final Byte APP_VERSION_UPGRADE_TYPE_NEWLY = 0;
    
    // 普通升级
    public static final Byte APP_VERSION_UPGRADE_TYPE_NORMAL = 1;
    
    // 灰度升级
    public static final Byte APP_VERSION_UPGRADE_TYPE_SCALE = 2;
    
    // 强制升级
    public static final Byte APP_VERSION_UPGRADE_TYPE_FORCE = 3;
    
    // 不关心
    public static final Byte NOT_CARE = -1;
    
    // 验证码不正确
    public static final Byte APPLET_MOBILE_BIND_OBJ_ERR_CODE = 1;
    
    // 修改字段
    public static final Byte APPLET_MOBILE_BIND_OBJ_UPDATE = 2;
    
    // 合并用户
    public static final Byte APPLET_MOBILE_BIND_OBJ_MERGE = 3;
    
    //是否线下推荐商家:否
    public static final Byte OFFLINE_ISRECOMMEND_NO = 0;
    
    //是否线下推荐商家：是
    public static final Byte OFFLINE_ISRECOMMENG_YES = 1;
    
    //线下商家审核是否通过：未审核
    public static final Byte OFFLINE_STATUS_NOCHECKED = 1;
    
    //线下商家审核是否通过：审核通过
    public static final Byte OFFLINE_STATUS_YES = 2;
    
    //线下商家审核是否通过：审核不通过
    public static final Byte OFFLINE_STATUS_NO = 3;
    // 提现申请
    public static final Byte MOBILE_PIN_SEND_TYPE_ENCASH = 1;
    
    
    
    // 通用参数  String 类型 1
    public static final String STRING_ONE = "1" ;
     
    // 通用参数  String 类型 2
    public static final String STRING_TWO = "2" ;
    
    // 通用参数  String 类型 3
    public static final String STRING_THREE = "3" ;
    
    //线下专题推荐是否推荐：推荐
    public static final Byte OFFLINE_WARMRECOMMEND_YES = 1; 
    
    //线下专题推荐是否推荐：不推荐
    public static final Byte OFFLINE_WARMRECOMMEND_NO = 0;
    
    //线下专题推荐专题类型：店铺分类
    public static final Byte OFFLINE_SUBJECTRECOMMEND_STORE = 1;
    //线下专题推荐专题类型：商品分类
    public static final Byte  OFFLINE_SUBJECTRECOMMEND_PACKAGE = 2;  
    
    
    // ------------------------------------------------------------支付方式
    //无支付方式
    public static final byte PAY_TYPE_NO = 0;
    //支付宝
    public static final byte PAY_TYPE_ALIPAY = 1;
    //微信
    public static final byte PAY_TYPE_WEIXI = 2;
    //gda
    public static final byte PAY_TYPE_GDA = 3;
    //余额
    public static final byte PAY_TYPE_BALANCE = 4;
    //公众号
    public static final byte PAY_TYPE_PUBLIC = 5 ;
    //微信h5
    public static final byte PAY_TYPE_WX_H5 = 6 ;
    
    //paypal
    public static final byte PAY_TYPE_PAYPAL = 7 ;
 // 线上订单类型
    public static final String  ORDER_ONLINE_TYPE = "1" ;
    //线下订单类型
    public static final String  ORDER_OFFLINE_TYPE = "2" ;
  //到店付 订单
    public static final String  ORDER_OFFLINE_SHANGJIA_TYPE = "3" ;
    
    //支付宝支付 线上订单标题
    public static final String  ONLINE_ORDER_SUBJECT = "onlineorder" ;
    //支付宝支付 线下订
    public static final String  OFFLINE_ORDER_SUBJECT ="offlineorder" ;
    //支付宝 
    public static final String  OFFLINE_SHANGJIA_ORDER_SUBJECT ="offlineshangjiaorder";
    
    
    public static final String  ONLINE_ORDER = "0" ;
   
    public static final String  OFFLINE_ORDER ="1" ;
    
    
    public static final String  OFFLINE_SHANGJIA_ORDER ="2" ;
    
    
    // ----------- 线下订单状态 start------------//
    	//已取消
    public static final byte  OFFLINE_ORDER_STATUS_CANCELED = 0 ;
    	//待支付
    public static final byte  OFFLINE_ORDER_STATUS_TOBEPAID = 1 ;
    	//已支付
    public static final byte  OFFLINE_ORDER_STATUS_ALREAY_PAID = 2 ;
    	//申请退款
    public static final byte  OFFLINE_ORDER_STATUS_ALREADY_USED = 3 ;
    	//拒绝退款
    public static final byte  OFFLINE_ORDER_STATUS_RETURN = 4 ;
    	//已退款
    public static final byte  OFFLINE_ORDER_STATUS_RETURN_REFUSED = 5 ;
    	//已消费
    public static final byte  OFFLINE_ORDER_STATUS_RETURNED = 6 ;
    // ----------- 线下订单状态  end------------//
    
    
    // -----------订单支付方式 start---------------//
    	//支付宝
    public static final byte  ALIPAY = 1 ;
    	//微信
    public static final byte  WXPAY = 2 ;
    	//gda
    public static final byte  GADPAY = 3 ;
    	//余额
    public static final byte  AVAILABALANCEPAY = 4;	
    // -----------订单支付方式 end---------------//
    
    // -----------线下店铺审核 start---------------//
    	//不提交审核
    public static final byte NOT_SH = 0 ;
    	//未审核
    public static final byte UNAUDITED = 1 ;
    	//审核通过
    public static final byte PASS = 2 ;
    	//审核不通过
    public static final byte PASS_REFUSE = 3 ;
    // -----------线下店铺审核 start---------------//
    
    // -----------线下订单评论状态 start---------------//
    
    public static final byte  OFFLINE_ORDER_COMMENT_STATUS_TRUE = 1 ;
    
    public static final byte  OFFLINE_ORDER_COMMENT_STATUS_FALSE = 0 ;
    
    // -----------线下订单评论状态 end---------------//
    
    // -----------线上商品评论状态 start---------------//
         //以评价
    public static final byte  ONLINE_GOODS_COMMENT_STATUS_TRUE = 1 ;
         //未评价
    public static final byte  ONLINE_GOODS_COMMENT_STATUS_FALSE = 0 ;
    
    // -----------线上商品评论状态 end---------------//
    
    // -----------线上订单申请状态---------------//
		//申请状态
	public static final Byte ONLINE_ORDER_ALREADY_USED = 1;
		//拒绝退款
	public static final Byte ONLINE_ORDER_RETURN_REFUSED = 2;
		//已退款
	public static final Byte ONLINE_ORDER_RETURN_COMPLETE = 3;
	
	// -----------线上订单申请状态---------------//
    
    // -----------线上商品评论商家回复状态 start---------------//
        //以回复
    public static final Byte  ONLINE_GOODS_REPLY_STATUS_TRUE = 1 ;
        //未回复
    public static final Byte  ONLINE_GOODS_REPLY_STATUS_FALSE = 0 ;

    // -----------线上商品评论商家回复状态 end---------------//
    
    // 未开始
    public static final Byte STATUS_UNSTART = 0;
    
    // 已开始
    public static final Byte STATUS_STARTED = 1;
    
    // 已结束
    public static final Byte STATUS_ENDED = 2;
    
    // -----------线下订单消费码长度---------------//
    public static final int ConsumeCode_Length = 8;
    
 // -----------线下订单5星好评---------------//
    public static final BigDecimal COMMENT_FULL = BigDecimal.valueOf(5);
    

    
    // 登陆类型：买家
    public static final Byte LOGIN_TYPE_BUYER = 1;
    
    // 登陆类型：线上卖家
    public static final Byte LOGIN_TYPE_ON_SELLER = 2;
    
    // 登陆类型：线下卖家
    public static final Byte LOGIN_TYPE_OFF_SELLER = 3;
    
    // 登陆类型：运营后台用户
    public static final Byte LOGIN_TYPE_ADMIN = 4;
    
    // 字符长度(255)
    public static final int CHARACTER_LENGTH = 255;
    
    // im自定义加密规则
    public static final String  PASSWORDHEAD= "f3d422bc19a21e4789b4b44eff2c233d" ;
    
    // --------------财务相关---------------//
        // 资金变动: 无变动
    public static final Byte FINANCE_NOCHANGE = 0;
        // 资金变动: 增加
    public static final Byte FINANCE_INCREASE = 1;
        // 资金变动: 减少   
    public static final Byte FINANCE_DECREASE = 2;
    
        // 财务明细类型 : 全部
    public static final Byte FINANCE_ALL = 0;
        // 财务明细类型 : 营业额
    public static final Byte FINANCE_TURNOVER = 1;
        // 财务明细类型 : 余额
    public static final Byte FINANCE_BALANCE = 2;
        // 财务明细类型 : G
    public static final Byte FINANCE_VIRTUAL = 3;
        // 财务明细类型 : 退款
    public static final Byte FINANCE_REFUND = 4;
        // 财务明细类型 : 冻结金额
    public static final Byte FINANCE_FREEZE = 5;

    //商品上架状态:已经上架
    public static final Byte GOODS_STATUS_YES = 0;
    
    //商品上架状态:仓库中
    public static final Byte GOODS_STATUS_NO = 1;
    
    
    
    
    // --------------实名审核状态 start--------------//
    //审核通过
    public static final Byte AUDIT_YES = 1 ;
    //审核拒绝
    public static final Byte AUDIT_NO = 2 ;
    //等待审核
    public static final Byte AUDIT_WAIT  = 0 ;
    // --------------实名审核状态 end --------------//
    
    
    
    // --------------提现审核状态 start--------------//
    //审核通过
    public static final Byte CASH_YES = 1 ;
    //审核拒绝
    public static final Byte CASH_NO = -1 ;
    //等待审核
    public static final Byte CASH_WAIT  = 0 ;
    
    //已支付
    public static final Byte CASH_PAY_YES = 2 ;
    //待支付
    public static final Byte CASH_PAY_NO = 0 ;
   
    // --------------实名审核状态 end --------------//
    
    
    
    
    // --------------用户余额明细类型 start--------------//
    //提现申请同意
    public static final Byte BALANCE_WITHDRAW_YES = 9 ;
    //提现申请拒绝
    public static final Byte BALANCE_WITHDRAW_NO  = 10 ;
    // --------------实名审核状态 end --------------//
    
    // --------------分类级别 start--------------//
    //一级
    public static final Integer LEVEL_ONE = 0 ;
    //二级
    public static final Integer LEVEL_TWO = 1 ;
    //三级
    public static final Integer LEVEL_THREE  = 2 ;
    // --------------分类级别 end --------------//
    
    // --------------广告管理start--------------//
    //广告分类管理是否启用 1-是；0-否
    public static final Integer AP_STATUS_YES = 1;
    public static final Integer AP_STATUS_NO = 0;
    //点击数、浏览次数
    public static final Integer AD_CLICK_NUM = 0;
    //资金数
    public static final Integer AD_GOLD_NUM = 0;
    //广告排序
    public static final Integer AD_SLIDE_SEQUENCE = 0;
    //审核通过
    public static final Integer AD_STATUS_YES = 1;
    //审核不通过
    public static final Integer AD_STATUS_NO = 2;
    public static final Integer AP_SALE_TYPE = 0;
    public static final Integer AP_USE_STATUS = 0;
    
    
    //活动未开始
    public static final Byte AP_NOSTART  = 0 ;
    //活动开始
    public static final Byte AP_START = 1 ;
    //活动结束
    public static final Byte AP_END = 2 ;
    //活动报名时间未截止
    public static final Byte AP_APPLY_START  = 0 ;
    
    //活动报名时间已截止
    public static final Byte AP_APPLY_END  = 1 ;
    
    //活动类型 店铺
    public static final Byte AP_CONTENT_TYPE_STORE = 1;
    
    //活动类型 商品
    public static final Byte AP_CONTENT_TYPE_GOODS = 2;
    
    //活动类型 商品分类
    public static final Byte AP_CONTENT_TYPE_GOODSCLS = 3;
    
    //类型  1 专题 2 首页轮播活动
    public static final Byte ACTIVITY_SPECIAL = 1;
    //类型  1 专题 2 首页轮播活动
    public static final Byte ACTIVITY_ROLL = 2;
    
    
    // --------------广告管理 end --------------//
    
    // --------------优惠券获取方式 begin--------------//
    //获取方式：1-发放；2-领取
    public static final int COUPON_DRAW_TYPE_GRANT = 1;
    public static final int COUPON_DRAW_TYPE_DRAW = 2;
    // --------------优惠券获取方式 end--------------//
    
    // --------------优惠券类型 begin--------------//
    //优惠券类型：1-平台优惠券；2-店铺优惠券
    public static final int COUPON_CLASSIFY_PLATFORM = 1;
    public static final int COUPON_CLASSIFY_STORE = 2;
    // --------------优惠券类型 end--------------//
    
    // --------------优惠券常量 begin--------------//    
    public static final int COUPON_PUBLIC_NUM_ZERO = 0;
    // --------------优惠券常量 end--------------//
    
    // --------------优惠券适用对象 begin--------------//  
    //适用对象：0-全部
    public static final int COUPON_USE_OBJECT_ALL = 0;
    public static final int COUPON_USE_OBJECT_USER = 1;//指定会员
    // --------------优惠券适用对象 end--------------//
    
    // --------------优惠券领取状态 begin--------------//  
    //0-可领取
    public static final int COUPON_USE_DRAW_TYPE_NO = 0;
    //1-已领取
    public static final int COUPON_USE_DRAW_TYPE_YES = 1;
    //2-已抢光
    public static final int COUPON_USE_DRAW_TYPE_NULL = 2;
    // --------------优惠券领取状态 end--------------//
    
    // --------------优惠券关联类型 begin--------------//  
    //1-所有
    public static final int COUPON_TYPE_ALL = 1;
    //2-分类
    public static final int COUPON_TYPE_GOODSCLASS = 2;
    //3-商品
    public static final int COUPON_TYPE_GOODS = 3;
    // --------------优惠券关联类型 end--------------//
    
    // --------------优惠券关联类型 begin--------------//  
    //0-无限制
    public static final int COUPON_RESTRICT_NO = 0;
    //1-限制
    public static final int COUPON_RESTRICT_YES = 1;
    // --------------优惠券关联类型 end--------------//
    
    // ---优惠券状态 --start --//
    // 未开始
    public static final Byte SHOPPING_COUPON_STATUS_WAIT = 1;
    
    // 已开始
    public static final Byte SHOPPING_COUPON_STATUS_START = 2;
    
    // 已结束
    public static final Byte SHOPPING_COUPON_STATUS_END = 3;
    
    // 停止发放(禁止用户领取优惠券，已领取优惠券的用户仍然可以使用该优惠券)
    public static final Byte SHOPPING_COUPON_STATUS_STOP = 4;
    // ---优惠券状态 --start --//
    
    
    // ---代金券状态 --start --//
    // 未使用
    public static final Byte SHOPPING_USER_COUPON_STATUS_UNUSED = 1;
    
    // 已使用
    public static final Byte SHOPPING_USER_COUPON_STATUS_USED = 2;
    
    // 已过期
    public static final Byte SHOPPING_USER_COUPON_STATUS_FINISHED = 3;
    // ---代金券状态 --start --//
    
    
    
    // ---退货图片类型 --start --//
        //申请时图片
    public static final Byte IMG_1 =1 ;
        //物流时图片
    public static final Byte IMG_2 =2 ;
    // ---退货图片类型 --end --//
    
    // --------------我的钱包-交易记录/我的收益start--------------//
    //交易记录类别：0-全部
    public static final int JYTYPE_ALL = 0;
    //交易记录类别：1-联盟商家消费
    public static final int JYTYPE_ONLINE_PAY = 1;
    //交易记录类别：2-周边服务消费
    public static final int JYTYPE_OFFLINE_PAY = 2;
    //交易记录类别：3-转账
    public static final int JYTYPE_TURN_OUT = 3;
    //交易记录类别：4-提现
    public static final int JYTYPE_WITHDRAW_DEPOSIT = 4;
    //交易记录类别：5-退款
    public static final int JYTYPE_REFUND = 5;
    //交易记录类别：6-营业额
    public static final int JYTYPE_BALANCE = 6;
    //交易记录类别：7-未到账
    public static final int JYTYPE_OUT_OF_ACCOUNT = 7;
    
    //G明细列表查看详情：8-消费收益
    public static final int TYPE_CONSUME_INCOME = 8;
    //G明细列表查看详情：9-分享收益
    public static final int TYPE_SHARE_INCOME = 9;
    
    //我的收益类别：0-全部
    public static final int SYTYPE_ALL = 0;
    //我的收益类别：1-消费收益
    public static final int SYTYPE_CONSUME_INCOME = 1;
    //我的收益类别：2-分享收益
    public static final int SYTYPE_SHARE_INCOME = 2;
    // --------------我的钱包-交易记录/我的收益end--------------//

    //app协议类型: 注册协议
    public static final Byte APP_REGISTRATION_AGREEMENT = 1;
    //app协议类型: 注册协议id
    public static final Long APP_REGISTRATION_AGREEMENT_ID = 196645L;

    //APP协议类型: 入驻协议
    public static final Byte APP_ENTER_AGREEMENT = 2;
    //APP协议类型: 入驻协议id
    public static final Long APP_ENTER_AGREEMENT_ID = 196641L;

    //APP协议类型: 抢购协议
    public static final Byte APP_PURCHASE_AGREEMENT = 3;
    //APP协议类型: 抢购协议id
    public static final Long APP_PURCHASE_AGREEMENT_ID = 196642L;
    
    
    
    // -------------------预售活动 开始--------------------------//
    //预售活动状态  已开始
    public static final Byte PRE_SALE_STATUS_STARTED = 2;
    //预售活动状态  未开始
    public static final Byte PRE_SALE_STATUS_WAIT = 1;
    //预售活动状态  以结束
    public static final Byte PRE_SALE_STATUS_END = 3;
    
    // -------------------预售活动 结束--------------------------//
    
    
    
    
    // -------------------限时抢购 开始--------------------------//
    
    //抢购活动开启状态-关闭
    public static final Byte Flash_SALE_OPEN_STATUS_CLOSE= 0;
  //抢购活动开启状态-开启
    public static final Byte Flash_SALE_OPEN_STATUS_OPEN = 1;
    
    // 抢购活动状态 已开始
    public static final Byte FLASH_SALE_STATUS_STARTED = 2;
    //抢购活动状态 未开始
    public static final Byte FLASH_SALE_STATUS_WAIT = 1;
    //抢购活动状态 以结束
    public static final Byte FLASH_SALE_STATUS_END = 3;
    
    //抢购活动场次状态-展示
    public static final Boolean FLAH_SALE_ISDISPLAY_YES = true;
    //抢购活动场次状态-关闭
    public static final Boolean FLAH_SALE_ISDISPLAY_NO = false;
    
    //抢购商品状态，已开始
    public static final Byte FLASH_SALE_GOODS_START = 1;
    //抢购商品状态，以结束
    public static final Byte FLASH_SALE_GOODS_END = 2;
    
    //抢购活动场次状态 已开始
    public static final Byte FLASH_SALE_TIME_START = 2;
  //抢购活动场次状态 未开始
    public static final Byte FLASH_SALE_TIME_WAIT = 1;
    
    // -------------------限时抢购 结束--------------------------//
    
    //线上订单
    public static final Byte ORDER_ONLINE = 1 ;
    
    //线下订单
    public static final Byte ORDER_OFFLINE = 2 ;
    
    //到店付订单
    public static final Byte ORDER_STORE = 3 ;
     //app图片类型 1启动广告  2. 优惠券背景图片
    public static final Byte SHOPPING_APP_START_CATEGORY_ADV=1;
    // 2. 优惠券背景图片
    public static final Byte SHOPPING_APP_START_CATEGORY_COUPON=2;
    //3.app拼团图片
    public static final Byte SHOPPING_APP_START_CATEGORY_GROUP=3;
    //4. app预售背景图片
    public static final Byte SHOPPING_APP_START_CATEGORY_EXPECT=4;
    //5.直播背景图
    public static final Byte SHOPPING_APP_START_CATEGORY_PLAY=5;
    //6.爱心帮
    public static final Byte SHOPPING_APP_START_CATEGORY_HEART=6;
    
    //7.脉砍价
    public static final Byte SHOPPING_APP_START_BARGAIN=7;
    
    //8.首页弹出广告
    public static final Byte SHOPPING_APP_START_HOMEPAGEAD=8;

    // 热销市场类型：热销市场
    public static final Byte HOTMARKET_CATEGORY_HOTMARKET = 1;
    
    // 热销市场类型：人气爆款
    public static final Byte HOTMARKET_CATEGORY_POPULAR = 2;

    // 热销市场类型：热门潮流(PC商城)
    public static final Byte HOTMARKET_CATEGORY_FASHION = 3;

    // 商品活动状态：活动中
    public static final Byte GOODS_ACTIVITY_YES = 1;
    
    //未绑定
    public static final Byte NO_BIND = 0 ;
    
    //已绑定
    public static final Byte BINDED = 1 ;
    
    
    
    
    //用户不存在
    public static final int LOGIN_ERROR_NOT_EXIST = 1 ;
    
    //密码错误
    public static final int LOGIN_ERROR_PASSWORD = 2 ;
    
    //已经绑定过了
    public static final int LOGIN_ERROR_ALREADY_BIND = 3 ;
    //联盟商家统计
    public static final Byte SHOPPING_STATISTICS_TYPE_STORE=1;
    //周边服务统计
    public static final Byte SHOPPING_STATISTICS_TYPE_SHANGJIA=2;
    
    // --------------拼团常量使用 begin--------------//
    //拼团商品 是否推荐 0 否 1是
    public static final Byte RECOMMEND = 1 ;
    public static final Byte NOT_RECOMMEND = 0 ;
    //拼团类型:0-普通团;1-超级团;2-限时团;3-新人团
    public static final Integer GROUP_PURCHASE_TYPE_COMMON = 0;
    public static final Integer GROUP_PURCHASE_TYPE_SUPER = 1;
    public static final Integer GROUP_PURCHASE_TYPE_TIME = 2;
    public static final Integer GROUP_PURCHASE_TYPE_NEW = 3;
    
    //审核状态:0-待审核;1-审核通过;2-审核不通过;3-商品下架
    public static final Integer GROUP_PURCHASE_AUDIT_STATUS_PENDING = 0;
    public static final Integer GROUP_PURCHASE_AUDIT_STATUS_YES = 1;
    public static final Integer GROUP_PURCHASE_AUDIT_STATUS_NO = 2;
    public static final Integer GROUP_PURCHASE_AUDIT_STATUS_OFFSHELF = 3;
    
    //拼团活动状态:1-未开始;2-已开始;3-已结束
    public static final Integer GROUP_PURCHASE_STATUS_WAIT = 1;
    public static final Integer GROUP_PURCHASE_STATUS_START = 2;
    public static final Integer GROUP_PURCHASE_STATUS_END = 3;
    
    //开团状态:1-拼团中;2-成功;3-失败
    public static final Integer GROUP_PURCHASE_OPENSTATUS_BEGINNING = 1;
    public static final Integer GROUP_PURCHASE_OPENSTATUS_SUCCESS = 2;
    public static final Integer GROUP_PURCHASE_OPENSTATUS_FAIL = 3;
    
    //拼团成员类型:0-团长;1-普通成员
    public static final Integer GROUP_PURCHASE_MEMBERTYPE_HEAD = 0;
    public static final Integer GROUP_PURCHASE_MEMBERTYPE_MEMBER = 1;
    // --------------拼团常量使用 end--------------//
    
    // 文章管理: 合同的分类id
    public static final Long ARTICLE_CONTRACT_CLASSID = 163845L;

    // ---------------合同管理: 审核状态/审核次数 --------------- //
    // 未申请过
    public static final Byte CONTRACT_AUDIT_NOTAPPLY = -1;
    // 审核中
    public static final Byte CONTRACT_AUDIT_WAIT = 0;
    // 审核通过
    public static final Byte CONTRACT_AUDIT_PASS = 1;
    // 审核不通过
    public static final Byte CONTRACT_AUDIT_NOTPASS = 2;
    // 审核次数超限
    public static final Byte CONTRACT_AUDIT_LIMITED= 3;

    // 一次
    public static final Byte CONTRACT_LIMIT_ONE = 1;
    // 两次
    public static final Byte CONTRACT_LIMIT_TWO = 2;
    
    //-------------短信标示-开始-----------------------//
    public static final String sms_toseller_online_pay_ok_notify = "sms_toseller_online_pay_ok_notify";
    
   //-------------短信标示-结束-----------------------//
    
    //'1普通订单 2优惠活动订单 3团购订单 4团购单独购买 5预购订单'
    public static final Integer ORDER_TYPE_GENRERAL = 1;
    public static final Integer ORDER_TYPE_PREFERENTIAL = 2;
    public static final Integer ORDER_TYPE_GROUP = 3;
    public static final Integer ORDER_TYPE_GROUP_SELF = 4;
    public static final Integer ORDER_TYPE_PRESALE = 5;
    public static final Integer ORDER_TYPE_BARGAIN = 6;
    
    
    
    //默认头像地址
    public static final String DEFAULT_AVATAR = "/avatar/default.png" ;

    // ---------------快递查询通用接口mark--------------- //
    // 快递鸟
    public static final String EXPRESS_KDN = "KDN";
    
    public static final String EXPRESS_KD100 = "KD100";
    
    //活动状态 1-未开始 2-已开始 3- 已结束  Integer类型
    public static final Integer Activity_WAIT =1;
    public static final Integer Activity_START =2;
    public static final Integer Activity_END =3;
    
  //活动状态 1-未开始 2-已开始 3- 已结束 byte类型
    public static final Byte Activity_WAIT_BYTE =1;
    public static final Byte Activity_START_BYTE  =2;
    public static final Byte Activity_END_BYTE  =3;

    //订单组合状态
    public static final Integer  ORDER_GROUP_TYPE_ZERO = 0;
    public static final Integer  ORDER_GROUP_TYPE_ONE = 1;
    public static final Integer  ORDER_GROUP_TYPE_TWO = 2;
    
    // 预售商品审核状态: 0-待审核;1-审核通过;2-审核不通过;3-商品下架
    public static final Byte PRE_SALE_GOODS_AUDIT_WAIT = 0;
    public static final Byte PRE_SALE_GOODS_AUDIT_PASS = 1;
    public static final Byte PRE_SALE_GOODS_AUDIT_NOTPASS = 2;
    public static final Byte PRE_SALE_GOODS_AUDIT_OFFSLAE = 3;

    //预售活动是否发送短信 1- 未发送 2- 已发送
    public static final Integer PRE_SALE_NOT_SNED_MESSAGE =1;
    public static final Integer PRE_SALE_SNED_MESSAGE =2;
    
    // 顶级菜单code
    public static final String TOP_MENU = "0";
    
    //商品活动状态  抢购
    public static final Byte GOODS_ACTIVE_FLASH = 1;
    //商品活动状态  普通
    public static final Byte GOODS_ACTIVE_NORMAL = 2;
    //商品活动状态  拼团
    public static final Byte GOODS_ACTIVE_GROUP = 3;
    //商品活动状态  预售
    public static final Byte GOODS_ACTIVE_PRESALE = 4;
    //商品活动状态  脉豆
    public static final Byte GOODS_ACTIVE_MDOU = 5;
    //商品活动状态  砍价
    public static final Byte GOODS_ACTIVE_BARGIN = 6;
  //商品活动状态  虚拟
    public static final Byte GOODS_ACTIVE_VIRTUAL = 7;
    
    //数据库中商品状态
    //商品活动状态  普通
    public static final Byte DB_GOODS_ACTIVE_NORMAL = 0;
    //商品活动状态  预售
    public static final Byte DB_GOODS_ACTIVE_PRESALE = 1;
    //商品活动状态  抢购
    public static final Byte DB_GOODS_ACTIVE_FLASH = 2;
    //商品活动状态  拼团
    public static final Byte DB_GOODS_ACTIVE_GROUP = 3;
    //商品活动状态  砍价
    public static final Byte DB_GOODS_ACTIVE_BARGIN = 4;
    
    //虚拟商品标示
    public static final Integer VIRTUAL_GOODS = 1;
    
    //邮箱第三方绑定
    public static final String 	BINDING_EMAIL = "2";
    //手机第三方号绑定
    public static final String BINDING_MOBILE = "1";

    // 脉豆变动类型: 无变动
    public static final Byte MDOU_NO_CHANGE = 0;
    // 脉豆变动类型: 充值
    public static final Byte MDOU_RECHARGE = 1;
    // 脉豆变动类型: 转入
    public static final Byte MDOU_TRANSFER_INTO = 2;
    // 脉豆变动类型: 消耗
    public static final Byte MDOU_EXPEND = 3;
    // 脉豆变动类型: 退还
    public static final Byte MDOU_RETURN = 4;
    // 脉豆变动类型: 转出
    public static final Byte MDOU_TRANSFER_OUT = 5;
    // 脉豆变动类型: 签到
    public static final Byte MDOU_SIGN = 6;
    // 脉豆变动类型: 抽奖
    public static final Byte MDOU_GAME = 7;
    
    


    // 脉豆金额变动类型: 无变动
    public static final Byte MDOU_AMOUNT_NO_CHANGE = 0;
    // 脉豆金额变动类型: 增加
    public static final Byte MDOU_AMOUNT_INCREASE = 1;
    // 脉豆金额变动类型: 减少
    public static final Byte MDOU_AMOUNT_DECREASE = 2;

    // 脉豆金额变动异常状态码: 转出账号异常
    public static final Byte MDOU_TRANSFER_ACCOUNT_EXCEPTION = 10;
    // 脉豆金额变动异常状态码: 转入账号异常
    public static final Byte MDOU_INTO_ACCOUNT_EXCEPTION = 20;
    // 脉豆金额变动异常状态码: 转出账号余额不足
    public static final Byte MDOU_TRANSFER_ACCOUNT_BALANCE_NOT_ENOUGH = 30;
    // 脉豆金额变动异常状态码: 转出转入账号相同
    public static final Byte MDOU_TRANSFER_SAME_ACCOUNT = 40;
    
    
    
    /************用户关联账户类型***********/
    //1.QQ
    public static final Byte USER_ASSOICATED_ACCOUNT_QQ=1;
    //2.微信
    public static final Byte USER_ASSOICATED_ACCOUNT_WEIXIN=2;
    
    /*** 商品品牌***/
    //品牌类型（1:平台品牌 2:商家品牌）
    public static final String BRAND_TYPE_ADMIN = "1";
    public static final String BRAND_TYPE_STORE = "2";
    
    //是否显示（1:显示 0:不显示）
    public static final Integer BRAND_DISPLAY_YES = 1;
    public static final Integer BRAND_DISPLAY_NO = 0;

    //砍价成员类型 主
    public static final  Byte FIRST =  0 ;
    //砍价成员类型 辅
    public static final  Byte SECONS =  1 ;

    //appIco是否显示
    public static final Integer APP_ICO_STATUS_YES = 1;
    public static final Integer APP_ICO_STATUS_NO = 0;
    
    //新品推荐
    public static final Integer NEW_RECOMMEND_NO = 0;
    public static final Integer NEW_RECOMMEND_YES = 1;
    
    //砍价状态:0进行中;1-成功;2-失败 3-已砍至最低价
    public static final  Byte BARGIN_ING =  0 ;
    
    public static final  Byte BARGIN_SUCCESS =  1 ;
    
    public static final  Byte BARGIN_FAIL =  2 ;
    
    public static final  Byte BARGIN_LOWEST =  3 ;
    
    //砍价审核 :0-待审核;1-审核通过;2-审核不通过;3-商品下架
    public static final  Byte BARGIN_STATUS_WAIT =  0 ;
    
    public static final  Byte BARGIN_STATUS_PASS =  1 ;
    
    public static final  Byte BARGIN_STATUS_REJECT =  2 ;
    
    public static final  Byte BARGIN_STATUS_GOODS_NOT_AUTID =  3 ;
    
    //砍价活动状态 '1-未开始;2-已开始;3-已结束'
    public static final  Byte BARGIN_NOT_START = 1 ;
    
    public static final  Byte BARGIN_STARTED = 2 ;
    
    public static final  Byte BARGIN_FINISHED = 3 ;
    
    //砍价推荐 0: 不推荐 1:推荐
    public static final  Byte BARGIN_RECOMMEND = 1 ;
    
    public static final  Byte BARGIN_NOT_RECOMMEND = 0 ;

    // 周边服务商家资质图片类型: 2:身份证, 3:特殊资质
    public static final Byte OFFLINE_PHOTO_ID_CARD = 2;

    public static final Byte OFFLINE_PHOTO_SPECIFIC = 3;
    
    //签到  奖励领取类型 是否领取（1.不可领取 2.待领取 3.已领取）
    public static final Integer SIGN_CONTINU_TYPE_ONE = 1;
    public static final Integer SIGN_CONTINU_TYPE_TWO = 2;
    public static final Integer SIGN_CONTINU_TYPE_THREE = 3;

    // 周边订单类型 1:周边服务订单，2:到店付
    public static final Byte OFFLINE_ORDER_TYPE_ONLINE = 1;

    public static final Byte OFFLINE_ORDER_TYPE_OFFLINE = 2;

    // 周边订单来源 1:app，2:wap，3:pc
    public static final Byte OFFLINE_ORDRE_SOURCE_APP = 1;

    public static final Byte OFFLINE_ORDRE_SOURCE_WAP = 2;

    public static final Byte OFFLINE_ORDRE_SOURCE_PC = 3;
    
    //爱心帮帮帮：
    //发布类型：1001-待接单；1002-进行中；1003-放弃发布；1004-到期；1006-已完成；1007-强制停止；
    // 1001-待接单
    public static final Integer HELP_TASK_STATUS_1001 = 1001;
    
    // 1002-进行中
    public static final Integer HELP_TASK_STATUS_1002 = 1002;
    
    // 1003-放弃发布
    public static final Integer HELP_TASK_STATUS_1003 = 1003;
    
    // 1004-到期
    public static final Integer HELP_TASK_STATUS_1004 = 1004;
    
    // 1006-已完成
    public static final Integer HELP_TASK_STATUS_1006 = 1006;
    
    // 1007-强制停止
    public static final Integer HELP_TASK_STATUS_1007 = 1007;
    
    // 求助操作类型：删除操作
    public static final Integer HELP_OPERATE_DEL = 1;
    
    // 求助操作类型：放弃求助操作
    public static final Integer HELP_OPERATE_GUP = 2;
    
    // 传给app端展示的发布求助状态：
    // 1-发布中
    public static final Integer HELP_TASK_STATUS_1 = 1;
    // 2-待处理
    public static final Integer HELP_TASK_STATUS_2 = 2;
    // 3-进行中
    public static final Integer HELP_TASK_STATUS_3 = 3;
    // 4-已放弃
    public static final Integer HELP_TASK_STATUS_4 = 4;
    // 5-已过期
    public static final Integer HELP_TASK_STATUS_5 = 5;
    // 6-已完成
    public static final Integer HELP_TASK_STATUS_6 = 6;
    // 7-已停止
    public static final Integer HELP_TASK_STATUS_7 = 7;

    //爱心帮帮帮 -订单的状态
    //201-发布者待处理
    public static final Integer HELP_ORDER_STATUS_201=201;

    //2011 --处理拒绝
    public static final Integer HELP_ORDER_STATUS_2011=2011;

    //2012 -处理同意
    public static final Integer HELP_ORDER_STATUS_2012=2012;

    //202 -待完成
    public static final Integer HELP_ORDER_STATUS_202=202;

    //203 -完成
    public static final Integer HELP_ORDER_STATUS_203=203;

    //204 -已评价
    public static final Integer HELP_ORDER_STATUS_204=204;

    //205-取消订单
    public static final Integer HELP_ORDER_STATUS_205=205;

    // pc首页 商品推荐模块
    // 1-发现好货
    public static final Integer PC_GOODS_RECOMMEND_DISCOVER = 1;
    // 2-新品推荐
    public static final Integer PC_GOODS_RECOMMEND_NEWLY = 2;
    // 3-人气热销
    public static final Integer PC_GOODS_RECOMMEND_HOT = 3;
    // 4-猜你喜欢
    public static final Integer PC_GOODS_RECOMMEND_RELATED = 4;
    
    
    
    // 商品链接相对路劲
    public static final String GOODS_URL = "product?id=";
    
    // 线上商品
    public static final Byte ONLINE_GOODS = 0;
    // 线下套餐
    public static final Byte OFFLINE_GOODS = 1;
    
    // pv分润方式
    public static final Byte PROFIT_PV = 1;
    // 固定分润金额方式
    public static final Byte PROFIT_MONEY = 2;
    
    // 已读
    public static final Integer READ_YES = 1;
    

}

