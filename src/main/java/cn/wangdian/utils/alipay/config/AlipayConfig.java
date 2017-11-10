package cn.wangdian.utils.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public final static String partner = "2088621917137025";

	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public final static String seller_id = partner;

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	//没去除 '\n' 的原私钥
//	public final static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKA4+HV/SSOd5xOt\n" +
//			"5/OWt/b5rggOZJ/gJVr8rjQnWWlJqtHqKDdkgMqv/FeaHTwYy/AyHBMIyCiyabGJ\n" +
//			"TQdOjYysCTCrpuXa0EX5vK2SqhGhl8CohCpsgVznaOYcWhJSQaotG+dv/A3zcMLK\n" +
//			"X4BLb23yrSZTDM03ePbyRbvJZECXAgMBAAECgYAT7WIBgxY+psnxqaR6qFkzD3S0\n" +
//			"rc2YENG0kk68T9SQAsiLGRttYEOgcejpoFJYgiEDO8hAGcyDO0Vn/lKlQQDbtrwn\n" +
//			"2wY9l+BVDrwaSLlcyl7xekZ6Tr2lOdwrItJim8cS1Hq/8Jw1lafYC1p763v31Jkr\n" +
//			"1HEQa3Kd5xdiKi8aeQJBAMzwb1Y4Rn1VrFaI4VsoQivq6dLwG3RZkKn20dvX8to1\n" +
//			"2/lx600tOtNnUhr/Z3Bl0FPT2e9YlVqqBeGLR3ATNpsCQQDIJGODyT6IeknEVPaP\n" +
//			"JOxUzNkOrZL/kawoS9NFVCEY1FpKuuZ0JdkQumXP3X9WmNKWLi23gG6e30s2kKS6\n" +
//			"w7+1AkAimq6SBmqQmzjAW9iBhei2dbIJthVMrhrpOA3zayFMiy+bjQrIO0zliVqp\n" +
//			"yFp09hwZK3pvGawOZQsdLGFhoTzlAkA+RMQJboVcJN/qi5yicJRu1cT6ghpoFUSA\n" +
//			"zQaQXVpmxTrKYVft7xCX2cZvY7SIlsd+HiPvIbBJEaPu+BWoXdq5AkEAulICmt9z\n" +
//			"2WMF63j18b4QbIm2B8u557twmC/DbDLLVw+u75eYVNgwcL1wwt+iQtZb0gt4IW2B\n" +
//			"15vRJpk9PAE2ug==";

	public final static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKA4+HV/SSOd5xOt5/OWt/b5rggOZJ/gJVr8rjQnWWlJqtHqKDdkgMqv/FeaHTwYy/AyHBMIyCiyabGJTQdOjYysCTCrpuXa0EX5vK2SqhGhl8CohCpsgVznaOYcWhJSQaotG+dv/A3zcMLKX4BLb23yrSZTDM03ePbyRbvJZECXAgMBAAECgYAT7WIBgxY+psnxqaR6qFkzD3S0rc2YENG0kk68T9SQAsiLGRttYEOgcejpoFJYgiEDO8hAGcyDO0Vn/lKlQQDbtrwn2wY9l+BVDrwaSLlcyl7xekZ6Tr2lOdwrItJim8cS1Hq/8Jw1lafYC1p763v31Jkr1HEQa3Kd5xdiKi8aeQJBAMzwb1Y4Rn1VrFaI4VsoQivq6dLwG3RZkKn20dvX8to12/lx600tOtNnUhr/Z3Bl0FPT2e9YlVqqBeGLR3ATNpsCQQDIJGODyT6IeknEVPaPJOxUzNkOrZL/kawoS9NFVCEY1FpKuuZ0JdkQumXP3X9WmNKWLi23gG6e30s2kKS6w7+1AkAimq6SBmqQmzjAW9iBhei2dbIJthVMrhrpOA3zayFMiy+bjQrIO0zliVqpyFp09hwZK3pvGawOZQsdLGFhoTzlAkA+RMQJboVcJN/qi5yicJRu1cT6ghpoFUSAzQaQXVpmxTrKYVft7xCX2cZvY7SIlsd+HiPvIbBJEaPu+BWoXdq5AkEAulICmt9z2WMF63j18b4QbIm2B8u557twmC/DbDLLVw+u75eYVNgwcL1wwt+iQtZb0gt4IW2B15vRJpk9PAE2ug==";

	//这个与商户公钥不同，请注意!!!
	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public final static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	//老版wap支付密钥
//	public final static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCGs0HspGsCVJbXHUIQxP+f6OtVDHoi4Bkae7yLCc/qc5YYq+JaN8AS3GLzVariKpzw3byQo7GLUadHNSg1emSkQcx8ix3Cd7VfmpuBZ8z1R7womgGmy3/0bLJyPEdRvqnepOyCKrLiALusyJA6Q7km/xykDFh6cg/reXMnVoYkewIDAQAB";

	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	// {example}写自己的域名，
	// {project_name}写自己的项目名
	// 如此配置是为了支付成功后可以成功回调到自己的服务器
	public final static String notify_url = "http://www.dianxiaoer365.com/notify_url";
	//放到 newzillion.cn 测试
//	public final static String notify_url = "http://www.newzillion.cn/notify_url";


	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	// {example}写自己的域名，
	// {project_name}写自己的项目名
	// 如此配置是为了支付成功后可以成功回调到自己的服务器
	public final static String return_url = "http://www.dianxiaoer365.com/return_url";

	// 签名方式
	public final static String sign_type = "RSA";

	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public final static String log_path = "/home/wangdian/alipay.log"; // 这里根据不同偏好设置不同的日志文件位置

	// 字符编码格式 目前支持utf-8
	public final static String input_charset = "utf-8";

	// 支付类型 ，无需修改
	public final static String payment_type = "1";

	// 调用的接口名，无需修改
	public final static String service = "alipay.wap.create.direct.pay.by.user";

	// 店主支付完成年费后，支付宝服务器通知我们服务器
	public final static String annualfee_notify_url = "http://www.dianxiaoer365.com/annualFee/notify_url";

	// 店主支付完成年费后，支付宝页面跳转到该页面
	public final static String annualfee_return_url = "http://www.dianxiaoer365.com/annualFee/return_url";

	//支付宝转账的网关
    public final static String transfer_url = "https://openapi.alipay.com/gateway.do";

    //APP_ID
    public final static String app_id = "2017042106873218";

    //支付宝返回信息的格式 目前只支持 json
    public final static String transfer_return_format = "json";

    //收账方的支付宝类型 ==> ALIPAY_LOGONID 指的是普通用户
    public final static String payee_type = "ALIPAY_LOGONID";

    public final static String transfer_show_name = "店小二提现";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}

