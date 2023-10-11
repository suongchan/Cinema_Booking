package com.example.cinema_booking.Remote;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;

@Component
public class PaymentAPI {

    public void getQrFromOtherClient(RequestBody requestBody) {
        //Url
        String url =  UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api-merchant.payos.vn")
                .path("v2/payment-requests")
                .build()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-client-id", "9f9afe18-099b-4cd0-81c1-fc229326dccf");
        headers.set("x-api-key", "f13eb249-fa4d-4a01-95f6-3656f8d81406");


        HttpEntity<RequestBody> requestEntity = new HttpEntity<>(requestBody, headers);

        RestOperations restOperations = new RestTemplate();

        ResponseBody x = restOperations.postForEntity(url, requestEntity, ResponseBody.class).getBody();
        assert x != null;
//        System.out.println(x.getData().getCheckoutUrl());
    }

    //        clientId
//        9f9afe18-099b-4cd0-81c1-fc229326dccf
//        Api Key
//        f13eb249-fa4d-4a01-95f6-3656f8d81406
//        Checksum Key
//        22ee21ab306b80fac1782bb426e6140498bc4b5b9f483f30d4883f320731e29e


    public static void main(String[] args) {

        RequestBody requestBody = new RequestBody();
        requestBody.setOrderCode(99999999999L);
        requestBody.setAmount(40000L);
        requestBody.setDescription("tuankhungs");
        requestBody.setBuyerName("Suong");
        requestBody.setBuyerAddress("Quang Nam");
        requestBody.setBuyerPhone("0707125903");
        requestBody.setBuyerEmail("suong@gmail.com");
        requestBody.setCancelUrl("cancelURL");
        requestBody.setSignature("bea83bf3ad7eb1ffea4ec9c2e2c094d99f9ca3f8995acbf3575f374f24305a5b");
        requestBody.setReturnUrl("returnUrl");

        RequestItem item = new RequestItem();
        item.setName("ve nguoi lon");
        item.setPrice(20000L);
        item.setQuantity(1);

        RequestItem item1 = new RequestItem();
        item1.setName("combo 1");
        item1.setPrice(20000L);
        item1.setQuantity(1);

        requestBody.setItems(List.of(item));
        requestBody.setItems(List.of(item1));


        PaymentAPI api = new PaymentAPI();
        api.getQrFromOtherClient(requestBody);
    }

}
@Getter
@Setter
 class RequestBody {
    private Long orderCode;
    private Long amount;
    private String description;
    private String buyerName;
    private String buyerEmail;
    private String buyerPhone;
    private String buyerAddress;
    private String cancelUrl;
    private String returnUrl;
    private String signature;
    private List<RequestItem> items;

}

@Getter
@Setter
 class RequestItem {
    private String name;
    private int quantity;
    private long price;
}

@Getter
@Setter
class ResponseBody {
    private String code;
    private String desc;
    private String signature;
    private ResponseData data;

}
@Getter
@Setter
class ResponseData {
    private String bin;
    private String accountNumber;
    private String accountName;
    private Long amount;
    private String description;
    private Long orderCode;
    private String paymentLinkId;
    private String status;
    private String checkoutUrl;
    private String qrCode;
}
