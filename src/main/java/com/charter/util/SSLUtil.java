package com.charter.util;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SSLUtil{
	
	private static final Logger logger = LoggerFactory.getLogger(SSLUtil.class);

    static {
        //for localhost testing only
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
        new javax.net.ssl.HostnameVerifier(){

            public boolean verify(String hostname,
                    javax.net.ssl.SSLSession sslSession) {
            	return hostname.equalsIgnoreCase(sslSession.getPeerHost());
            }
        });
    }

    private static final TrustManager[] UNQUESTIONING_TRUST_MANAGER = new TrustManager[]{
            new X509TrustManager() {
            	@Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers(){
                    return new X509Certificate[0];
                }
                @Override
                public void checkClientTrusted(final java.security.cert.X509Certificate[] certs, String authType ){
                	
                   try {
					throw new CertificateException("None of the TrustManagers trust this certificate certs");
				} catch (CertificateException e) {
					logger.debug("Exception caught in Client Trusted :: {}",e);
					
				}
                }
                @Override
                public void checkServerTrusted(final java.security.cert.X509Certificate[] certs, String authType) {
                	 
                	try {
						throw new CertificateException("None of the TrustManagers trust this certificate certs");
					} catch (CertificateException ex) {
						
						logger.debug("Exception caught in Client Trusted :: {}",ex);
					}
                }
                
            }
        };

    public  static void turnOffSslChecking() throws NoSuchAlgorithmException, KeyManagementException {
        // Install the all-trusting trust manager
        final SSLContext sc = SSLContext.getInstance("TLS");
        sc.init( null, UNQUESTIONING_TRUST_MANAGER, null );
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    public static void turnOnSslChecking() throws KeyManagementException, NoSuchAlgorithmException {
        // Return it to the initial state (discovered by reflection, now hardcoded)
        SSLContext.getInstance("TLS").init( null, null, null );
    }

    private SSLUtil(){
        throw new UnsupportedOperationException( "Do not instantiate libraries.");
    }
}
