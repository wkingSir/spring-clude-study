package com.ribbon.eurekaribbonconsumer;

import com.baijz.bean.BookBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : Administrator
 * @Date : 2018/12/1 16 02
 * @Description :
 */
@RestController
public class HelloComsumer {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/ribbon-server" ,method = RequestMethod.GET)
    public String helloController(){

        return restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
    }

    @RequestMapping(value = "/get-hello", method = RequestMethod.GET)
    public String getHello() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class);
        String body = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpHeaders headers = responseEntity.getHeaders();
        StringBuffer result = new StringBuffer();
        result.append("responseEntity.getBody()：").append(body).append("<hr>")
                .append("responseEntity.getStatusCode()：").append(statusCode).append("<hr>")
                .append("responseEntity.getStatusCodeValue()：").append(statusCodeValue).append("<hr>")
                .append("responseEntity.getHeaders()：").append(headers).append("<hr>");
        return result.toString();
    }

    @RequestMapping("/getParameter1")
    public String sayHello(String name,int age) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity
                ("http://HELLO-SERVICE/say-hello?name={1}&age={2}",
                String.class, name,age);
        return responseEntity.getBody();
    }
    @RequestMapping("/getParameter2")
    public String sayHello2(String name,int age) {
        Map<String, Object> map = new HashMap<>();
        map.put("name",name);
        map.put("age",age);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity
                ("http://HELLO-SERVICE/say-hello?name={name}&age={age}", String.class, map);
        return responseEntity.getBody();
    }

    /**
     * 通过UriComponents来构建请求的参数
     * @return
     */
    @RequestMapping("/getParameter3")
    public String sayHello3(String name,int age) {
        Map<String ,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("age",age);
        UriComponents uriComponents = UriComponentsBuilder.fromUriString
                ("http://HELLO-SERVICE/say-hello?name={name}&age={age}").build().expand(map).encode();
        URI uri = uriComponents.toUri();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        return responseEntity.getBody();
    }

    /**
     * 获取对象
     * @return
     */
    @RequestMapping(value = "/getBook", method = RequestMethod.GET)
    public BookBase getBook(){
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://HELLO-SERVICE/provide-book").build().encode();
        URI uri = uriComponents.toUri();
        //ResponseEntity<BookBase> responseEntity = restTemplate.getForEntity(uri,BookBase.class);
        //return responseEntity.getBody();
        return restTemplate.getForObject(uri,BookBase.class);
    }

    /**
     * 通过post获取对象
     * @return
     */
    @RequestMapping(value = "/postBook", method = RequestMethod.GET)
    public URI getBookByPost(HttpServletRequest request){
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://HELLO-SERVICE/provide-book").build().encode();
        URI uri = uriComponents.toUri();
        ResponseEntity<BookBase> responseEntity = restTemplate.postForEntity(uri,"",BookBase.class);
        //return responseEntity.getBody();
        //return restTemplate.postForObject("http://HELLO-SERVICE/provide-book",null,BookBase.class);
        URI uri1 = restTemplate.postForLocation("http://HELLO-SERVICE/provide-book-uri","");//这里返回的是空值
        return uri1;
    }
}
