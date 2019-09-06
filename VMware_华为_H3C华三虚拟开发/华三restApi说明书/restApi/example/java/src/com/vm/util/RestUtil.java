package com.vm.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class RestUtil {

	/**
	 * 通过REST接口查询外部云数据, 此方法要求接口返回状态码为 200 {HttpStatus.SC_OK}，错误信息返回状态码 409 {HttpStatus.SC_CONFLICT}。
	 * @param <T> 返回结果类型
	 * @param client http client
	 * @param uri 接口uri
	 * @param clazz 返回结果的类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T httpGet(DefaultHttpClient client, String uri, Class<T> clazz) {
		T result = null;
		HttpGet httpGet = null;
		try {
			httpGet = new HttpGet(uri);
			httpGet.addHeader("accept", "application/xml");
			HttpResponse response = client.execute(httpGet);
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				 String resultData = EntityUtils.toString(response.getEntity(), "UTF-8");
				 if (StringUtils.isNotBlank(resultData)) {
					 if (Long.class == clazz) {
						 result = (T) Long.valueOf(resultData);
					 } else if (String.class == clazz) {
						 result = (T) resultData;
					 } else if (Boolean.class == clazz) {
						 result = (T) Boolean.valueOf(resultData);
					 } else {
						 StringReader reader = new StringReader(resultData);
						 result = JAXB.unmarshal(reader, clazz);						 
					 }
				 }
			} else if (HttpStatus.SC_CONFLICT == response.getStatusLine().getStatusCode()) {
                // 操作错误
                int errorCode = Integer.valueOf(response.getFirstHeader("Error-Code").getValue());
                String charset = response.getFirstHeader("Charset").getValue();
                Header header = response.getFirstHeader("Error-Message");                
                String errorMsg = charset != null ? new String(header.getValue().getBytes("ISO-8859-1"), charset) : new String(header.getValue().getBytes("ISO-8859-1"));
                throw new AppException("Error code:" + errorCode + ",Error-Message:" + errorMsg);
            } else {//其他错误，如 404 URI路径不存在
            	int statusCode = response.getStatusLine().getStatusCode();
                debug("request data error\n request uri :" + uri);
            	throw new AppException("http status : " + statusCode);
            }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (httpGet != null) {
				httpGet.abort();
			}
		}
		return result;
	}

	 /**
	 * 通过REST接口POST数据, 此方法要求接口返回状态码为 204 {HttpStatus.SC_NO_CONTENT}/ 200 {HttpStatus.SC_OK}，错误信息返回状态码 409 {HttpStatus.SC_CONFLICT}。
	 * @param <T>
	 * @param httpConnManager http 链接管理类
	 * @param cloud 共有云对象
	 * @param uri 接口uri
	 * @param obj 发送到信息实体
	 * @param clazz 返回结果类型
	 * @return
	 */
	public static <T> T httpPost(DefaultHttpClient client, String uri, Object obj, Class<T> clazz) {
		return httppostOrPut(client, uri, obj, clazz, 0);
	}
	
	 /**
	 * 通过REST接口PUT数据, 此方法要求接口返回状态码为 204 {HttpStatus.SC_NO_CONTENT}/ 200 {HttpStatus.SC_OK}，错误信息返回状态码 409 {HttpStatus.SC_CONFLICT}。
	 * @param <T>
	 * @param httpConnManager http 链接管理类
	 * @param cloud 共有云对象
	 * @param uri 接口uri
	 * @param obj 发送到信息实体
	 * @param clazz 返回结果类型
	 * @return
	 */
	public static <T> T httpPut(DefaultHttpClient client, String uri, Object obj, Class<T> clazz) {
		return httppostOrPut(client, uri, obj, clazz, 1);
	}
	
	public static void httpDelete(DefaultHttpClient client, String uri) {
		HttpDelete httpGet = new HttpDelete(uri);
		try {
			HttpResponse response = client.execute(httpGet);
			if (HttpStatus.SC_NO_CONTENT == response.getStatusLine().getStatusCode() || HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				return;
			} else if (HttpStatus.SC_CONFLICT == response.getStatusLine().getStatusCode()) {
                // 操作错误
                int errorCode = Integer.valueOf(response.getFirstHeader("Error-Code").getValue());
                String charset = response.getFirstHeader("Charset").getValue();
                Header header = response.getFirstHeader("Error-Message");                
                String errorMsg = charset != null ? new String(header.getValue().getBytes("ISO-8859-1"), charset) : new String(header.getValue().getBytes("ISO-8859-1"));
                throw new AppException("Error code:" + errorCode + ", Error-Message:" + errorMsg);
            } else {//其他错误，如 404 URI路径不存在
            	int statusCode = response.getStatusLine().getStatusCode();
            	debug("request data error\n request uri :" + uri);
                throw new AppException("http status : " + statusCode);
            }
		} catch (IOException e) {
			debug(e);
		} finally {
			if (httpGet != null) {
				httpGet.abort();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T httpDelete(DefaultHttpClient client, String uri, Class<T> clazz) {
		T result = null;
		HttpDelete httpDel = new HttpDelete(uri);
		try {
			HttpResponse response = client.execute(httpDel);
			
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				 String resultData = EntityUtils.toString(response.getEntity(), "UTF-8");
				 if (StringUtils.isNotBlank(resultData)) {
					 if (Long.class == clazz) {
						 result = (T) Long.valueOf(resultData);
					 } else if (String.class == clazz) {
						 result = (T) resultData;
					 } else {
						 StringReader reader = new StringReader(resultData);
						 result = JAXB.unmarshal(reader, clazz);						 
					 }
				 }
			} else if (HttpStatus.SC_CONFLICT == response.getStatusLine().getStatusCode()) {
                // 操作错误
                int errorCode = Integer.valueOf(response.getFirstHeader("Error-Code").getValue());
                String charset = response.getFirstHeader("Charset").getValue();
                Header header = response.getFirstHeader("Error-Message");                
                String errorMsg = charset != null ? new String(header.getValue().getBytes("ISO-8859-1"), charset) : new String(header.getValue().getBytes("ISO-8859-1"));
                throw new AppException("Error code:" + errorCode + ", Error-Message:" + errorMsg);
            } else {//其他错误，如 404 URI路径不存在
            	int statusCode = response.getStatusLine().getStatusCode();
            	debug("request data error\n request uri :" + uri);
                throw new AppException("http status : " + statusCode);
            }
		} catch (IOException e) {
			debug(e);
		} finally {
			if (httpDel != null) {
				httpDel.abort();
			}
		}
		return result;
	}
	
	   /**
	 * 通过REST接口POST/PUT数据, 此方法要求接口返回状态码为 204 {HttpStatus.SC_NO_CONTENT}/ 200 {HttpStatus.SC_OK}，错误信息返回状态码 409 {HttpStatus.SC_CONFLICT}。
	 * @param <T>
	 * @param httpConnManager http 链接管理类
	 * @param cloud 共有云对象
	 * @param uri 接口uri
	 * @param obj 发送到信息实体
	 * @param clazz 返回结果类型
	 * @param type 0: post, other : put
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static  <T> T httppostOrPut(DefaultHttpClient client, String uri, Object obj, Class<T> clazz, int type) {
		HttpEntityEnclosingRequestBase httpRequest = type == 0 ? new HttpPost(uri) : new HttpPut(uri);
		T result = null;
		try {
			if (obj != null) {
				String objXml = convertObjectToXml(obj, obj.getClass());
				HttpEntity entity = new StringEntity(objXml, "application/xml", "UTF-8");
				httpRequest.setEntity(entity);
			}

			HttpResponse response = client.execute(httpRequest);
			if (HttpStatus.SC_NO_CONTENT == response.getStatusLine().getStatusCode()) {
				//成功返回
				return null;
			} else if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode() && clazz != null) {
				 String resultData = EntityUtils.toString(response.getEntity(), "UTF-8");
				 if (StringUtils.isNotBlank(resultData)) {
					 if (Long.class == clazz) {
						 result = (T) Long.valueOf(resultData);
					 } else if (String.class == clazz) {
						 result = (T) resultData;
					 } else {
						 StringReader reader = new StringReader(resultData);
						 result = JAXB.unmarshal(reader, clazz);						 
					 }
				 }
			} else if (HttpStatus.SC_CONFLICT == response.getStatusLine().getStatusCode()) {
            	// 错误
               	int errorCode = Integer.valueOf(response.getFirstHeader("Error-Code").getValue());
             	String charset = response.getFirstHeader("Charset").getValue();
               	Header header = response.getFirstHeader("Error-Message");                
              	String errorMsg = charset != null ? new String(header.getValue().getBytes("ISO-8859-1"), charset) : new String(header.getValue().getBytes("ISO-8859-1"));
              	debug("return error code:" + errorCode + "; error message:" + errorMsg);
              	throw new AppException("http status : " + HttpStatus.SC_CONFLICT + "error : " + errorCode + ":" + errorMsg);
            } else {//其他错误，如 404 URI路径不存在
            	int statusCode = response.getStatusLine().getStatusCode();
            	debug("request data error\n request uri :" + uri);
            	debug("return status code:" + statusCode);
            	throw new AppException("http status : " + response.getStatusLine().getStatusCode());
            }
		} catch (IOException e) {
			debug(e);
		} finally {
			if (httpRequest != null) {
				httpRequest.abort();
			}
		}
		//其他错误
		return result;
	}
	
	/**
	 * Convert object to xml string.
	 * 
	 * @param obj
	 *            Object with JAXB annotations.
	 * @return XML string.
	 */
	@SuppressWarnings("rawtypes")
	public static String convertObjectToXml(Object obj, Class cls) {
		if (obj == null) {
			debug("Class Name:" + cls.getName());
			return null;
		}

		StringWriter writer = null;
		try {
			writer = new StringWriter();
			getMarshaller(cls).marshal(obj, writer);
			String result = writer.toString();
			// result = ServerUtils.toUTF8String(result);
			result = new String(gbkToUtf8(result), "UTF-8");
			return result;
		} catch (JAXBException e) {
			debug(e);
			return null;
		} catch (Exception e) {
			debug(e);
			return null;
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					debug(e);
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public static Marshaller getMarshaller(Class cls) throws JAXBException {
		Marshaller marshaller = null;
		JAXBContext context = JAXBContext.newInstance(cls);
		marshaller = context.createMarshaller();
		// 设置编码方式为GBK
		// domMarshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
		// //是否格式化生成的xml串
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// 省略xml头信息（<?xml version
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		return marshaller;
	}
	
	/**
	 * 将给定的字符串转换为UTF编码数组。主要用于将汉字转换为UTF-8编码。
	 * 
	 * @param chenese
	 *            输入字符串。
	 * @return the byte[] 经UTF编码后的数组
	 */
	public static byte[] gbkToUtf8(String chenese) {
		// Step 1: 得到GBK编码下的字符数组，一个中文字符对应这里的一个c
		char c[] = chenese.toCharArray();
		// Step 2: UTF-8使用3个字节存放一个中文字符，所以长度必须为字符的3 倍
		byte[] fullByte = new byte[3 * c.length];
		int pos = 0;
		// Step 3: 循环将字符的GBK编码转换成UTF-8编码
		for (int i = 0; i < c.length; i++) {
			// Step 3-1：将字符的ASCII编码转换成2进制值
			int m = (int) c[i];
			if (m >= 0 && m <= 255) {
				fullByte[pos] = (byte) m;
				pos++;
				continue;
			}
			String word = Integer.toBinaryString(m);

			// Step 3-2：将2进制值补足16位(2个字节的长度)
			StringBuffer sb = new StringBuffer();
			int len = 16 - word.length();
			for (int j = 0; j < len; j++) {
				sb.append("0");
			}
			// Step 3-3：得到该字符最终的2进制GBK编码
			// 形似：1000 0010 0111 1010
			sb.append(word);
			// Step 3-4：最关键的步骤，根据UTF-8的汉字编码规则，首字节
			// 以1110开头，次字节以10开头，第3字节以10开头。在原始的2进制
			// 字符串中插入标志位。最终的长度从16--->16+3+2+2=24。
			sb.insert(0, "1110");
			sb.insert(8, "10");
			sb.insert(16, "10");

			// Step 3-5：将新的字符串进行分段截取，截为3个字节
			String s1 = sb.substring(0, 8);
			String s2 = sb.substring(8, 16);
			String s3 = sb.substring(16);

			// Step 3-6：最后的步骤，把代表3个字节的字符串按2进制的方式
			// 进行转换，变成2进制的整数，再转换成16进制值
			byte b0 = Integer.valueOf(s1, 2).byteValue();
			byte b1 = Integer.valueOf(s2, 2).byteValue();
			byte b2 = Integer.valueOf(s3, 2).byteValue();

			// Step 3-7：把转换后的3个字节按顺序存放到字节数组的对应位置
			byte[] bf = new byte[3];
			bf[0] = b0;
			bf[1] = b1;
			bf[2] = b2;

			fullByte[pos] = bf[0];
			fullByte[pos + 1] = bf[1];
			fullByte[pos + 2] = bf[2];
			pos += 3;
			// Step 3-8：返回继续解析下一个中文字符
		}
		byte[] result = new byte[pos];
		System.arraycopy(fullByte, 0, result, 0, pos);
		return result;
	}
	
	private static void debug(Object obj) {
		System.out.println(RestUtil.class + "：" + obj);
	}
}
