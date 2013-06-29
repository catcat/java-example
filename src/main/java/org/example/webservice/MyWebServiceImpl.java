package org.example.webservice;


import javax.jws.WebService;

@WebService(endpointInterface = "org.example.webservice.MyWebService")
public class MyWebServiceImpl implements MyWebService {
    @Override
    public String compute(int a, int b) {
        return String.format("Result: %d + %d = %d",a,b,a*b);
    }
}
