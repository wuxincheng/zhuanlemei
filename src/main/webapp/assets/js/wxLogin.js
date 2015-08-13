!function(a, b) {
	function d(a) {
		var e, c = b.createElement("iframe"), d = "https://open.weixin.qq.com/connect/qrconnect?appid="
				+ a.appid
				+ "&scope="
				+ a.scope
				+ "&redirect_uri="
				+ a.redirect_uri + "&state=" + a.state + "&login_type=jssdk";
		d += a.style ? "&style=" + a.style : "", d += a.href ? "&href="
				+ a.href : "", c.src = d, c.frameBorder = "0",
				c.allowTransparency = "true", c.scrolling = "no",
				c.width = "300px", c.height = "400px", e = b
						.getElementById(a.id), e.innerHTML = "", e
						.appendChild(c);
	}
	a.WxLogin = d;
}(window, document);

/*
https://open.weixin.qq.com/connect/qrconnect?appid=wx8bc224d6280b4ace&scope=snsapi_login&redirect_uri=http%3A%2F%2Fwww.zhuanlemei.com%2Ftop%2Foauth%2Fwechat%2Fcallback&state=1F89790D141190956ACED9DDD44FA10D&login_type=jssdk&style=black&href=http://www.zhuanlemei.com/top/assets/css/wechat.css
 */