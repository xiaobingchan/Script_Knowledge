package com.vm.util;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class DownLoadFileClient {
	
    /** ��־��¼ʵ�塣 */
    private static Log log = LogFactory.getLog(DownLoadFileClient.class);
    
    private final static int SC_OK = 200;

	/**
	 * ȷ���ļ��Ƿ��Ѿ����أ���û���������
	 * @param pathAndFile
	 * @return
	 */
	public static boolean isFileExist(String pathAndFile) {
		File file = new File(pathAndFile);
		if (file.exists())
			return true;
		else
			return false;
	}
	
	/**
	 * ȷ���Ѿ������˵��ļ���С
	 * @param pathAndFile
	 * @return
	 */
	public static long getFileSize(String pathAndFile) {
		File file = new File(pathAndFile);
		return file.length();
	}
	
	/**
	 * ��������ȫ���ļ�������ȥ��.tp��
	 * @param fName
	 * @param nName
	 */
	public static void fileRename(String fName, String nName) {
		File file = new File(fName);
		file.renameTo(new File(nName));
		file.delete();
	}

	public static void downLoadFile(String urlStr, String localFilePath) {
		URL url = null;
		HttpURLConnection urlc = null;
		DataOutputStream dos = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		int len = 0;
		byte[] bt = new byte[20480000];
		RandomAccessFile raFile = null;
		try {
			url = new URL(urlStr);
			if (urlStr.startsWith("https")) {
				urlc = (HttpsURLConnection)url.openConnection();
				
				// ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
		        TrustManager[] tm = { new MyX509TrustManager() };
		        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		        sslContext.init(null, tm, new SecureRandom());
		        // ������SSLContext�����еõ�SSLSocketFactory����
		        SSLSocketFactory ssf = sslContext.getSocketFactory();
		        //��У������
		        ((HttpsURLConnection)urlc).setHostnameVerifier(new TrustAnyHostnameVerifier());
		        ((HttpsURLConnection)urlc).setSSLSocketFactory(ssf);
			} else {
				urlc = (HttpURLConnection) url.openConnection();
			}
			
			// ���ý�����Ϣ
			urlc.setRequestProperty("Accept", "*/*");
			if (SC_OK == urlc.getResponseCode()) {
				// ����ԭʼ����
				// û��������Ͼͽ��ļ�����չ������.tp
				fos = new FileOutputStream(localFilePath); 
				dos = new DataOutputStream(fos);
				bis = new BufferedInputStream(urlc.getInputStream());
				// ѭ����ȡ�ļ�
				while ((len = bis.read(bt)) > 0) {
					dos.write(bt, 0, len);
				}
			} else {
				// ��������
                Integer errorCode = Integer.valueOf(urlc.getHeaderField("Error-Code"));
                String charset = urlc.getHeaderField("Charset");
                String header = urlc.getHeaderField("Error-Message");
                if (errorCode != null && charset != null && header != null) {
                	String errorMsg = charset != null ? new String(header.getBytes("ISO-8859-1"), charset) : new String(header.getBytes("ISO-8859-1"));
                	log.error("return status code:" + urlc.getResponseCode());
                	log.error("return error code:" + errorCode);
                	log.error("return error message:" + errorMsg);
                } else {
                	log.error("UNKNOWN_ERROR");
                }
			}
		} catch (AppException ae) {
			log.error(null, ae);
		} catch (Exception e) {
			log.error(null, e);
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (dos != null) {
					dos.close();
				}
				if (fos != null) {
					fos.close();
				}
				if (raFile != null) {
					raFile.close();
				}
			} catch (IOException e) {
				log.error(null, e);
			}
		}
    }
}
