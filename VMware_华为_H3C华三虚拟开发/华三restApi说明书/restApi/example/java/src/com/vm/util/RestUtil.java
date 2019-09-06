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
	 * ͨ��REST�ӿڲ�ѯ�ⲿ������, �˷���Ҫ��ӿڷ���״̬��Ϊ 200 {HttpStatus.SC_OK}��������Ϣ����״̬�� 409 {HttpStatus.SC_CONFLICT}��
	 * @param <T> ���ؽ������
	 * @param client http client
	 * @param uri �ӿ�uri
	 * @param clazz ���ؽ������
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
                // ��������
                int errorCode = Integer.valueOf(response.getFirstHeader("Error-Code").getValue());
                String charset = response.getFirstHeader("Charset").getValue();
                Header header = response.getFirstHeader("Error-Message");                
                String errorMsg = charset != null ? new String(header.getValue().getBytes("ISO-8859-1"), charset) : new String(header.getValue().getBytes("ISO-8859-1"));
                throw new AppException("Error code:" + errorCode + ",Error-Message:" + errorMsg);
            } else {//���������� 404 URI·��������
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
	 * ͨ��REST�ӿ�POST����, �˷���Ҫ��ӿڷ���״̬��Ϊ 204 {HttpStatus.SC_NO_CONTENT}/ 200 {HttpStatus.SC_OK}��������Ϣ����״̬�� 409 {HttpStatus.SC_CONFLICT}��
	 * @param <T>
	 * @param httpConnManager http ���ӹ�����
	 * @param cloud �����ƶ���
	 * @param uri �ӿ�uri
	 * @param obj ���͵���Ϣʵ��
	 * @param clazz ���ؽ������
	 * @return
	 */
	public static <T> T httpPost(DefaultHttpClient client, String uri, Object obj, Class<T> clazz) {
		return httppostOrPut(client, uri, obj, clazz, 0);
	}
	
	 /**
	 * ͨ��REST�ӿ�PUT����, �˷���Ҫ��ӿڷ���״̬��Ϊ 204 {HttpStatus.SC_NO_CONTENT}/ 200 {HttpStatus.SC_OK}��������Ϣ����״̬�� 409 {HttpStatus.SC_CONFLICT}��
	 * @param <T>
	 * @param httpConnManager http ���ӹ�����
	 * @param cloud �����ƶ���
	 * @param uri �ӿ�uri
	 * @param obj ���͵���Ϣʵ��
	 * @param clazz ���ؽ������
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
                // ��������
                int errorCode = Integer.valueOf(response.getFirstHeader("Error-Code").getValue());
                String charset = response.getFirstHeader("Charset").getValue();
                Header header = response.getFirstHeader("Error-Message");                
                String errorMsg = charset != null ? new String(header.getValue().getBytes("ISO-8859-1"), charset) : new String(header.getValue().getBytes("ISO-8859-1"));
                throw new AppException("Error code:" + errorCode + ", Error-Message:" + errorMsg);
            } else {//���������� 404 URI·��������
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
                // ��������
                int errorCode = Integer.valueOf(response.getFirstHeader("Error-Code").getValue());
                String charset = response.getFirstHeader("Charset").getValue();
                Header header = response.getFirstHeader("Error-Message");                
                String errorMsg = charset != null ? new String(header.getValue().getBytes("ISO-8859-1"), charset) : new String(header.getValue().getBytes("ISO-8859-1"));
                throw new AppException("Error code:" + errorCode + ", Error-Message:" + errorMsg);
            } else {//���������� 404 URI·��������
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
	 * ͨ��REST�ӿ�POST/PUT����, �˷���Ҫ��ӿڷ���״̬��Ϊ 204 {HttpStatus.SC_NO_CONTENT}/ 200 {HttpStatus.SC_OK}��������Ϣ����״̬�� 409 {HttpStatus.SC_CONFLICT}��
	 * @param <T>
	 * @param httpConnManager http ���ӹ�����
	 * @param cloud �����ƶ���
	 * @param uri �ӿ�uri
	 * @param obj ���͵���Ϣʵ��
	 * @param clazz ���ؽ������
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
				//�ɹ�����
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
            	// ����
               	int errorCode = Integer.valueOf(response.getFirstHeader("Error-Code").getValue());
             	String charset = response.getFirstHeader("Charset").getValue();
               	Header header = response.getFirstHeader("Error-Message");                
              	String errorMsg = charset != null ? new String(header.getValue().getBytes("ISO-8859-1"), charset) : new String(header.getValue().getBytes("ISO-8859-1"));
              	debug("return error code:" + errorCode + "; error message:" + errorMsg);
              	throw new AppException("http status : " + HttpStatus.SC_CONFLICT + "error : " + errorCode + ":" + errorMsg);
            } else {//���������� 404 URI·��������
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
		//��������
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
		// ���ñ��뷽ʽΪGBK
		// domMarshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
		// //�Ƿ��ʽ�����ɵ�xml��
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// ʡ��xmlͷ��Ϣ��<?xml version
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		return marshaller;
	}
	
	/**
	 * ���������ַ���ת��ΪUTF�������顣��Ҫ���ڽ�����ת��ΪUTF-8���롣
	 * 
	 * @param chenese
	 *            �����ַ�����
	 * @return the byte[] ��UTF����������
	 */
	public static byte[] gbkToUtf8(String chenese) {
		// Step 1: �õ�GBK�����µ��ַ����飬һ�������ַ���Ӧ�����һ��c
		char c[] = chenese.toCharArray();
		// Step 2: UTF-8ʹ��3���ֽڴ��һ�������ַ������Գ��ȱ���Ϊ�ַ���3 ��
		byte[] fullByte = new byte[3 * c.length];
		int pos = 0;
		// Step 3: ѭ�����ַ���GBK����ת����UTF-8����
		for (int i = 0; i < c.length; i++) {
			// Step 3-1�����ַ���ASCII����ת����2����ֵ
			int m = (int) c[i];
			if (m >= 0 && m <= 255) {
				fullByte[pos] = (byte) m;
				pos++;
				continue;
			}
			String word = Integer.toBinaryString(m);

			// Step 3-2����2����ֵ����16λ(2���ֽڵĳ���)
			StringBuffer sb = new StringBuffer();
			int len = 16 - word.length();
			for (int j = 0; j < len; j++) {
				sb.append("0");
			}
			// Step 3-3���õ����ַ����յ�2����GBK����
			// ���ƣ�1000 0010 0111 1010
			sb.append(word);
			// Step 3-4����ؼ��Ĳ��裬����UTF-8�ĺ��ֱ���������ֽ�
			// ��1110��ͷ�����ֽ���10��ͷ����3�ֽ���10��ͷ����ԭʼ��2����
			// �ַ����в����־λ�����յĳ��ȴ�16--->16+3+2+2=24��
			sb.insert(0, "1110");
			sb.insert(8, "10");
			sb.insert(16, "10");

			// Step 3-5�����µ��ַ������зֶν�ȡ����Ϊ3���ֽ�
			String s1 = sb.substring(0, 8);
			String s2 = sb.substring(8, 16);
			String s3 = sb.substring(16);

			// Step 3-6�����Ĳ��裬�Ѵ���3���ֽڵ��ַ�����2���Ƶķ�ʽ
			// ����ת�������2���Ƶ���������ת����16����ֵ
			byte b0 = Integer.valueOf(s1, 2).byteValue();
			byte b1 = Integer.valueOf(s2, 2).byteValue();
			byte b2 = Integer.valueOf(s3, 2).byteValue();

			// Step 3-7����ת�����3���ֽڰ�˳���ŵ��ֽ�����Ķ�Ӧλ��
			byte[] bf = new byte[3];
			bf[0] = b0;
			bf[1] = b1;
			bf[2] = b2;

			fullByte[pos] = bf[0];
			fullByte[pos + 1] = bf[1];
			fullByte[pos + 2] = bf[2];
			pos += 3;
			// Step 3-8�����ؼ���������һ�������ַ�
		}
		byte[] result = new byte[pos];
		System.arraycopy(fullByte, 0, result, 0, pos);
		return result;
	}
	
	private static void debug(Object obj) {
		System.out.println(RestUtil.class + "��" + obj);
	}
}
