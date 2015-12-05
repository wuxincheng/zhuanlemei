package lihu.zhuanlemei.oauth.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import sun.net.www.protocol.https.Handler;

/**
 * Https请求
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年7月27日 下午9:45:55
 * 
 */
public class HttpsConnection {

	private static final Logger log = Logger.getLogger(HttpsConnection.class);

	private static final String METHOD_GET = "GET";
	private static final String METHOD_POST = "POST";
	private static final String DEFAULT_CHARSET = "utf-8";
	
	public static String doPost(String url, String params, String charset, int connectTimeout, int readTimeout,
			boolean getLocation) throws Exception {
		String ctype = "text/html;charset=" + charset;
		byte[] content = {};
		if (params != null) {
			content = params.getBytes(charset);
		}
		
		return doPost(url, ctype, content, connectTimeout, readTimeout, getLocation);
	}

	public static String doPost(String url, String ctype, byte[] content, int connectTimeout, int readTimeout,
			boolean getLocation) throws Exception {
		HttpsURLConnection conn = null;
		OutputStream out = null;
		String rsp = null;
		try {
			try {
				SSLContext ctx = SSLContext.getInstance("SSL");
				ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
				SSLContext.setDefault(ctx);

				conn = getConnection(new URL(null, url, new Handler()), METHOD_POST, ctype);
				conn.setRequestMethod("POST");
				conn.setHostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				});
				conn.setConnectTimeout(connectTimeout);
				conn.setReadTimeout(readTimeout);
			} catch (Exception e) {
				log.error("doPost GET_CONNECTOIN_ERROR, URL = " + url, e);
				throw e;
			}
			try {
				out = conn.getOutputStream();
				out.write(content);
				rsp = getResponseAsString(conn);
				if (rsp != null) {
					if (getLocation) {
						String location = conn.getHeaderField("Location");
						if (location != null) {
							return location.substring(location.lastIndexOf("/") + 1, location.length());
						}
					}
				}
			} catch (IOException e) {
				log.error("doPost REQUEST_RESPONSE_ERROR, URL = " + url, e);
				throw e;
			}

		} finally {
			if (out != null) {
				out.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}

		return rsp;
	}

	public static String doGet(String url) throws Exception{
		return doGet(url, null, 500, 1000);
	}
	
	public static String doGet(String url, String ctype, int connectTimeout, int readTimeout) throws Exception {
		HttpsURLConnection conn = null;
		String rsp = null;
		try {
			try {
				SSLContext ctx = SSLContext.getInstance("SSL");
				ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
				SSLContext.setDefault(ctx);

				conn = getConnection(new URL(null, url, new Handler()), METHOD_GET, ctype);
				conn.setHostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				});
				conn.setConnectTimeout(connectTimeout);
				conn.setReadTimeout(readTimeout);
			} catch (Exception e) {
				log.error("doGet GET_CONNECTOIN_ERROR, URL = " + url, e);
				throw e;
			}
			try {
				rsp = getResponseAsString(conn);
			} catch (IOException e) {
				log.error("doGet REQUEST_RESPONSE_ERROR, URL = " + url, e);
				throw e;
			}

		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return rsp;
	}

	private static class DefaultTrustManager implements X509TrustManager {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert
		 * .X509Certificate[], java.lang.String)
		 */
		@Override
		public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
				throws CertificateException {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert
		 * .X509Certificate[], java.lang.String)
		 */
		@Override
		public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
				throws CertificateException {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
		 */
		@Override
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}

	private static javax.net.ssl.HttpsURLConnection getConnection(java.net.URL JNurl, String method, String ctype)
			throws IOException {
		javax.net.ssl.HttpsURLConnection conn = (javax.net.ssl.HttpsURLConnection) JNurl.openConnection();
		conn.setRequestMethod(method);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		// conn.setRequestProperty("Accept", "text/plain");
		// conn.setRequestProperty("User-Agent", "stargate");
		// conn.setRequestProperty("Content-Type", ctype);
		return conn;
	}

	protected static String getResponseAsString(HttpsURLConnection conn) throws IOException {
		String charset = getResponseCharset(conn.getContentType());
		InputStream es = conn.getErrorStream();
		if (es == null) {
			return getStreamAsString(conn.getInputStream(), charset);
		} else {
			String msg = getStreamAsString(es, charset);
			if (StringUtils.isEmpty(msg)) {
				throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
			} else {
				throw new IOException(msg);
			}
		}
	}

	private static String getStreamAsString(InputStream stream, String charset) throws IOException {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
			StringWriter writer = new StringWriter();

			char[] chars = new char[256];
			int count = 0;
			while ((count = reader.read(chars)) > 0) {
				writer.write(chars, 0, count);
			}

			return writer.toString();
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}

	private static String getResponseCharset(String ctype) {
		String charset = DEFAULT_CHARSET;

		if (!StringUtils.isEmpty(ctype)) {
			String[] params = ctype.split(";");
			for (String param : params) {
				param = param.trim();
				if (param.startsWith("charset")) {
					String[] pair = param.split("=", 2);
					if (pair.length == 2) {
						if (!StringUtils.isEmpty(pair[1])) {
							charset = pair[1].trim();
						}
					}
					break;
				}
			}
		}

		return charset;
	}

}
