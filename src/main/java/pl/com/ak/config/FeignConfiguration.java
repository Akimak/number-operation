package pl.com.ak.config;

import feign.Client;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.cloud.commons.httpclient.ApacheHttpClientFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("pl.com.ak.client")
public class FeignConfiguration {

  @Bean
  public Client feignClient(
      final ApacheHttpClientFactory httpClientFactory,
      final HttpClientConnectionManager httpClientConnectionManager,
      final FeignHttpClientProperties httpClientProperties) {

    final RequestConfig defaultRequestConfig = getRequestConfig(httpClientProperties);
    final CloseableHttpClient httpClient = getCloseableHttpClient(
        httpClientFactory,
        httpClientConnectionManager,
        defaultRequestConfig);
    return new ApacheHttpClient(httpClient);
  }

  private CloseableHttpClient getCloseableHttpClient(
      final ApacheHttpClientFactory httpClientFactory,
      final HttpClientConnectionManager httpClientConnectionManager,
      final RequestConfig defaultRequestConfig) {
    return httpClientFactory
        .createBuilder()
        .setConnectionManager(httpClientConnectionManager)
        .setDefaultRequestConfig(defaultRequestConfig)
        .evictExpiredConnections()
        .build();
  }

  private RequestConfig getRequestConfig(final FeignHttpClientProperties httpClientProperties) {
    return RequestConfig
        .custom()
        .setConnectTimeout(httpClientProperties.getConnectionTimeout())
        .setRedirectsEnabled(httpClientProperties.isFollowRedirects())
        .build();
  }
}
