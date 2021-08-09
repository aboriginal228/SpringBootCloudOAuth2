package fr;

//@Configuration
//public class StatisticFeignClientConfiguration {
//
//    @Bean
//    RequestInterceptor oauth2FeignRequestInterceptor() {
//        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resource());
//    }
//
//    private OAuth2ProtectedResourceDetails resource() {
//        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
//        resourceDetails.setUsername("alex");
//        resourceDetails.setPassword("123");
//        resourceDetails.setAccessTokenUri("http://localhost:8780/auth/user");
//        resourceDetails.setClientId("flametoken");
//        resourceDetails.setClientSecret("thisissecret");
//        resourceDetails.setGrantType("password");
//        resourceDetails.setScope(Arrays.asList("webclient"));
//        return resourceDetails;
//    }
//}
