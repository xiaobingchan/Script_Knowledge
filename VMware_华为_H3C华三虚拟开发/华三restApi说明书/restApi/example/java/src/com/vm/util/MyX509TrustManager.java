package com.vm.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyX509TrustManager implements X509TrustManager{
	
    /** 日志记录对象。 */
    private static Log log = LogFactory.getLog(MyX509TrustManager.class);
	
    private static final String KEY_STORE = "/var/lib/tomcat6/security/keystore";
	
	X509TrustManager sunJSSEX509TrustManager;
	
	MyX509TrustManager() throws Exception {
		// create a "default" JSSE X509TrustManager.
		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream instream = new FileInputStream(new File(KEY_STORE));
		trustStore.load(instream, "h3cbj2013".toCharArray());
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509", "SunJSSE");
		tmf.init(trustStore);
		TrustManager tms[] = tmf.getTrustManagers();
		/*
		 * Iterate over the returned trustmanagers, look for an instance of
		 * X509TrustManager. If found, use that as our "default" trust manager.
		 */
		for (int i = 0; i < tms.length; i++) {
			if (tms[i] instanceof X509TrustManager) {
				sunJSSEX509TrustManager = (X509TrustManager) tms[i];
				return;
			}
		}
		throw new Exception("Couldn't initialize");
	}

	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		try {
            sunJSSEX509TrustManager.checkClientTrusted(chain, authType);
        } catch (CertificateException excep) {
        	log.error(null, excep);
        }
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		 try {
             sunJSSEX509TrustManager.checkServerTrusted(chain, authType);
         } catch (CertificateException excep) {
        	 log.error(null, excep);
         }

	}

	public X509Certificate[] getAcceptedIssuers() {
		return sunJSSEX509TrustManager.getAcceptedIssuers();
	}

}
