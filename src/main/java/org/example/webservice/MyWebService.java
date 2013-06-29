package org.example.webservice;

import javax.jws.WebService;

@WebService
public interface MyWebService {
    String compute(int a, int b);
}
